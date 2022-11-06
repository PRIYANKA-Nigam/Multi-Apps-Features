package com.example.darkmode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageButton imageButton;
  Toolbar toolbar; TextView textView;
    ArrayList<String > arrayList=new ArrayList<>();
    ArrayList<String> userSelection=new ArrayList<>();
    int counter=0;
     RecyclerAdapter adapter; String deleted=null;
    List<String> archived=new ArrayList<>();
  boolean isActionmode=false; int position=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        toolbar=findViewById(R.id.tool);setSupportActionBar(toolbar);
        textView=findViewById(R.id.tt);textView.setVisibility(View.GONE);
        recyclerView=(RecyclerView)findViewById(R.id.rec); recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        imageButton=findViewById(R.id.bt); imageButton.setVisibility(View.GONE);
        arrayList.add("i hate myself");
        arrayList.add("i hate others too");
        arrayList.add("i hate Everyone");
        arrayList.add("i hate myself");
        arrayList.add("i hate others too");
        arrayList.add("i hate Everyone");
        arrayList.add("i hate myself");
        arrayList.add("i hate others too");
        arrayList.add("i hate Everyone");
        arrayList.add("i hate myself");
        adapter=new RecyclerAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearActionMode();
            }
        });
    }

    private void clearActionMode() {
        isActionmode=false;
        textView.setVisibility(View.GONE);
        textView.setText("0 item selected");
        counter=0;userSelection.clear();
        toolbar.getMenu().clear();
        adapter.notifyDataSetChanged();
    }


    public  void check(View view ,int index){
        try{
            if (((CheckBox) view).isChecked()) {
                userSelection.add(arrayList.get(index));
                counter++;
                updateToolBarText(counter);
            } else {
                userSelection.remove(arrayList.get(index));
                counter--;
                updateToolBarText(counter);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
    public  void  startSelection(int index){
      try  {
            if (!isActionmode) {
                isActionmode = true;
                userSelection.add(arrayList.get(index));
                counter++;
                updateToolBarText(counter);
                textView.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.VISIBLE);
                toolbar.inflateMenu(R.menu.rec_delete);
                position = index;
                adapter.notifyDataSetChanged();
            }
        } catch (NullPointerException e){
          e.printStackTrace();
      }
    }
    private  void updateToolBarText(int counter) {
        if (counter==0)
            textView.setText("0 items selected");
       else if (counter==1)
            textView.setText("1 items selected");
        else
            textView.setText(counter+" Items selected");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.delete && userSelection.size()>0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Delete "+userSelection.size()+" items ?");
            builder.setTitle("Confirm");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
            for (String s : userSelection){
                arrayList.remove(s);
            }
            updateToolBarText(0);
            clearActionMode();

        }
        return super.onOptionsItemSelected(item);
    }
}