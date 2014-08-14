package com.xandergorkun.XanderMobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;

public class CreateNewFileActivity extends Activity {
    public static final String EXTRA_NAME = CreateNewFileActivity.class.toString() + ".EXTRA_NAME";

    public static final String EXTRA_JUST_REGISTERED = CreateNewFileActivity.class.toString() + ".EXTRA_JUST_REGISTERED";

    public static final String FILES_DIR = Environment.DIRECTORY_DOWNLOADS;

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
        TextView filePathHeaderWidget = (TextView) findViewById(R.id.new_file_path_header);
        filePathHeaderWidget.setText(filePathHeaderWidget.getText().toString().replace(":path", FILES_DIR));
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

    protected void writeNewFile(String content, String filename) throws IOException {
        File file = new File(Environment.getExternalStoragePublicDirectory(FILES_DIR), filename);
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.flush();
            writer.close();
        } else {
            Log.e("BULLSHIT.ANDROID", "Directory not created");
            throw new IOException();
        }
    }

    public void onCreateButtonClick(View button) {
        EditText fileContentWidget = (EditText) findViewById(R.id.new_file_edit);
        EditText filePathWidget = (EditText) findViewById(R.id.new_file_path_edit);
        String content = fileContentWidget.getText().toString();
        String path = filePathWidget.getText().toString();
        boolean error = false;
        if (!content.isEmpty() && !path.isEmpty()) {
            try {
                writeNewFile(content, path);
            } catch (IOException e) {
                AlertDialog.Builder fileReadErrorDialog = new AlertDialog.Builder(this);
                fileReadErrorDialog.setTitle(R.string.file_write_error);
                fileReadErrorDialog.setMessage(R.string.new_file_error);
                fileReadErrorDialog.setCancelable(false);
                fileReadErrorDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                fileReadErrorDialog.create().show();
                error = true;
            }
            if (!error) {
                finish();
            }
        }
    }
}