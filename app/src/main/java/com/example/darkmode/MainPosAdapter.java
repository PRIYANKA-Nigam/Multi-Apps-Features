package com.example.darkmode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainPosAdapter extends RecyclerView.Adapter<MainPosAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<MainModel> arrayList;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        public void onClick(int adapterPosition);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    public MainPosAdapter(Context context, ArrayList<MainModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new MyViewHolder(v,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
 holder.t1.setText(arrayList.get(position).getName());
 holder.t2.setText(arrayList.get(position).getPos());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
TextView t1,t2;
        public MyViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            t1=itemView.findViewById(R.id.tt);
            t2=itemView.findViewById(R.id.tt2);
          itemView.setOnClickListener(view -> onItemClickListener.onClick(getAdapterPosition()));
        }
    }
}
