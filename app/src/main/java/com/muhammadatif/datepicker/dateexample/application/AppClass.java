package com.muhammadatif.datepicker.dateexample.application;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.graphics.Typeface;

/**
 * Created by Atif Arif on 10/7/2017.
 */

public class AppClass extends Application {
    public static Typeface fontArialRegular = null;
    public static Typeface fontArialBold = null;



    public static void initializeCustomFonts(AssetManager assetManager) {
        try {
            fontArialBold = Typeface.createFromAsset(assetManager, "fonts/arial_bold.ttf");
            fontArialRegular = Typeface.createFromAsset(assetManager, "fonts/arial_regular.TTF");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
