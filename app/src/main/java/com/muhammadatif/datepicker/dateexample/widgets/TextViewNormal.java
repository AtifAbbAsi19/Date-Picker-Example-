package com.muhammadatif.datepicker.dateexample.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.muhammadatif.datepicker.dateexample.application.AppClass;

/**
 * Created by Atif Arif on 10/7/2017.
 */

public class TextViewNormal extends android.support.v7.widget.AppCompatTextView {
    public TextViewNormal(Context context) {
        super(context);
    }

    public TextViewNormal(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewNormal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(AppClass.fontArialRegular);
    }

}
