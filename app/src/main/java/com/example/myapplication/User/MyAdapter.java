package com.example.myapplication.User;

import android.content.Context;
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
        ArrayList<City>cityArrayListlist;
    private int image;

    public MyAdapter(Context context, ArrayList<City> cityArrayListlist) {
        this.context = context;
        this.cityArrayListlist = cityArrayListlist;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        City city=cityArrayListlist.get(position);

        holder.name.setText(city.getAmaan());
        Glide.with(context).load(cityArrayListlist.get(position).getImageUrl()).into(holder.image);




    }

    @Override
    public int getItemCount() {
        return cityArrayListlist.size();
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
