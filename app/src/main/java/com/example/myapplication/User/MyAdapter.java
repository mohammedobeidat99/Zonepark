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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        Context context;
        ArrayList<City> cityArrayList;
    private int image;

    public MyAdapter(Context context, ArrayList<City> cityArrayList) {
        this.context = context;
        this.cityArrayList = cityArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        City city= cityArrayList.get(position);
        Log.e("--TAG", "onBindViewHolder: " + city.getName() );
        Log.e("--TAG", "onBindViewHolder: " + city.getImageUrl() );
        holder.name.setText(city.getName());
        Glide.with(holder.image.getContext()).load(cityArrayList.get(position).getImageUrl()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return cityArrayList.size();
    }
    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView name;
      ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tvname);
           image=itemView.findViewById(R.id.imageitem);
        }
    }

}
