package com.example.richie.criminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

public class CrimeActivity extends SingleFragmentActivity {

    //Just passes CrimeFragment to the abstract class now! cool.
    @Override
    protected Fragment createFragment(){
        return new CrimeFragment();
    }
}
