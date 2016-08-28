package com.example.richie.currently;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by richardmacias on 7/20/16.
 */
public class PersonLab {
    private static PersonLab sPersonLab;//Creates a Singleton object of CrimeLab class.
    private List<Person> mPersons;

    public static PersonLab get(Context context) {
        if (sPersonLab == null) {
            sPersonLab = new PersonLab(context);
        }
        return sPersonLab;

    }
    private PersonLab(Context context) {
        //will make use of Context in Chapter 14.
        mPersons = new ArrayList<>();
        for(int i =0; i<100; i++){//100 fake crimes
            Person person = new Person();
            person.setName("Employee#"+i);
            person.setAvailability("Free");
            person.createTask("Finish schematics",3, "Salah Munasser");
            mPersons.add(person);
        }
    }

    public List<Person> getPersons(){
        return mPersons;
    }

    public Person getPerson(UUID id){
        for(Person person:mPersons){
            if(person.getId().equals(id)){
                return person;
            }
        }
        return null;
    }
}
