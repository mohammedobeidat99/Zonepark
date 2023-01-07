package com.example.myapplication.Admin;

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

import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SpaceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button btnDelete;
    DatabaseReference reff ,dbref;
    //**/*/*/*/*/*/*/*/*/*/*/

    String chosen_spinner;
    Spinner spinner2, spinner3 ,spinner4,spinner5;
    String chosen_spinner3, chosen_spinner4,chosen_spinner5;
    TextView numberpark ,time_date,Availablepark ,available_park;
    String dateTime;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    boolean flag;
    Button Editspace;
    ArrayList<String> trueth=new ArrayList<String>();

    ArrayList<String> numlist=new ArrayList<String>();

    ArrayList<String> yesnolist=new ArrayList<String>();


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


        time_date=findViewById(R.id.time_date);
       // Availablepark=findViewById(R.id.Availablepark);


        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("EEEE-LLLL-yyyy  KK:mm:ss aaa ");
        dateTime = simpleDateFormat.format(calendar.getTime()).toString();
        time_date.setText(dateTime);






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

        yesnolist.add("true");
        yesnolist.add("false");




        spinner2=findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.city, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);
        dbref=FirebaseDatabase.getInstance().getReference().child("mallinfo");
        spinner3=findViewById(R.id.spinner3);
        list=new ArrayList<>();
        adapter1=new ArrayAdapter<>(SpaceActivity.this,android.R.layout.simple_spinner_item,list);
        spinner3.setAdapter(adapter1);
        numberpark = (TextView) findViewById(R.id.numberpark);
        available_park= (TextView) findViewById(R.id.available_park);

        spinner4=findViewById(R.id.spinner4);
        ArrayAdapter adapter4=new ArrayAdapter<>(SpaceActivity.this,android.R.layout.simple_spinner_item,numlist);
        spinner4.setAdapter(adapter4);


        spinner5=findViewById(R.id.spinner5);
        ArrayAdapter adapter5=new ArrayAdapter<>(SpaceActivity.this,android.R.layout.simple_spinner_item,yesnolist);
        spinner5.setAdapter(adapter5);

        Editspace=findViewById(R.id.Editspace);






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
                        numberpark.setText("Number of park in "+chosen_spinner3+" : "+dataSnapshot.getChildrenCount());
                        numlist.clear();
                        for (int i=0;i<dataSnapshot.getChildrenCount();i++){
                            //System.out.println("the status: "+dataSnapshot.child(String.valueOf(i)).child("status").getValue());
                            numlist.add(String.valueOf(i));
                            if(dataSnapshot.child(String.valueOf(i)).child("status").getValue().toString().equals("true")) {
                                trueth.add("*");
                                //numlist.add(dataSnapshot.child(String.valueOf(i)).child("id").getValue().toString());

                            }
                            //System.out.println("the true's is *:"+trueth.size());

                        }
                        adapter4.notifyDataSetChanged();
                        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                chosen_spinner4=adapterView.getItemAtPosition(i).toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                chosen_spinner5=adapterView.getItemAtPosition(i).toString();
                                if(chosen_spinner5=="true")
                                    flag=true;
                                else
                                    flag=false;
                                System.out.println("chosen_spinner5 :"+chosen_spinner5);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });



                        //System.out.println("the true's is *:"+trueth.size());
                        available_park.setText("The Available parks : "+trueth.size());
                        trueth.clear();
                        //////////









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
        ////

        Editspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change the status to false
                reff.child(chosen_spinner).child(chosen_spinner3).child("space").child(chosen_spinner4).child("status").setValue(flag);
                System.out.println(chosen_spinner5);
                System.out.println("the flag: "+flag);
                adapter4.notifyDataSetChanged();
            }
        });
        ////


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