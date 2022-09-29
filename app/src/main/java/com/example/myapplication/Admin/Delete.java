package com.example.myapplication.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Delete extends AppCompatActivity {
    Button btnDelete;
    EditText ECity,EMall;
    Spinner spinner;
    ValueEventListener listener;
    DatabaseReference reff ,dbref;
    ArrayList<String>spinnerList;
    //ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);


        reff=FirebaseDatabase.getInstance().getReference().child("mallinfo");


        btnDelete=findViewById(R.id.BtnDelete);
        ECity= findViewById(R.id.City_Delete);
        EMall=findViewById(R.id.Mall_Delete);
        spinner=findViewById(R.id.s);

     dbref=FirebaseDatabase.getInstance().getReference();
        dbref.child(ECity.getText().toString()).child(EMall.getText().toString());

       spinnerList=new ArrayList<String>();
       //adapter=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,spinnerList);
        //spinner.setAdapter(adapter);

        fetchdata();



        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ECity.getText().toString().trim().isEmpty() ||
                        EMall.getText().toString().trim().isEmpty())
                {
                    if (ECity.getText().toString().isEmpty())
                        Toast.makeText(Delete.this, "Please Enter The City !", Toast.LENGTH_SHORT).show();

                    else if (EMall.getText().toString().isEmpty())
                        Toast.makeText(Delete.this, "Please Enter The Mall !", Toast.LENGTH_SHORT).show();
                }
                else {

                    reff.child(ECity.getText().toString()).child(EMall.getText().toString()).removeValue();
                    Toast.makeText(Delete.this, " Done Delete "+EMall.getText().toString() , Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
    public void fetchdata(){

        //ref.child("Tabela-Consulta").addValueEventListener(new ValueEventListener() {
        listener=dbref.child("mallinfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot item :snapshot.getChildren()){
                //   spinnerList.clear();
                    spinnerList.add(item.child("mallinfo").getKey());
                    //adapter.notifyDataSetChanged();
                }
                ArrayAdapter<String>adapter=new ArrayAdapter<>(Delete.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,spinnerList);

                spinner.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}