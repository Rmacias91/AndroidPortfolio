package com.example.richie.currently;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by richardmacias on 7/20/16.
 */
public class PersonLab {
    private static Personlab sPersonlab;//Creates a Singleton object of CrimeLab class.
    private List<Person> mPersons;

    public static PersonLab get(Context context) {
        if (sPersonlab == null) {
            sPersonlab = new PersonLab(context);
        }
        return sPersonLab;

    }
    private PersonLab(Context context) {
        //will make use of Context in Chapter 14.
        mPersons = new ArrayList<>();
        for(int i =0; i<100; i++){//100 fake crimes
            Person person = new Person();
            crime.setTitle("Crime #"+ i);
            crime.setSolved(i%2 ==0);
            mCrimes.add(crime);
        }
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
}
