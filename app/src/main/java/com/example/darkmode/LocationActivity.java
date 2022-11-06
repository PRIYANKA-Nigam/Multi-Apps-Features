package com.example.darkmode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LocationActivity extends AppCompatActivity {
EditText e1,e2;
Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        b=findViewById(R.id.button);
        e1=findViewById(R.id.editTextTextPersonName);
        e2=findViewById(R.id.editTextTextPersonName2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=e1.getText().toString().trim();
                String d=e2.getText().toString().trim();
                if (s.equals("")||d.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter Both Location",Toast.LENGTH_SHORT).show();
                }else {
                    displayTrack(s,d);
                }

            }
        });
    }

    private void displayTrack(String s, String d) {
        try {
            Uri uri =Uri.parse("https://www.google.co.in/maps/dir/"+s+"/"+d);
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);;
        }catch(ActivityNotFoundException e){
            Uri uri =Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}