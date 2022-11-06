package com.example.darkmode;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{
private List<MainData> list;
private Activity context;
private RoomDB db;

    public MainAdapter(List<MainData> list, Activity context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
MainData data =list.get(position);
db=RoomDB.getInstance(context);
holder.textView.setText(data.getText());
holder.i1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        MainData d=list.get(holder.getAdapterPosition());
        int sId=d.getId();
        String sText =d.getText();
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_update);
        int width = WindowManager.LayoutParams.MATCH_PARENT;
        int height =WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width,height);
        dialog.show();
        EditText editText=dialog.findViewById(R.id.edd);
        Button button=dialog.findViewById(R.id.btt);
        editText.setText(sText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String uText=editText.getText().toString().trim();
                db.mainDao().update(sId,uText);
                list.clear();
                list.addAll(db.mainDao().getAll());
                notifyDataSetChanged();
            }
        });
    }
});
holder.i2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        MainData d=list.get(holder.getAdapterPosition());
        db.mainDao().delete(d);
        int pos=holder.getAdapterPosition();
        list.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeChanged(pos,list.size());
    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
TextView textView; ImageView i1,i2;
CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView3);
            cardView=itemView.findViewById(R.id.cd);
            i1=itemView.findViewById(R.id.imageView3);
            i2=itemView.findViewById(R.id.imageView4);
        }
    }
}
