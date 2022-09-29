package com.example.myapplication.User;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class userMain extends AppCompatActivity {

        RecyclerView recyclerView;
        ArrayList<City>list;
        MyAdapter myAdapter;
        DatabaseReference database ;
        StorageReference firebaseStorage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        recyclerView=findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database=FirebaseDatabase.getInstance().getReference().child("mallinfo");

        list=new ArrayList<>();
        myAdapter=new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);
        firebaseStorage= FirebaseStorage.getInstance().getReference();
        firebaseStorage.listAll().addOnSuccessListener( new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for (StorageReference a:listResult.getItems()
                     ) {
                    a.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            a.getDownloadUrl().getResult();
                            //   database.child(a.getName());
                            list.add(new City(a.getName(),a.getDownloadUrl().getResult()));
                        }
                    });


                }
                myAdapter.notifyDataSetChanged();
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