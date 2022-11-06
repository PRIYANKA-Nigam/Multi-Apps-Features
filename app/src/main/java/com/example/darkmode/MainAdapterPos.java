package com.example.darkmode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.remote.EspressoRemoteMessage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;

public class MainAdapterPos extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<MainModel> arrayList;
MainPosAdapter adapterPos;
RecyclerView.LayoutManager layoutManager;
Button button,button2;
EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_adapter_pos);
       load();
        buildRec();

        button=findViewById(R.id.button12);
        button2=findViewById(R.id.button13);
      //  arrayList=new ArrayList<>();  insert button was not working when this line was uncommented ........
        e1=findViewById(R.id.editTextTextPersonName5);
        e2=findViewById(R.id.editTextTextEmailAddress);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                arrayList.add(new MainModel(s1,s2));
                adapterPos.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"inserted",Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                save();
            }
        });
     //   recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));


//        arrayList.add(new MainModel("a","b"));
//        arrayList.add(new MainModel("c","d"));
//        arrayList.add(new MainModel("e","f"));
//        arrayList.add(new MainModel("g","h"));
//        arrayList.add(new MainModel("i","j"));
//        adapterPos=new MainPosAdapter(this,arrayList);
//
// recyclerView.setAdapter(adapterPos);
adapterPos.setOnItemClickListener(pos->delete(pos));

    }

    private void buildRec() {
        recyclerView=findViewById(R.id.rec);
        DividerItemDecoration itemDecoration=new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.divider));
        recyclerView.addItemDecoration(itemDecoration);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapterPos=new MainPosAdapter(MainAdapterPos.this,arrayList);
        recyclerView.setAdapter(adapterPos);
    }

    private void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("delete", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("done", null);
        Type type = new TypeToken<ArrayList<MainModel>>() {
        }.getType();
        arrayList = gson.fromJson(json, type);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
//            adapterPos = new MainPosAdapter(this, arrayList);
//            adapterPos.notifyDataSetChanged();
        }
    }

    private void save() {
        SharedPreferences sharedPreferences=getSharedPreferences("delete",Context.MODE_PRIVATE);
        SharedPreferences.Editor sh=sharedPreferences.edit();
        Gson gson=new Gson();
        String json =gson.toJson(arrayList);
        sh.putString("done",json);
        sh.apply();
        Toast.makeText(getApplicationContext(),"Saved!", Toast.LENGTH_SHORT).show();

    }

    private void delete(int pos) {
        arrayList.remove(pos);
        adapterPos.notifyDataSetChanged();
        SharedPreferences sharedPreferences= getSharedPreferences("delete",MODE_PRIVATE);
        SharedPreferences.Editor sh=sharedPreferences.edit();
       Gson gson=new Gson();
      String json =gson.toJson(arrayList);
      sh.putString("done",json);
      sh.apply();
      Toast.makeText(getApplicationContext(),"deleted!", Toast.LENGTH_SHORT).show();
    }
}