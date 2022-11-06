package com.example.darkmode;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.airbnb.lottie.LottieAnimationView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Switch aSwitch; ImageView imageView;
    LottieAnimationView lottieAnimationView;
    private boolean pay=false;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.Theme_Dark);
        }else {
            setTheme(R.style.Theme_Light);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       lottieAnimationView=findViewById(R.id.ll);
       lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();
        lottieAnimationView.setTooltipText("Make Payment");
       lottieAnimationView.setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onClick(View view) {
               lottieAnimationView.playAnimation();
           }
       });
        imageView=findViewById(R.id.imageView);
      aSwitch=findViewById(R.id.switch1);
      aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
              if (b){
                  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
              }else {
                  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
              }
          }
      });

    }

    public void Track(View view) {
        startActivity(new Intent(getApplicationContext(),LocationActivity.class));
    }

    public void display(View view) {
        startActivity(new Intent(getApplicationContext(),GeoCodeActivity.class));

    }

    public void share(View view) {
        image();
    }
    private  void image(){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap =drawable.getBitmap();
        File file =new File(getExternalCacheDir()+"/"+getResources().getString(R.string.app_name)+".png");
        Intent intent;
        try{
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
        startActivity(Intent.createChooser(intent,"share image"));
    }

    public void GenerateQR(View view) {
Intent intent=new Intent(this,GenerateQRActivity.class);
startActivity(intent);
finish();
    }

    public void ForRoomDB(View view) {
        startActivity(new Intent(getApplicationContext(),RoomDBActivity.class));

    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }

    public void getNotified(View view) {
 startActivity(new Intent(getApplicationContext(),NotificationActivity.class));
    }

    public void GoogleCalendar(View view) {
        startActivity(new Intent(getApplicationContext(),GoogleCalendarActivity.class));
    }

    public void newCalendar(View view) {
        startActivity(new Intent(getApplicationContext(),SQLiteCalendarActivity.class));
    }

    public void MakePayment(View view) {
        startActivity(new Intent(getApplicationContext(),PaymentActivity.class));

    }

    public void recycler(View view) {
        startActivity(new Intent(getApplicationContext(),RecyclerViewActivity.class));

    }

    public void seeAdapterPosition(View view) {
        startActivity(new Intent(getApplicationContext(),MainAdapterPos.class));

    }
}