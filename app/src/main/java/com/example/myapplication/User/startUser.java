package com.example.myapplication.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class startUser extends AppCompatActivity {
    EditText carnumber;
    Button start;
    DatabaseReference data;
    user user1;
    TextView tv11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_user);
        tv11=findViewById(R.id.textView11);
        carnumber=(EditText) findViewById(R.id.carnumber);
        start=(Button) findViewById(R.id.start);
        data= FirebaseDatabase.getInstance().getReference().child("cars_number");
        user1=new user();
        tv11.setText("Terms & Policy");

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num=carnumber.getText().toString().trim();
                if(num.isEmpty() ){
                    carnumber.setError("should to Enter car number");
                    carnumber.requestFocus();
                    return;
                }
                if( num.length()<5){
                    carnumber.setError(" Enter correct car number");
                    carnumber.requestFocus();
                    return;
                }

                else { try {//to fix if the input numbers or no
                    int carnumber1 = Integer.parseInt(num.toString().trim());
                    user1.setCarnumber(carnumber1);
                    data.push().setValue(user1);
                    startActivity(new Intent(startUser.this, userMain.class));
                    Toast.makeText(startUser.this,"Done ", Toast.LENGTH_SHORT);
                }catch (NumberFormatException e) {
                    carnumber.setError("you should to insert a number");
                    carnumber.requestFocus();
                    return;                }

                }

            }
        });


    }


}