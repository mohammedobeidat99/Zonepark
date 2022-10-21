package com.example.myapplication.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Mall_page extends AppCompatActivity {
    TextView mall ,back;
    RecyclerView recyclerView;
    ArrayList<Mall> list1;
    MyAdapter2 myAdapter2;
    DatabaseReference database;
    StorageReference firebaseStorage;
    AlertDialog.Builder Alert;
    ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_page);
        mall=findViewById(R.id.mall);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Mall_page.this, userMain.class));

            }
        });
        String cityname=getIntent().getStringExtra("the city is ");
        mall.setText("Welcome to "+cityname);



        recyclerView = findViewById(R.id.rvmall);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance().getReference().child("mallinfo").child(cityname);

        list1 = new ArrayList<>();
        myAdapter2 = new MyAdapter2(this,list1);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));


        recyclerView.setAdapter(myAdapter2);
        firebaseStorage = FirebaseStorage.getInstance().getReference().child("City/").child(cityname);
        firebaseStorage.listAll().addOnCompleteListener(task -> {
            List<StorageReference> tasks = task.getResult().getItems();
            for (int i = 0; i < tasks.size(); i++) {
                StorageReference reference = tasks.get(i);
                reference.getDownloadUrl().addOnCompleteListener(urlTask -> {

                    Uri imageUri = urlTask.getResult();
                    Log.e("--TAG", "onCreate: " + imageUri);
                    list1.add(new Mall(reference.getName(), imageUri));
                    if (list1.size() == tasks.size()) myAdapter2.notifyItemRangeInserted(0,list1.size());
                });
            }

        });






    }

}