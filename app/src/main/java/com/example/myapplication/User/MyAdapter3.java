package com.example.myapplication.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.MyViewHolder> {
    private  final   Context context;
    private  final List<SpacePark> space;

    public MyAdapter3(Context context, List<SpacePark> space) {
        this.context = context;
        this.space = space;
    }


    @NonNull
    @Override
    public MyAdapter3.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item3,parent,false);

        return new MyAdapter3.MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyAdapter3.MyViewHolder holder, int position) {

        SpacePark spacePark= space.get(position);


        holder.number.setText(spacePark.getId());

    }
    @Override
    public int getItemCount() {
        return space.size();
    }

  /*  public interface Itemclicklistener{
        void onitemclick(City details);
    }
*/
    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView number;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            number=itemView.findViewById(R.id.text_space);

        }
    }
}
