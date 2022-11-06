package com.example.darkmode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class AlarmTriggerActivity extends AppCompatActivity {
    private final static String TAG_ALARM_TRIGGER_ACTIVITY = "ALARM_TRIGGER_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_trigger);

        setTitle("dev2qa.com - Alarm Triggered Activity.");

        // Get intent that trigger this activity.
        Intent intent = getIntent();

        // Get alarm type.
        String alarmType = intent.getStringExtra(NotificationActivity.ALARM_TYPE);

        // Get alarm description string.
        String alarmDescription = intent.getStringExtra(NotificationActivity.ALARM_DESCRIPTION);

        TextView textView = (TextView)findViewById(R.id.alarm_trigger_activity_text_view);
        textView.setText(alarmDescription);

        Log.d(TAG_ALARM_TRIGGER_ACTIVITY, alarmDescription);

    }
}