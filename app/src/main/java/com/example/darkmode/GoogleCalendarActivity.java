package com.example.darkmode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class GoogleCalendarActivity extends AppCompatActivity {
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_calendar);
        button=findViewById(R.id.button10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int y =2022,m=7,d=8;
                String rem="rem";
                for (int i=0;i<3;i++){
                    rem+=""+i+1;
                    addReminder(y,m,d,9,30);m++;
                    Toast.makeText(getApplicationContext(),"Event "+(i+1)+" date= "+d+"/"+m+"/"+y,Toast.LENGTH_LONG).show();
                }
//                Intent intent=new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
//                Calendar begin = Calendar.getInstance();
//                begin.set(2022,0,1,9,30);
//                Calendar end= Calendar.getInstance();
//                end.set(2022,0,1,10,30);
//        begin.set(y,m,d,9,30);
//        Calendar end= Calendar.getInstance();
//        for (int i=1;i<=12;i++) {
//            d=d+(28*i);
//            if (d>30 ||d>31) {
//                m = m + 1; d=1;
//            }
//            if (m>11)
//                m=0;
//            end.set(y, m, d + (28*i), 10, 30);
//                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin.getTime());
//                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end.getTimeInMillis());
//                intent.putExtra(CalendarContract.Events.TITLE, "Android Classes ");
//                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Android Classes Venue");
//                Toast.makeText(this, "You will be notified for the events in time", Toast.LENGTH_LONG).show();
//                startActivity(intent);
            }
        });
    }

    private void addReminder(int y, int m, int d, int i, int i1) {
        long callId=3; long startMillis=0,endMillis=0;
        Calendar begin = Calendar.getInstance();
        begin.set(y,m,d,9,30); startMillis=begin.getTimeInMillis();
        Calendar end = Calendar.getInstance();
        end.set(y,m,d+28,9,30); endMillis=begin.getTimeInMillis();
        Intent intent=new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin.getTime());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end.getTimeInMillis());
                intent.putExtra(CalendarContract.Events.TITLE, "Android Classes ");
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Android Classes Venue");
                Toast.makeText(this, "You will be notified for the events in time", Toast.LENGTH_LONG).show();
                startActivity(intent);
//        ContentResolver cr =getContentResolver();
//        ContentValues cv =new ContentValues();
//        cv.put(CalendarContract.Events.DTSTART,startMillis);
//        cv.put(CalendarContract.Events.DTEND,endMillis);
//        cv.put(CalendarContract.Events.TITLE,"Monthly Events");
//        cv.put(CalendarContract.Events.DESCRIPTION,"Monthly Errands");
//        cv.put(CalendarContract.Events.CALENDAR_ID,callId);
//        Uri uri =cr.insert(CalendarContract.Events.CONTENT_URI,cv);
    }
}