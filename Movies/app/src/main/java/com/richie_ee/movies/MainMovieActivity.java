package com.richie_ee.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_container);
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainMovieFragment())
                    .commit();
        }

    }


}


