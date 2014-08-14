package com.xandergorkun.XanderMobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProvideExtraDataActivity extends Activity {
    public static final String EXTRA_NAME = ProvideExtraDataActivity.class.toString() + ".EXTRA_NAME";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provide_extra_data);
        Intent receivedIntent = getIntent();
        String name = receivedIntent.getStringExtra(EXTRA_NAME);
        TextView header = (TextView) findViewById(R.id.provide_header);
        header.setText(getString(R.string.provide_page_header).replace(":name", name));
    }

    protected boolean writeContactInfo(String data) {
        return false;
    }

    public void onSendClick(View view) {
        EditText editText = (EditText) findViewById(R.id.contact_info);
        String data = editText.getText().toString();
        TextView errorWidget = (TextView) findViewById(R.id.contact_info_error);
        boolean error = false;
        if (!data.isEmpty()) {
            if (writeContactInfo(data)) {

            } else {
                errorWidget.setText(R.string.error_write_to_file);
                error = true;
            }
        } else {
            errorWidget.setText(R.string.edit_error_empty);
            error = true;
        }
        ViewGroup.LayoutParams layoutParams = errorWidget.getLayoutParams();
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
    }
}