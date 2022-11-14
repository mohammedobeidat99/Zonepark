package com.example.myapplication.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Scan_page;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.MyViewHolder> {

    private DatabaseReference database= FirebaseDatabase.getInstance().getReference();

    static Context context;
   ArrayList<SpacePark> spaceArrayList;
   Intent intent;

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
       holder.status.setText(spacePark.getStatus());



       if(holder.status.getText().equals("true")){
            holder.status.setText(".");
            holder.status.setTextColor(Color.GREEN);
        }else if(holder.status.getText().equals("false")){
            holder.status.setText(".");
        holder.status.setTextColor(Color.RED);}


       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               View modelBottomSheet = LayoutInflater.from(context).inflate(R.layout.item_scan, null);
               BottomSheetDialog dialog = new BottomSheetDialog(context);
               dialog.setContentView(modelBottomSheet);
               dialog.show();

               TextView numpark=modelBottomSheet.findViewById(R.id.NumberSpace);
               numpark.setText("Space "+spacePark.getId());
               Button  scan=modelBottomSheet.findViewById(R.id.button);



               TextView spark=modelBottomSheet.findViewById(R.id.st);

               Intent intent = ((Activity) context).getIntent();
               String m=intent.getStringExtra("mall");
              // ((Activity) context).finish();
               if(spacePark.getStatus().equals("true")){
               spark.setText("Parking number "+ spacePark.getId() +" available you can stop,Please scan QR code. ");
               scan.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Intent intent = new Intent(context, Scan_page.class);
                       intent.putExtra("num",spacePark.getId());
                       intent.putExtra("s1",spacePark.getStatus());
                      // intent.putExtra("mall",m);
                      // context.startActivity(context, Scan_page.class);
                       context.startActivity(intent);


                   }
               });
               }
               else if (spacePark.getStatus().equals("false")) {
                   spark.setText("Parking number " + spacePark.getId() + " is not available please select another parking. ");


                   scan.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent intent = new Intent(context, Scan_page.class);
                           intent.putExtra("num",spacePark.getId());
                           intent.putExtra("s1",spacePark.getStatus());
                           //intent.putExtra("mall",m);
                           // context.startActivity(context, Scan_page.class);
                           context.startActivity(intent);













                       }
                   });


               }




           }
       });
    }
    @Override
    public int getItemCount() {
        return spaceArrayList.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {
        //implements  View.OnClickListener

        TextView number , NumberSpace ,status,ss ;
        Button button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           number=itemView.findViewById(R.id.text_space);
            status=itemView.findViewById(R.id.avalbvle);
            NumberSpace=itemView.findViewById(R.id.NumberSpace);
            ss=itemView.findViewById(R.id.st);
           button=itemView.findViewById(R.id.button);





        }
/*
        @Override
        public void onClick(View view) {
            int postion =getAdapterPosition();
            Toast.makeText(context, "park"+postion, Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(context, Scan_page.class);
                context.startActivity(intent);


        }
        */

    }


}
