package com.example.darkmode;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private List<Alarm> alarmList;
    private LayoutInflater layoutInflater;

    public CustomAdapter(Context context, List<Alarm> alarmList) {
        this.context = context;
        this.alarmList = alarmList;
        layoutInflater =(LayoutInflater.from(this.context));
    }

    @Override
    public int getCount() {
        return alarmList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=layoutInflater.inflate(R.layout.time_row,null);
        final Alarm selectedAlarm = alarmList.get(i);
        final TextView textView1 =view.findViewById(R.id.textView5);
        final TextView textView2 =view.findViewById(R.id.textView6);
        final AlarmManager alarmManager =(AlarmManager) context.getSystemService(ALARM_SERVICE);
        textView1.setText(selectedAlarm.getName());
        textView2.setText(selectedAlarm.toString());
     //   Intent intent=new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
        final Intent serviceIntent =new Intent(context,CalendarContractActivity.class);
        Calendar begin = Calendar.getInstance();
        begin.set(2022,0,1,selectedAlarm.getHour(),selectedAlarm.getMin());
        Calendar end= Calendar.getInstance();
        end.set(2022,0,1,selectedAlarm.getHour(),selectedAlarm.getMin()+15);
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
//        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin.getTime());
//        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end.getTimeInMillis());
//        intent.putExtra(CalendarContract.Events.TITLE, "Android Classes ");
//        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Android Classes Venue");
//        Toast.makeText(context, "You will be notified for the events in time", Toast.LENGTH_LONG).show();
//        if (selectedAlarm.getStatus()){
//          intent.putExtra("extra","on");
//            context.startActivity(intent);
//           SQLiteCalendarActivity.activeAlarm=intent.getExtras().getString("active");
//        }else {
//                Toast.makeText(context,"Reminder is turned off",Toast.LENGTH_SHORT).show();
//               SQLiteCalendarActivity.activeAlarm = "";
//            }


        ToggleButton toggleButton =view.findViewById(R.id.toggleButton);
        toggleButton.setChecked(selectedAlarm.getStatus());
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                selectedAlarm.setStatus(isChecked);
                DataBaseHelper db =new DataBaseHelper(context);
                db.updateAlarm(selectedAlarm);
               SQLiteCalendarActivity.alarmList.clear();
                List<Alarm> list =db.getAllAlarms();
                SQLiteCalendarActivity.alarmList.addAll(list);
                notifyDataSetChanged();
                if (!isChecked && selectedAlarm.toString().equals(SQLiteCalendarActivity.activeAlarm)){
                    serviceIntent.putExtra("extra","off");
                    context.startActivity(serviceIntent);
                // intent.putExtra("extra","off");
//                    PendingIntent pendingIntent =PendingIntent.getBroadcast(context,i,serviceIntent,PendingIntent.FLAG_UPDATE_CURRENT);
//                    alarmManager.cancel(pendingIntent);
//                    context.sendBroadcast(serviceIntent);
                }
            }
        });
        LinearLayout linearLayout=view.findViewById(R.id.lin);
        linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // selectedAlarm.setStatus(isChecked);
                DataBaseHelper db =new DataBaseHelper(context);
                db.deleteAlarm(selectedAlarm);
               SQLiteCalendarActivity.alarmList.remove(i);
                notifyDataSetChanged();
                Toast.makeText(context,"Alarm deleted !!!!!!!",Toast.LENGTH_LONG).show();
                return true;
            }
        });
//        if (selectedAlarm.getStatus()){
//            intent.putExtra("extra","on");
//          intent.putExtra("active",selectedAlarm.toString());
////            PendingIntent pendingIntent =PendingIntent.getBroadcast(context,i,serviceIntent,PendingIntent.FLAG_UPDATE_CURRENT);
////            // alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
////            //AlarmManager.ELAPSED_REALTIME: Alarm can not be executed when the android device is sleep.
////            // This alarm use relative time
////            alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
//            // alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
//            //AlarmManager.RTC_WAKEUP: Similar to AlarmManager.RTC,
//            // the difference is this alarm is running even android os sleep,
//            // it will wake up the android device when the alarm run time is coming.
//        }
        if (selectedAlarm.getStatus()) {
            serviceIntent.putExtra("extra", "on");
            serviceIntent.putExtra("active", selectedAlarm.toString());
        }
        return view;
    }

}
