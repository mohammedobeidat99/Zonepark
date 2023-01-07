package com.example.myapplication.User;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication.Capture;
import com.example.myapplication.R;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Scan_page extends AppCompatActivity  {
    private Button scan;
    NotificationManagerCompat notificationManagerCompat;
    Notification notification,notification1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_page);

       Notification_Scan();
        Notification_Scan1();

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
        ////////////////////////
        Intent intent3 = getIntent();
        String c = intent3.getStringExtra("c");

        Intent intent4 = getIntent();
        String m = intent4.getStringExtra("m");



        if (intentResult.getContents().equals(num)){


            if (ss.equals("false")) {

                FirebaseDatabase.getInstance().getReference("mallinfo").child(c).child(m).child("space").child(num).child("status").setValue(true);
                AlertDialog.Builder builder = new AlertDialog.Builder(Scan_page.this);
                builder.setTitle("Results");
                builder.setMessage("Space Park Number "+intentResult.getContents()+" is not booked Successfully.");
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });

                builder.show();



            }
          else if (ss.equals("true")) {

                FirebaseDatabase.getInstance().getReference("mallinfo").child(c).child(m).child("space").child(num).child("status").setValue(false);
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
                notificationManagerCompat.notify(1,notification);


            }




        } else {
            Toast.makeText(this, "Sory you did not scan anything ! ", Toast.LENGTH_SHORT).show();

          //  builder.show();
            notificationManagerCompat.notify(1,notification1);


        }

    }

    public void Notification_Scan() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("zoneScan","zonepark", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager =getSystemService( NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"zoneScan")
                .setSmallIcon(R.drawable.zonepark_logo)
                .setContentTitle("Scan")
                .setContentText("Parking has been successfully reserved, thank you for the Scan, We wish you a nice day.");

        notification=builder.build();
        notificationManagerCompat=NotificationManagerCompat.from(this);



    }
    public void Notification_Scan1() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("zoneScan","zonepark", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager =getSystemService( NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"zoneScan")
                .setSmallIcon(R.drawable.zonepark_logo)
                .setContentTitle("Scan")
                .setContentText("The parking number does not match, please scan the correct QR number.");

        notification1=builder.build();
        notificationManagerCompat=NotificationManagerCompat.from(this);



    }


    }