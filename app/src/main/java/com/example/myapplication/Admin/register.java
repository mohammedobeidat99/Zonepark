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
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity implements View.OnClickListener{
    private EditText etName,  etEmail, etPassword ,etPassword2;
    private TextView btnRegister ,login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        etName = (EditText) findViewById(R.id.etName);
        login=findViewById(R.id.login);

        etEmail = (EditText) findViewById(R.id.City_Delete);
        etPassword = (EditText) findViewById(R.id.password);
        etPassword2 = (EditText) findViewById(R.id.etPassword2);
        btnRegister=(Button) findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent backlogin = new Intent(register.this, MainActivity.class);
               // startActivity(backlogin);
                startActivity(new Intent(register.this, MainActivity.class));
                //startActivity(new Intent(register.this,MainActivity.class));

            }
        });

    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnRegister:
                resgisteradmin();

                break;

        }


    }

    private void resgisteradmin() {
        String firstname =etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String password2 = etPassword2.getText().toString().trim();


        if (firstname.isEmpty()) {
            etName.setError("Name is Empty");
            etName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            etEmail.setError("Email is Empty");
            etEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etPassword.setError("password is Empty");
            etPassword.requestFocus();
            return;
        }
        if (password2.isEmpty()) {
            etPassword2.setError("Confirm password is Empty");
            etPassword2.requestFocus();
            return;
        }
       /* if (password == password2) {
            etPassword2.setError("Mismatch Password");
            etPassword2.requestFocus();
            return;
        }
        */

        //ProgressBar.setVisibilty(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Admin admin=new Admin(firstname,email,password);
                            FirebaseDatabase.getInstance().getReference("admins")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(admin).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(register.this, "Admin has been registeres Successfully", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(register.this,MainActivity.class));
                                    }else{
                                        Toast.makeText(register.this, "Faild registeres ", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });

                        }else {
                            Toast.makeText(register.this, "Faild registeres ", Toast.LENGTH_LONG).show();

                        }
                    }
                });



    }
}