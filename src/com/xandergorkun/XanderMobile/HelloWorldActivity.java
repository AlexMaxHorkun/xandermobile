package com.xandergorkun.XanderMobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class HelloWorldActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void sendApplyForm(View view){
        Intent intent=new Intent(this,ApplyFormResponse.class);
        EditText name=(EditText)view.findViewById(R.id.name_edit);
        RadioButton agreed=(RadioButton)view.findViewById(R.id.terms_radio);
        intent.putExtra(ApplyFormResponse.EXTRA_NAME, name.getText().toString());
        intent.putExtra(ApplyFormResponse.EXTRA_APPLY,agreed.isChecked());
        startActivity(intent);
    }
}
