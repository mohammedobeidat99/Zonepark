package com.example.myapplication;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Admin.Delete;
import com.example.myapplication.Admin.home;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SpaceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button btnDelete;
    DatabaseReference reff ,dbref;
    //**/*/*/*/*/*/*/*/*/*/*/
    Spinner spinner2;
    String chosen_spinner;

    //////////////////////////////////////////////////////////////
    Spinner spinner3;
    String chosen_spinner3;
    TextView numberpark;

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        chosen_spinner=adapterView.getItemAtPosition(i).toString();
        fetchdata();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    ValueEventListener listener;

    ArrayList<String> list;
    ArrayAdapter<String> adapter1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space);



        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.navigationView);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.Space);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @ Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.Delete:
                        startActivity(new Intent(getApplicationContext(), Delete.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Space:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), home.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        reff= FirebaseDatabase.getInstance().getReference().child("mallinfo");

        btnDelete=findViewById(R.id.BtnDelete);




        spinner2=findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.city, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);
////////////////////////////////////////////////////////////////////////////////////
        dbref=FirebaseDatabase.getInstance().getReference().child("mallinfo");
        spinner3=findViewById(R.id.spinner3);
        list=new ArrayList<>();
        adapter1=new ArrayAdapter<>(SpaceActivity.this,android.R.layout.simple_spinner_item,list);
        spinner3.setAdapter(adapter1);

        numberpark = (TextView) findViewById(R.id.numberpark);




        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                chosen_spinner3=adapterView.getItemAtPosition(i).toString();

                DatabaseReference dayOneRef = reff.child(chosen_spinner).child(chosen_spinner3).child("space");
                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        }
                        numberpark.setText("number of parking in the mall is : "+dataSnapshot.getChildrenCount());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d(TAG, databaseError.getMessage()); //Don't ignore errors!
                    }
                };
                dayOneRef.addListenerForSingleValueEvent(valueEventListener);

                //////////////////////////////////////
                DatabaseReference in2 = reff.child(chosen_spinner).child(chosen_spinner3).child("space");



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



///*//////////////////****

    }

    public void fetchdata(){
        listener=dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot mydata:snapshot.child(chosen_spinner).getChildren() ){
                    list.add(String.valueOf(String.valueOf(mydata.getKey())));
                }

                adapter1.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }}