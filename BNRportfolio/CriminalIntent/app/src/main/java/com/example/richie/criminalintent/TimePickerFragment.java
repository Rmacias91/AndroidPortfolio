package com.example.richie.criminalintent;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Richie on 7/17/2016.
 */
public class TimePickerFragment extends DialogFragment {

    private TimePicker mTimePicker;
    private static final String ARG_DATE = "date";
    public static final String EXTRA_HOUR = "com.example.richie.criminalintent.hour";
    public static final String EXTRA_MINUTES = "com.example.richie.criminalintent.minutes";

    public static TimePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE,date);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_time,null);

        Date date =(Date)getArguments().getSerializable(ARG_DATE);
        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_picker);
        mTimePicker.setCurrentHour(date.getHours());
        mTimePicker.setCurrentMinute(date.getMinutes());
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.time_picker_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                int hour = mTimePicker.getCurrentHour();
                                int minutes = mTimePicker.getCurrentMinute();
                                sendResult(Activity.RESULT_OK,hour,minutes);
                            }
                        })
                .create();
    }
    private void sendResult(int resultCode, int hour, int minutes){
        if(getTargetFragment()==null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_HOUR,hour);
        intent.putExtra(EXTRA_MINUTES,minutes);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(),resultCode,intent);
    }
}
