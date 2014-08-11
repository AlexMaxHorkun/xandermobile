package com.xandergorkun.XanderMobile;

import android.app.Activity;
import android.os.Bundle;


public class ApplyFormResponse extends Activity {
    public final static String EXTRA_NAME=ApplyFormResponse.class.toString()+"APPLY_NAME";

    public final static String EXTRA_APPLY=ApplyFormResponse.class.toString()+"APPLY_AGREED";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_form_response);
    }
}