package com.example.myapplication.User;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {
    Context context;
    ArrayList<Mall> mallArrayList;


    private int image;
    //private Itemclicklistener mItemlistener;

    public MyAdapter2(Context context, ArrayList<Mall> mallArrayList) {
        this.context = context;
        this.mallArrayList = mallArrayList;


       // this.mItemlistener=itemclicklistener;
    }



    @NonNull
    @Override
    public MyAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item2,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.MyViewHolder holder, int position) {

        Mall mall= mallArrayList.get(position);

        Log.e("--TAG", "onBindViewHolder: " + mall.getNamemall() );
        Log.e("--TAG", "onBindViewHolder: " + mall.getImageUrlmall() );
        holder.name.setText(mall.getNamemall());
        Glide.with(holder.image.getContext()).load(mallArrayList.get(position).getImageUrlmall()).into(holder.image);



       /* holder.itemView.setOnClickListener(view -> {
            mItemlistener.onitemclick(cityArrayList.get(position));
        });*/



    }

    @Override
    public int getItemCount() {
        return mallArrayList.size();
    }

    public interface Itemclicklistener{
        void onitemclick(City details);
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView name;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_mall);
            image=itemView.findViewById(R.id.image2);
        }
    }

}