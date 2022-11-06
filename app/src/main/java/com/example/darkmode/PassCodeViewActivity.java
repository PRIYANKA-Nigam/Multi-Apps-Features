package com.example.darkmode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.hanks.passcodeview.PasscodeView;

public class PassCodeViewActivity extends AppCompatActivity {
PasscodeView passcodeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_code_view);
        passcodeView=findViewById(R.id.pass);
        passcodeView.setPasscodeLength(4).setLocalPasscode("2504").setListener(new PasscodeView.PasscodeViewListener() {
            @Override
            public void onFail() {
                Toast.makeText(getApplicationContext(),"Password is Wrong",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String number) {
                Intent intent=new Intent(PassCodeViewActivity.this,ViewPagerActivity.class);
                startActivity(intent);
            }
        });

    }
}