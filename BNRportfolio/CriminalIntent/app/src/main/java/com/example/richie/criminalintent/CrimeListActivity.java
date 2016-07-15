package com.example.richie.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Richie on 7/15/2016.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
