package com.example.darkmode;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class AlarmTriggerService extends Service {
    private MediaPlayer mediaPlayer;
    private final static String TAG_ALARM_TRIGGER_SERVICE = "ALARM_TRIGGER_SERVICE";

    public AlarmTriggerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        String message = "Alarm trigger this service. This is service onCreate() method.";
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        Log.d(TAG_ALARM_TRIGGER_SERVICE, message);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String alarmType = intent.getStringExtra(NotificationActivity.ALARM_TYPE);
        String alarmDescription = intent.getStringExtra(NotificationActivity.ALARM_DESCRIPTION);
        alarmDescription = alarmDescription + ", this is service onStartCommand() method/";
//        mediaPlayer=MediaPlayer.create(this,R.raw.sample3);
//        mediaPlayer.start();
        Toast.makeText(getApplicationContext(), alarmDescription, Toast.LENGTH_LONG).show();

        Log.d(TAG_ALARM_TRIGGER_SERVICE, alarmDescription);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mediaPlayer.stop();
    }
}