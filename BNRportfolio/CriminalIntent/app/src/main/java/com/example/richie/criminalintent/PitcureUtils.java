package com.example.richie.criminalintent;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Richie on 9/21/2016.
 */
public class PitcureUtils {
/*
    public static Bitmap getScaledBitmap(String path, Activity activity){
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay()
                .getSize(size);
        return getScaledBitmap(path, size.x, size.y);
    }
    */

    public static Bitmap getScaledBitmap(String path, int destWidth, int destHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        int inSampleSize = 1;
        if(srcHeight > destHeight || srcWidth > destWidth){
            if(srcWidth > srcHeight){
                inSampleSize = Math.round(srcHeight/destHeight);
            }else{
                inSampleSize = Math.round(srcWidth/destWidth);
            }
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        Bitmap scaledBitmap= BitmapFactory.decodeFile(path, options);
        ExifInterface ei;
       try{
           ei = new ExifInterface(path);
       }
       catch(IOException e){
           Log.d("ExitInterface",  " Exit Interface didn't work" + e);
           return null;
       }
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        switch(orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
               return rotateImage(scaledBitmap, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(scaledBitmap, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(scaledBitmap, 270);
            case ExifInterface.ORIENTATION_NORMAL:
            default: return scaledBitmap;
        }


    }
    private static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix,
                true);
    }


}
