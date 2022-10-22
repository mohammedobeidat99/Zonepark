package com.example.myapplication.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Delete extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btnDelete;
    EditText EMall;
    DatabaseReference reff ,dbref;
    //**/*/*/*/*/*/*/*/*/*/*/
    Spinner spinner2;
    String chosen_spinner;
////****/*//*/*/*/**//*/*/////
    FirebaseStorage storage1;
    StorageReference storageReferenceCity;

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        chosen_spinner=adapterView.getItemAtPosition(i).toString();
        //Toast.makeText(adapterView.getContext(),chosen_spinner,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        reff=FirebaseDatabase.getInstance().getReference().child("mallinfo");

        btnDelete=findViewById(R.id.BtnDelete);
        EMall=findViewById(R.id.Mall_Delete);
        dbref=FirebaseDatabase.getInstance().getReference();


        spinner2=findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.city, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);

///*//////////////////****
        storage1=FirebaseStorage.getInstance();
        storageReferenceCity=storage1.getReference().child("City/");




        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (EMall.getText().toString().isEmpty())
                    Toast.makeText(Delete.this, "Please Enter The Mall !", Toast.LENGTH_SHORT).show();

                else {
                    ///************delete from the data base
                    reff.child(chosen_spinner).child(EMall.getText().toString()).removeValue();
                    ///************delete th image from the storage
                    storageReferenceCity.child(chosen_spinner).child(EMall.getText().toString()).delete();
                    Toast.makeText(Delete.this, " Done Delete "+EMall.getText().toString() , Toast.LENGTH_SHORT).show();
                }

            }
        });




    }






}