package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Scan_page extends AppCompatActivity   {
        private Button scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_page);


        scan = (Button) findViewById(R.id.scan);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(Scan_page.this);
                intentIntegrator.setPrompt("Use volume up key for Flash on/ volume down key for flash off");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,
                resultCode, data);
        // test if the content is not null :

        Intent intent1 = getIntent();
        String num=intent1.getStringExtra("num");
        Intent intent2 = getIntent();
        String ss = intent2.getStringExtra("s1");
        Intent intent3 = getIntent();
       // String m = intent2.getStringExtra("m");
        String m="Maka Mall";

        if (intentResult.getContents().equals(num)){


            if (ss.equals("true")) {

                FirebaseDatabase.getInstance().getReference("mallinfo").child("Amman").child(m).child("space").child(num).child("status").setValue(false);
                AlertDialog.Builder builder = new AlertDialog.Builder(Scan_page.this);
                builder.setTitle("Results");
                builder.setMessage("Space Park Number "+intentResult.getContents()+" is booked Successfully.");
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
            else if (ss.equals("false")) {

                FirebaseDatabase.getInstance().getReference("mallinfo").child("Amman").child(m).child("space").child(num).child("status").setValue(true);
                AlertDialog.Builder builder = new AlertDialog.Builder(Scan_page.this);
                builder.setTitle("Results");
                builder.setMessage("Space Park Number "+intentResult.getContents()+" is Not booked.");
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();

            }


        } else {
            Toast.makeText(this, "Sory you did not scan anything ! ", Toast.LENGTH_SHORT).show();
        }

    }
}