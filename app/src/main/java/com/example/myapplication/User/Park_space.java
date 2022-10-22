package com.example.myapplication.User;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Park_space extends AppCompatActivity {

    RecyclerView recyclerViewspace;
    ArrayList<SpacePark> lists;
    MyAdapter3 myAdapter3;
    private final DatabaseReference database= FirebaseDatabase.getInstance().getReference();
    private final List<SpacePark> myspaceList=new ArrayList<>();
    TextView namemall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_space);



        recyclerViewspace = findViewById(R.id.recyclerViewspace);
        namemall=findViewById(R.id.name_mall2);
        recyclerViewspace.setHasFixedSize(true);
        recyclerViewspace.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewspace.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        lists = new ArrayList<>();
        myAdapter3 = new MyAdapter3(this,lists);

        Intent intent2 = getIntent();
        String mallname = intent2.getStringExtra("the mall is ");
        namemall.setText(mallname);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myspaceList.clear();
                for(DataSnapshot mallinfo : snapshot.child("mallinfo").child("Amman").child(mallname).child("space").getChildren()){

                    final String getNum= String.valueOf(String.valueOf(mallinfo.getKey()));

                  SpacePark myspace=new SpacePark(getNum);
                  myspaceList.add(myspace);
            }
                recyclerViewspace.setAdapter(new MyAdapter3(Park_space.this,myspaceList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}