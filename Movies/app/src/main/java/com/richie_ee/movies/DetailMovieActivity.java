package com.richie_ee.movies;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Richie on 10/30/2016.
 */
public class DetailMovieActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_container);
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailMovieFragment())
                    .commit();
        }

    }


    public static class DetailMovieFragment extends Fragment{
        private final String LOG_TAG= DetailMovieActivity.class.getSimpleName();

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.movie_detail, container, false);

            Bundle data = getActivity().getIntent().getExtras();
            Movie movie = (Movie) data.getParcelable("movie");
            TextView titleView = (TextView) rootView.findViewById(R.id.detail_title);
            TextView plotView = (TextView) rootView.findViewById(R.id.detail_plot);
            TextView plotExtraView= (TextView) rootView.findViewById(R.id.detail_plotExtra);
            TextView releaseView = (TextView) rootView.findViewById(R.id.detail_release);
            TextView ratingView = (TextView) rootView.findViewById(R.id.detail_rating);

            ImageView posterImage = (ImageView) rootView.findViewById(R.id.detail_poster);

            titleView.setText(movie.getTitle());

            String plot = movie.getPlot();
            if( (plot.length()<291) || (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE)){
                plotView.setText(plot);
            }else{
                String[] plotArray= SplitPlot(plot);
                plotView.setText(plotArray[0]);
                plotExtraView.setText(plotArray[1]);

            }
            //Log.v(LOG_TAG,"Plot length is:"+ movie.getPlot().length());



            releaseView.setText(movie.getReleaseDate());
            ratingView.setText(movie.getRating());

            Picasso.with(getContext()).load(movie.getPosterPath()).into(posterImage);


            return rootView;
        }

        //If plot length is over 290 use this function to split into 2 views.
        //Pass in the original String for plot, return an array of 2 String elements.
        //This is really bad code, refactor and call Mario. Prob a way easier way to do this. Function is too long
        //This way will split string count.. will mess up on different size screens for sure!
        private String[] SplitPlot(String originalPlot){
            String plot1= originalPlot.substring(0,300);
            String plot2 = originalPlot.substring(300,originalPlot.length());
            String[] newPlot = {plot1,plot2};


//This way will split in words, and I just have to find the # of words from when to split.
//            String plotArray[] = originalPlot.split(" ");
//            int half= plotArray.length/2;
//
//            StringBuilder builder = new StringBuilder();
//            for(int i =0; i< half; i++) {
//                builder.append(plotArray[i]);
//                builder.append(" ");
//            }
//            String plot1 = builder.toString();
//            StringBuilder builder2 = new StringBuilder();
//            for(int i =half; i< plotArray.length; i++) {
//                builder2.append(plotArray[i]);
//                builder2.append(" ");
//            }
//            String plot2= builder2.toString();
//            String[] newPlot={plot1,plot2};
            return newPlot;
        }


    }

}
