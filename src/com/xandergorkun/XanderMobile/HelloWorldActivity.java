package com.xandergorkun.XanderMobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class HelloWorldActivity extends Activity {
    public final static String EXTRA_ERROR_NAME = HelloWorldActivity.class.toString() + ".EXTRA_ERROR_NAME";

    public final static String EXTRA_ERROR_TERMS = HelloWorldActivity.class.toString() + ".EXTRA_ERROR_TERMS";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent receivedIntent = getIntent();
        boolean nameError = false;
        boolean termsError = false;
        if (receivedIntent != null) {
            nameError = receivedIntent.getBooleanExtra(EXTRA_ERROR_NAME, false);
            termsError = receivedIntent.getBooleanExtra(EXTRA_ERROR_TERMS, false);
        }
        if (!nameError) {
            findViewById(R.id.name_error).setVisibility(View.GONE);
        }
        if (!termsError) {
            findViewById(R.id.terms_error).setVisibility(View.GONE);
        }
    }

    public void sendApplyForm(View view) {
        Intent intent = new Intent(this, ApplyFormResponse.class);
        EditText name = (EditText) findViewById(R.id.name_edit);
        RadioButton agreed = (RadioButton) findViewById(R.id.terms_radio);
        intent.putExtra(ApplyFormResponse.EXTRA_NAME, name.getText().toString());
        intent.putExtra(ApplyFormResponse.EXTRA_APPLY, agreed.isChecked());
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
