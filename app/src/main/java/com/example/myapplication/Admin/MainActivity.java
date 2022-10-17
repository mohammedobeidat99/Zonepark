package com.example.myapplication.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView register;
    private EditText Email,password;
    private Button signIn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register=(TextView) findViewById(R.id.register);
        register.setOnClickListener(this);
        signIn=(Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(this);
        Email=(EditText) findViewById(R.id.City_Delete);
        password=(EditText) findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                startActivity(new Intent(this, register.class));
                break;
            case R.id.signIn:
                adminLogin();
                break;
        }
    }

    private void adminLogin() {
        String email=Email.getText().toString().trim();
        String password1=password.getText().toString().trim();
        if(email.isEmpty()){
            Email.setError("Email is required");
            Email.requestFocus();
            return;
        }
        if(password1.isEmpty()){
            password.setError("password is required");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, cities.class));
                }else{
                    Toast.makeText(MainActivity.this, "faild to login check your email/password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}