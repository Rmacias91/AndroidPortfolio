package com.example.richie.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Richie on 9/24/2016.
 */
public class ImageViewerFragment extends DialogFragment{

    private static final String ARG_PHOTO = "photo";

    public static ImageViewerFragment newInstance(File file){
        Bundle args = new Bundle();
        args.putSerializable(ARG_PHOTO, file);
        ImageViewerFragment fragment = new ImageViewerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        File mPhotoFile = (File) getArguments().getSerializable(ARG_PHOTO);



        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_image,null);

       ImageView mImageView = (ImageView) v.findViewById(R.id.dialog_image_viewer);

        if (mPhotoFile != null) {

            //int y = mImageView.getMeasuredHeight();
            //int x = mImageView.getMeasuredWidth();
            Point size = new Point();
            getActivity().getWindowManager().getDefaultDisplay()
                    .getSize(size);
            Bitmap bitmap = PitcureUtils.getScaledBitmap(mPhotoFile.getPath(),size.x,size.y);
            mImageView.setImageBitmap(bitmap);
        }



        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
    }


}
