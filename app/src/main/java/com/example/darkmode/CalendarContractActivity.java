package com.example.darkmode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class CalendarContractActivity extends AppCompatActivity {
    boolean isRunning = false;  private List<Alarm> alarmList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_contract);
        final Alarm selectedAlarm = alarmList.get(0);
        Intent intent = getIntent();
        String st = intent.getExtras().getString("extra");
        if (st.equals("on") && !isRunning) {
            Intent intent1 = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
            final Calendar calendar = Calendar.getInstance();
            Calendar begin = Calendar.getInstance();
            begin.set(2022, 0, 1, selectedAlarm.getHour(), selectedAlarm.getMin());
            Calendar end = Calendar.getInstance();
            end.set(2022, 0, 1, selectedAlarm.getHour(), selectedAlarm.getMin() + 30);
            intent1.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin.getTime());
            intent1.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end.getTimeInMillis());
            intent1.putExtra(CalendarContract.Events.TITLE, "Android Classes ");
            intent1.putExtra(CalendarContract.Events.EVENT_LOCATION, "Android Classes Venue");
            Toast.makeText(this, "You will be notified for the events in time", Toast.LENGTH_LONG).show();
            startActivity(intent1);
            SQLiteCalendarActivity.activeAlarm = intent.getExtras().getString("active");
        } else if (st.equals("off")) {
            SQLiteCalendarActivity.activeAlarm = "";
        }

    }
}