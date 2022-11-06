package com.example.darkmode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RoomDBActivity extends AppCompatActivity {
EditText e;
Button b1,b2;
RecyclerView recyclerView;
List<MainData> list=new ArrayList<>();
LinearLayoutManager layoutManager; RoomDB roomDB;
MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_d_b);
        e=findViewById(R.id.editTextTextPersonName3);
        b1=findViewById(R.id.button7);
        b2=findViewById(R.id.button8);
        recyclerView=findViewById(R.id.rec);

         roomDB =RoomDB.getInstance(this);
         list =roomDB.mainDao().getAll();
        layoutManager=new LinearLayoutManager(this);
         recyclerView.setLayoutManager(layoutManager);
         adapter=new MainAdapter(list,RoomDBActivity.this);
         recyclerView.setAdapter(adapter);
         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String s=e.getText().toString().trim();
                 if (!s.equals(" ")){
//                     MainData data =new MainData("food");
//                     data.setText(s);
                     roomDB.mainDao().insert(new MainData(s));
                     e.setText(" ");
                     list.clear();
                     list.addAll(roomDB.mainDao().getAll());
                     adapter.notifyDataSetChanged();

                 }
                 Toast.makeText(getApplicationContext(),"saved ........",Toast.LENGTH_SHORT).show();
             }
         });
         b2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 roomDB.mainDao().reset(list);
                 list.clear();
                 list.addAll(roomDB.mainDao().getAll());
                 adapter.notifyDataSetChanged();
                 Toast.makeText(getApplicationContext(),"Received ........",Toast.LENGTH_SHORT).show();
             }
         });
    }
}