package com.richie_ee.movies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by Richie on 10/22/2016.
 */

public class MainMovieFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String[] data = new String[]{"Richie","Rocks","So Much","Its Impossirburee","Richie","Rocks","So Much","Its Impossirburee",
                "Richie","Rocks","So Much","Its Impossirburee",};
        List<Movie> movies;

        View v = inflater.inflate(R.layout.fragment_main_movie, container, false);



        RecyclerView rvData = (RecyclerView) v.findViewById(R.id.rvData);

        MovieAdapter adapter = new MovieAdapter(getContext(), data);

        rvData.setAdapter(adapter);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getContext(),2);
        rvData.setLayoutManager(gridLayoutManager);
        return v;
    }

    public class fetchMovieTask extends AsyncTask<String,Void,List<Movie>>{

        private <List>Movie getMovieListFromJSON(String result){
            //TODO Sort JSON String
        }

        @Override
        protected List<Movie> doInBackground(String... strings) {
            //TODO Call Movie API
            //Strings will contain the sorting. Just change API calls i think(Verify)
            //if(strings.length ==0){
            //return null;
            //}

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            //for now i will change Base URL based on what user sorts
            //I might sort current list by Rating or.. Just store two lists
            //and Swap either or out with adaper.

            String sortBy = "popular";
            //Later will be Strings[0]


        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
        //TODO Update List Here and Notify Custom Adapter of Change
        }
    }


}