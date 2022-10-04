package com.example.myapplication.User;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class userMain extends AppCompatActivity  {

    RecyclerView recyclerView;
    ArrayList<City> list;
    MyAdapter myAdapter;
    DatabaseReference database;
    StorageReference firebaseStorage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance().getReference().child("mallinfo");

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this, list);
        recyclerView.setAdapter(myAdapter);
        firebaseStorage = FirebaseStorage.getInstance().getReference().child("City/");
        firebaseStorage.listAll().addOnCompleteListener(task -> {
            List<StorageReference> tasks = task.getResult().getItems();
            for (int i = 0; i < tasks.size(); i++) {
                StorageReference reference = tasks.get(i);
                reference.getDownloadUrl().addOnCompleteListener(urlTask -> {

                    Uri imageUri = urlTask.getResult();
                    Log.e("--TAG", "onCreate: " + imageUri);
                    //   database.child(a.getName());
                    list.add(new City(reference.getName(), imageUri));

                    if (list.size() == tasks.size()) myAdapter.notifyItemRangeInserted(0,list.size());
                });
            }

        });


      /*  database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                 String  city=dataSnapshot.getKey();
                // City image=dataSnapshot.getValue(City.class);
                    list.add(new City(city));
                   // list.add(image);


                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

    }


}