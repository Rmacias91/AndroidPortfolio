package com.richie_ee.movies;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richie on 10/22/2016.
 */

public class MainMovieFragment extends Fragment {
    private final String LOG_TAG = MainMovieFragment.class.getSimpleName();
    private List<Movie> mMovies;
    private MovieAdapter mAdapter;

    @Override
    public void onStart(){
        super.onStart();
        updateMovies();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main_movie, container, false);

        RecyclerView rvData = (RecyclerView) v.findViewById(R.id.rvData);
        mMovies = new ArrayList<>();
        //Pass movie datalist into adapter
        //updateMovies();
        Log.v(LOG_TAG, "MoviesList size is: "+ mMovies.size());
        mAdapter = new MovieAdapter(getContext(), mMovies);
        rvData.setAdapter(mAdapter);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getContext(),2);
        rvData.setLayoutManager(gridLayoutManager);
        return v;
    }

    private void updateMovies(){
        fetchMovieTask  fetchMovieTask = new fetchMovieTask();
        fetchMovieTask.execute();
    }

    public class fetchMovieTask extends AsyncTask<String,Void,List<Movie>>{

        private final String LOG_TAG = fetchMovieTask.class.getSimpleName();

        private List<Movie> getMovieListFromJSON(String movieJSonString)
            throws JSONException{

            List<Movie> movies = new ArrayList<>();

            JSONObject reader = new JSONObject(movieJSonString);
            JSONArray resultsArray = reader.getJSONArray("results");

            //Go Through whole list and store into Movie List Object;
            for(int i =0; i <resultsArray.length(); i++){
                JSONObject jObj = resultsArray.getJSONObject(i);
                String poster_path = jObj.getString("poster_path");
                String BASE_URL ="http://image.tmdb.org/t/p/";
                String size = "w185";
                String poster_image= BASE_URL+size+poster_path;


                String title= jObj.getString("original_title");
                String plot= jObj.getString("overview");
                String rating= jObj.getString("vote_average");
                String release_date= jObj.getString("title");
                movies.add(new Movie(poster_image,title,plot,rating,release_date));
            }
        return movies;
        }

        @Override
        protected List<Movie> doInBackground(String... strings) {
            //Strings will contain the sorting. Just change API calls i think(Verify)
            //if(strings.length ==0){
            //return null;
            //}

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            //for now i will change Base URL based on what user sorts
            //I might sort current list by Rating or.. Just store two lists
            //and Swap either or out with adaper.
            String moviesJsonString;
            String sortBy = "popular";
            //Later will be Strings[0]
            try {
                final String MOVIE_BASE_URL =
                        "http://api.themoviedb.org/3/movie/" + sortBy + "?";
                final String APPID_PARAM = "api_key";
                //TODO Call multiple pages with the page? Parameter and store into list
                //Build the URI from the URL and API KEY
                Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                        .appendQueryParameter(APPID_PARAM, BuildConfig.MOVIE_API_KEY)
                        .build();

                //Open the connection
                URL url = new URL(builtUri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                //Create an input stream and a buffer
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while((line = reader.readLine()) != null){
                    buffer.append(line + "\n");
                }

                moviesJsonString = buffer.toString();


            }catch(IOException e){
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
                }finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
            try{
                return getMovieListFromJSON(moviesJsonString);
            }catch(JSONException e){
                Log.e(LOG_TAG,e.getMessage(), e);
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
            mMovies.clear();
            mMovies.addAll(movies);
            mAdapter.notifyDataSetChanged();
            Log.v(LOG_TAG,"mMovies after Async is size: "+ mMovies.size());

        }
    }


}