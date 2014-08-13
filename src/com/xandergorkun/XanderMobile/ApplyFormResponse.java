package com.xandergorkun.XanderMobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class ApplyFormResponse extends Activity {
    public final static String EXTRA_NAME = ApplyFormResponse.class.toString() + ".APPLY_NAME";

    public final static String EXTRA_APPLY = ApplyFormResponse.class.toString() + ".APPLY_AGREED";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_form_response);

    }

    @Override
    public void onResume(){
        super.onResume();
        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_NAME);
        boolean agreed = intent.getBooleanExtra(EXTRA_APPLY, false);
        if (!agreed || name.isEmpty()) {
            Intent errorIntent = new Intent(this, HelloWorldActivity.class);
            if (!agreed) {
                errorIntent.putExtra(HelloWorldActivity.EXTRA_ERROR_TERMS, true);
            }
            if (name.isEmpty()) {
                errorIntent.putExtra(HelloWorldActivity.EXTRA_ERROR_NAME, true);
            }
            errorIntent.putExtra(HelloWorldActivity.EXTRA_NAME, name);
            errorIntent.putExtra(HelloWorldActivity.EXTRA_APPLY, agreed);
            startActivity(errorIntent);
        } else {
            TextView userNameWidget = (TextView) findViewById(R.id.user_name);
            userNameWidget.setText(name);
        }
    }
}