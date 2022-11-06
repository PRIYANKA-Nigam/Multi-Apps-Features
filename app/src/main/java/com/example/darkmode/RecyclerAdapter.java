package com.example.darkmode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private RecyclerViewActivity recyclerViewActivity;
    ArrayList<String> arrayList;
    Context context;

    public RecyclerAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        this.recyclerViewActivity=(RecyclerViewActivity)context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_items,parent,false);
        return new ViewHolder(view,recyclerViewActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
    holder.textView2.setText(arrayList.get(position));
    if (recyclerViewActivity.position == position){
        holder.checkBox.setChecked(true);
       recyclerViewActivity.position=-1;
    }else
    {
        holder.checkBox.setChecked(false);
    }
//    if (recyclerViewActivity.isActionmode){
//        Anim anim =new Anim(100,holder.linearLayout);
//        anim.setDuration(1000);
//        holder.linearLayout.setAnimation(anim);
//    }else {
//        Anim anim =new Anim(0,holder.linearLayout);
//        anim.setDuration(300);
//        holder.linearLayout.setAnimation(anim);
//        holder.checkBox.setChecked(false);
//    }
    holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
          recyclerViewActivity.startSelection(position);
            return true;
        }
    });
    holder.checkBox.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           recyclerViewActivity.check(view,position);

        }
    });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerViewActivity recyclerViewActivity;
        CheckBox checkBox;
        TextView textView2;
        LinearLayout linearLayout;
        CardView cardView;
        public ViewHolder(@NonNull View itemView,RecyclerViewActivity recyclerViewActivity) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.chh);
            linearLayout=itemView.findViewById(R.id.l);
            cardView=itemView.findViewById(R.id.cdd);
            textView2=itemView.findViewById(R.id.ttt);
            this.recyclerViewActivity=recyclerViewActivity;
        }
    }
    class Anim extends Animation {
        private  int width,startWidth;
        private View view;

        public Anim(int width, View view) {
            this.width = width;
            this.startWidth = view.getWidth();
            this.view = view;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            int newWidth=startWidth+(int)((width-startWidth)*interpolatedTime);
            view.getLayoutParams().width=newWidth;
            view.requestLayout();
            super.applyTransformation(interpolatedTime, t);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }
    }

