package com.example.richie.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.richie.criminalintent.database.CrimeBaseHelper;
import com.example.richie.criminalintent.database.CrimeDbSchema;
import com.example.richie.criminalintent.database.CrimeDbSchema.CrimeTable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Richie on 7/15/2016.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;//Creates a Singleton object of CrimeLab class.
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;

    }
    private CrimeLab(Context context) {
        //will make use of Context in Chapter 14.
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext)
                .getWritableDatabase();

    }

    public List<Crime> getCrimes(){
        return new ArrayList<>();
    }

    public Crime getCrime(UUID id){
        return null;
    }
    public void updateCrime(Crime crime){
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeTable.NAME, values,
                CrimeTable.Cols.UUID + " = ?", new String[] {uuidString});
    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE,crime.getTitle());
        values.put(CrimeTable.Cols.DATE,crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);

        return values;
    }
    public void addCrime(Crime c){
        ContentValues values = getContentValues(c);
        mDatabase.insert(CrimeTable.NAME, null, values);
    }

}
