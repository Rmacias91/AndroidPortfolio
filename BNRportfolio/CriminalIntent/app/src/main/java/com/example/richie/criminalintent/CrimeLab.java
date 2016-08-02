package com.example.richie.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Richie on 7/15/2016.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;//Creates a Singleton object of CrimeLab class.
    private List<Crime> mCrimes;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;

    }
    private CrimeLab(Context context) {
        //will make use of Context in Chapter 14.
        mCrimes = new ArrayList<>();
    }

    public List<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for(Crime crime:mCrimes){
            if(crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }
    public void addCrime(Crime c){
        mCrimes.add(c);
    }

}
