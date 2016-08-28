package com.example.richie.currently;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by richardmacias on 8/3/16.
 */
public class PersonFragment extends Fragment {

    private static final String ARG_PERSON_ID="person_id";


    public static PersonFragment newInstance(UUID personId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_PERSON_ID,personId);

        PersonFragment fragment= new PersonFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
