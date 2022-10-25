package com.example.myapplication.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.MyViewHolder> {

    Context context;
   ArrayList<SpacePark> spaceArrayList;

   // private Itemclicklistener xItemlistener;


    public MyAdapter3(Context context, ArrayList<SpacePark> spaceArrayList) {
        this.context = context;
        this.spaceArrayList = spaceArrayList;
       // this.xItemlistener=xItemlistener;
    }


    @NonNull
    @Override
    public MyAdapter3.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item3,parent,false);

        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyAdapter3.MyViewHolder holder, int position) {

        SpacePark spacePark= spaceArrayList.get(position);


        holder.number.setText(spacePark.getId());
        holder.status.setText(String.valueOf(spacePark.getStatus()));

     /*  holder.itemView.setOnClickListener(view -> {
           xItemlistener.onitemclick(spaceArrayList.get(position));
        });
*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), ""+spacePark.getStatus(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return spaceArrayList.size();
    }







    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView number , status;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            number=itemView.findViewById(R.id.text_space);
            status=itemView.findViewById(R.id.avalbvle);

        }
    }
}
