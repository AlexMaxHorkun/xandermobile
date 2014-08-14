package com.xandergorkun.XanderMobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.*;

public class CreateNewFileActivity extends Activity {
    public static final String EXTRA_NAME = CreateNewFileActivity.class.toString() + ".EXTRA_NAME";

    public static final String EXTRA_JUST_REGISTERED = CreateNewFileActivity.class.toString() + ".EXTRA_JUST_REGISTERED";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_file_layout);
        findViewById(R.id.just_registered_header).setVisibility(View.INVISIBLE);
        TextView contactInfoWidget = (TextView) findViewById(R.id.contact_info_read);
        String contactInfo = null;
        try {
            contactInfo = readContactInfo();
            contactInfoWidget.setText(contactInfo);
        } catch (Exception ex) {
            AlertDialog.Builder fileReadErrorDialog = new AlertDialog.Builder(this);
            fileReadErrorDialog.setTitle(R.string.file_read_error);
            fileReadErrorDialog.setMessage(R.string.contact_info_error);
            fileReadErrorDialog.setCancelable(false);
            fileReadErrorDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            fileReadErrorDialog.create().show();
            findViewById(R.id.contact_info_header).setVisibility(View.INVISIBLE);
            findViewById(R.id.contact_info_read).setVisibility(View.INVISIBLE);
        }

    }

    public void onResume() {
        super.onResume();
        boolean justRegistered = getIntent().getBooleanExtra(EXTRA_JUST_REGISTERED, false);
        if (justRegistered) {
            findViewById(R.id.just_registered_header).setVisibility(View.VISIBLE);
        }
    }

    public String readContactInfo() throws FileNotFoundException, IOException {
        File contactFile = new File(getFilesDir(), ProvideExtraDataActivity.CONTACT_INFO_FILENAME);
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(contactFile)));
        StringBuilder info = new StringBuilder();
        String temp = null;
        while ((temp = input.readLine()) != null) {
            info.append(temp);
        }
        input.close();
        return info.toString();
    }
}