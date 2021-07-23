package com.example.srvideocall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class Dashboard extends AppCompatActivity {
       EditText codebox;
       Button joinbtn,sharebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getSupportActionBar().hide();

        setContentView(R.layout.activity_dashboard);
        codebox = findViewById(R.id.codebox);
        joinbtn = findViewById(R.id.joinbtn);
        sharebtn = findViewById(R.id.sharebtn);
        URL serverURL;


            try {
                serverURL = new URL("https://meet.jit.si");
                JitsiMeetConferenceOptions defaultoptions = new JitsiMeetConferenceOptions.Builder()
                        .setServerURL(serverURL).setWelcomePageEnabled(false).build();


                JitsiMeet.setDefaultConferenceOptions(defaultoptions);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


      //  JitsiMeetConferenceOptions defaultoptions= new JitsiMeetConferenceOptions.Builder()
//                  .setServerURL(serverURL).setWelcomePageEnabled(false).build();

        //JitsiMeet.setDefaultConferenceOptions(defaultoptions);

        joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(codebox.getText().toString()).setWelcomePageEnabled(false)
                        .build();


                JitsiMeetActivity.launch(Dashboard.this, options);
            }
        });


    }
}