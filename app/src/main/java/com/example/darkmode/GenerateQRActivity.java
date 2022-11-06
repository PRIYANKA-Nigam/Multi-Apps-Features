package com.example.darkmode;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenerateQRActivity extends AppCompatActivity {
TextView textView;
ImageView imageView;
TextInputEditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_q_r);
        textView=findViewById(R.id.tt);
        imageView=findViewById(R.id.img);
        editText=findViewById(R.id.edd);
    }

    public void generate(View view) {
        String data=editText.getText().toString().trim();
        if (data.isEmpty()){
            Toast.makeText(this,"Please enter data",Toast.LENGTH_SHORT).show();
        }else {
            MultiFormatWriter writer = new MultiFormatWriter();
            try{
                BitMatrix matrix =writer.encode(data, BarcodeFormat.QR_CODE,350,350);
                BarcodeEncoder encoder =new BarcodeEncoder();
                Bitmap bitmap =encoder.createBitmap(matrix);
                imageView.setImageBitmap(bitmap);
                InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                methodManager.hideSoftInputFromWindow(editText.getApplicationWindowToken(),0);
            }catch (WriterException e){
                e.printStackTrace();
            }
//            WindowManager windowManager= (WindowManager) getSystemService(WINDOW_SERVICE);
//            Display display =windowManager.getDefaultDisplay();
//            Point point =new Point();
//            display.getSize(point);
//            int width=point.x;
//            int height=point.y;
//            int dimen=width<height ?width:height;
//            dimen=dimen *3/4;
//            qrgEncoder=new QRGEncoder(data,null, QRGContents.Type.TEXT,dimen);
////           try {
////                bitmap = qrgEncoder.en
////                imageView.setVisibility(View.GONE);
////                imageView.setImageBitmap(bitmap);
////            }catch (WriterException e){
////               e.printStackTrace();
////           }
//            try {
//             Bitmap  bitmap=qrgEncoder.en
//            }catch (WriterException e){
//                e.printStackTrace();
//            }
        }
    }
}