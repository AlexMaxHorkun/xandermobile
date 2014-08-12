package com.xandergorkun.XanderMobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class HelloWorldActivity extends Activity {
    public final static String EXTRA_ERROR_NAME=HelloWorldActivity.class.toString()+".EXTRA_ERROR_NAME";

    public final static String EXTRA_ERROR_TERMS=HelloWorldActivity.class.toString()+".EXTRA_ERROR_TERMS";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void sendApplyForm(View view){
        Intent receivedIntent=getIntent();
        if(receivedIntent!=null){
            boolean nameError=receivedIntent.getBooleanExtra(EXTRA_ERROR_NAME,false);
            boolean termsError=receivedIntent.getBooleanExtra(EXTRA_ERROR_TERMS,false);
            if(nameError){

            }
            if(termsError){

            }
        }

        Intent intent=new Intent(this,ApplyFormResponse.class);
        EditText name=(EditText)findViewById(R.id.name_edit);
        RadioButton agreed=(RadioButton)findViewById(R.id.terms_radio);
        intent.putExtra(ApplyFormResponse.EXTRA_NAME, name.getText().toString());
        intent.putExtra(ApplyFormResponse.EXTRA_APPLY,agreed.isChecked());
        startActivity(intent);
    }
}
