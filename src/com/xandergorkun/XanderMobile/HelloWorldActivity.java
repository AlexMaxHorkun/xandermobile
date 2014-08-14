package com.xandergorkun.XanderMobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class HelloWorldActivity extends Activity {
    public final static String EXTRA_ERROR_NAME = HelloWorldActivity.class.toString() + ".EXTRA_ERROR_NAME";

    public final static String EXTRA_ERROR_TERMS = HelloWorldActivity.class.toString() + ".EXTRA_ERROR_TERMS";

    public final static String EXTRA_NAME = HelloWorldActivity.class.toString() + ".APPLY_NAME";

    public final static String EXTRA_APPLY = HelloWorldActivity.class.toString() + ".APPLY_AGREED";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.clear_data:
                clearFormFields();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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

    public void clearFormFields() {
        EditText nameWidget = (EditText) findViewById(R.id.name_edit);
        nameWidget.setText(null);
        RadioButton agreedWidget = (RadioButton) findViewById(R.id.terms_radio);
        agreedWidget.setChecked(false);
    }

    @Override
    public void onResume() {
        super.onResume();
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
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        String name = null;
        Boolean agreed = null;
        if (receivedIntent.hasExtra(EXTRA_NAME)) {
            name = receivedIntent.getStringExtra(EXTRA_NAME);
        } else if (preferences.contains(getString(R.string.helloworld_preferences_name))) {
            name = preferences.getString(getString(R.string.helloworld_preferences_name), null);
        }
        if (receivedIntent.hasExtra(EXTRA_APPLY)) {
            agreed = receivedIntent.getBooleanExtra(EXTRA_APPLY, false);
        } else if (preferences.contains(getString(R.string.helloworld_preferences_agreed))) {
            agreed = preferences.getBoolean(getString(R.string.helloworld_preferences_agreed), false);
        }
        if (name != null && !name.isEmpty()) {
            EditText nameWidget = (EditText) findViewById(R.id.name_edit);
            nameWidget.setText(name);
        }
        if (agreed != null) {
            RadioButton agreedWidget = (RadioButton) findViewById(R.id.terms_radio);
            agreedWidget.setChecked(agreed);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putString(getString(R.string.helloworld_preferences_name), ((EditText) findViewById(R.id.name_edit)).getText().toString());
        preferencesEditor.putBoolean(getString(R.string.helloworld_preferences_agreed), ((RadioButton) findViewById(R.id.terms_radio)).isChecked());
        preferencesEditor.commit();
    }
}
