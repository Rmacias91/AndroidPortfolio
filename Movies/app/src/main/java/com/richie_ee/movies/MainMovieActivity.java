package com.richie_ee.movies;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_movie);
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainMovieFragment())
                    .commit();
        }

    }

    static public class MainMovieFragment extends Fragment{

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

            String[] data = new String[]{"Richie","Rocks","So Much","Its Impossirburee","Richie","Rocks","So Much","Its Impossirburee",
                    "Richie","Rocks","So Much","Its Impossirburee",
                    "Richie","Rocks","So Much","Its Impossirburee",
                    "Richie","Rocks","So Much","Its Impossirburee",};

            View v = inflater.inflate(R.layout.fragment_main_movie, container, false);

            RecyclerView rvData = (RecyclerView) v.findViewById(R.id.rvData);

            MovieAdapter adapter = new MovieAdapter(getContext(), data);

            rvData.setAdapter(adapter);
            GridLayoutManager gridLayoutManager =
                    new GridLayoutManager(getContext(),2);
            rvData.setLayoutManager(gridLayoutManager);
            return v;
        }
    }

}


