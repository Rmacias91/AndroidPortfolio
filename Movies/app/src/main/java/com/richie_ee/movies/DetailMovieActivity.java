package com.richie_ee.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Richie on 10/30/2016.
 */
public class DetailMovieActivity extends AppCompatActivity {

    public DetailMovieActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_movie);
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailMovieFragment()
                    .commit();
        }

    }












    public static class DetailMovieFragment extends Fragment{




    }

}
