package com.example.deepthi.sendemail;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


public class MyActivity extends Activity {
    EditText emailid;
    EditText subject;
    EditText message;
    Button sendbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        sendbtn = (Button)findViewById(R.id.button);
        sendbtn.setOnClickListener(
            new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    emailid   = (EditText)findViewById(R.id.emailid);
                    subject   = (EditText)findViewById(R.id.subject);
                    message   = (EditText)findViewById(R.id.message);

                    System.out.print(emailid.toString());
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    // The intent does not have a URI, so declare the "text/plain" MIME type
                    emailIntent.setType("application/octet-stream");

                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailid.getText().toString()}); // recipients
                    //emailIntent.setData(Uri.parse(emailid.toString()));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                    emailIntent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());
                    //emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/at
                    PackageManager packageManager = getPackageManager();
                    List<ResolveInfo> activities = packageManager.queryIntentActivities(emailIntent, 0);
                    boolean isIntentSafe = activities.size() > 0;

                    // Start an activity if it's safe
                    if (isIntentSafe) {
                        startActivity(emailIntent);
                    }
                }

            });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
