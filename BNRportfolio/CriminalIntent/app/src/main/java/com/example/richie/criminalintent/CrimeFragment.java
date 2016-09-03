package com.example.richie.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by Richie on 7/9/2016.
 */
public class CrimeFragment extends Fragment {
    private static final String ARG_CRIME_ID= "crime_id";
    private static final String DIALOG_DATE="DialogDate";
    private static final String DIALOG_TIME="DialogTime";

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckbox;
    private Button mTimeButton;


    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args= new Bundle();
        args.putSerializable(ARG_CRIME_ID,crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime= CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override
    public void onPause(){
        super.onPause();
        
        CrimeLab.get(getActivity())
                .updateCrime(mCrime);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_crime,container,false);

        mTitleField= (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //nothing
            }
        });
        mDateButton = (Button) v.findViewById(R.id.crime_date);


        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this,REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);

            }
        });
        mTimeButton = (Button) v.findViewById(R.id.crime_time);
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = TimePickerFragment
                        .newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this,REQUEST_TIME);
                dialog.show(manager,DIALOG_TIME);
            }
        });
        updateDate();//Update Date and Time Buttons
        mSolvedCheckbox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckbox.setChecked(mCrime.isSolved());
        mSolvedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mCrime.setSolved(b);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_DATE){
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }
        if(requestCode ==REQUEST_TIME){
            int hour = data
                    .getIntExtra(TimePickerFragment.EXTRA_HOUR,0);
            int minutes = data.
                    getIntExtra(TimePickerFragment.EXTRA_MINUTES,0);
            Date date = mCrime.getDate();
            date.setHours(hour);
            date.setMinutes(minutes);
            updateDate();
        }

    }

    private void updateDate() {
        String dateOfCrime = new SimpleDateFormat("EEE MM/dd/yyyy",Locale.US).format(mCrime.getDate());
        mDateButton.setText(dateOfCrime);
        String timeOfCrime = new SimpleDateFormat("hh:mm a",Locale.US).format(mCrime.getDate());
        mTimeButton.setText(timeOfCrime);
    }

}
