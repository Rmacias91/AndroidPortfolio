package com.richie_ee.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.movie_detail, container, false);

            Bundle data = getActivity().getIntent().getExtras();
            Movie movie = (Movie) data.getParcelable("student");


            return rootView;
        }

    }

}
