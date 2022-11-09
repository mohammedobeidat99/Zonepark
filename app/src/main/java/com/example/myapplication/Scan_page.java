package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class Scan_page extends AppCompatActivity   {
        private Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_page);



        update=findViewById(R.id.update);

        //String ss="null";
        Intent intent1 = getIntent();
        String num=intent1.getStringExtra("num");
        Intent intent2 = getIntent();
        String ss = intent2.getStringExtra("s1");


        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (ss.equals("true")) {

                    FirebaseDatabase.getInstance().getReference("mallinfo").child("Amman").child("Maka Mall").child("space").child(num).child("status").setValue(false);
                }
                else if (ss.equals("false")) {

                    FirebaseDatabase.getInstance().getReference("mallinfo").child("Amman").child("Maka Mall").child("space").child(num).child("status").setValue(true);
                }



              /*  if (s.equals("false")) {
                    Toast.makeText(view.getContext(), "Not Available" , Toast.LENGTH_SHORT).show();

                    FirebaseDatabase.getInstance().getReference("mallinfo").child("Amman").child("Maka Mall").child("space").child("1").child("status").setValue(true);
                }*/


            }
        });


    }
}