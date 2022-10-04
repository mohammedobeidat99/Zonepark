package com.example.myapplication.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class cities extends AppCompatActivity {
    ///data from database
    //private FirebaseUser user;
    //private DatabaseReference reference;
    //private String userID;
    List<String> mylist;
    EditText tecity,temall,teparksnumber;
    Button btnsave ;
    DatabaseReference reff;
    mallinfo mallinfo1;
    parkingspace sp;
    TextView Delete;
    EditText tenumber;

    //image view city&mall
    Button insertcityphoto,insertmallphoto;
    ImageView imageView,imageView2;
    FirebaseStorage storage1,storage2;
    StorageReference storageReferenceCity,storageReferenceMall ;
    private int img_request_id = 10;
    private Uri imgUri;
    private int img_request_id2 = 20;
    private Uri imgUri2;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);



        temall = (EditText) findViewById(R.id.temall);
        tecity = (EditText) findViewById(R.id.City_Delete);
        teparksnumber = (EditText) findViewById(R.id.teparksnumber);
        btnsave = (Button) findViewById(R.id.btnsave);
        Delete = findViewById(R.id.tvDelete);
        reff = FirebaseDatabase.getInstance().getReference().child("mallinfo");
        mallinfo1 = new mallinfo();
        sp = new parkingspace();
        ArrayList<parkingspace> mylist = new ArrayList<parkingspace>();


        imageView=(ImageView) findViewById(R.id.imageView);
        imageView2=(ImageView) findViewById(R.id.imageView2);
        storage1=FirebaseStorage.getInstance();
        storage2=FirebaseStorage.getInstance();
        storageReferenceCity=storage1.getReference().child("City/");
        storageReferenceMall=storage2.getReference().child("Mall/");
       // imageView.set;

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestImage();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestImage2();
            }
        });




        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int spacenumber= Integer.parseInt(teparksnumber.getText().toString().trim());
                mallinfo1.setCity(tecity.getText().toString().trim());
                mallinfo1.setMall(temall.getText().toString().trim());
                mallinfo1.setParksnumber(teparksnumber.getText().toString().trim());
                // mallinfo1.setParksnumber(Parksnumber);

               // sp.setId(teparksnumber.getText().toString());
               // mallinfo1.setSpace(sp);

                for(int i=0 ; i<spacenumber; i++ ){
                    sp=new parkingspace();
                    mylist.add(sp);
                    sp.setId("park"+i);

                }


                    String City =tecity.getText().toString().trim();
                    String Mall =temall.getText().toString().trim();
                    String parknum =teparksnumber.getText().toString().trim();
                    //int park=Integer.parseInt(parknum);

                    if (City.isEmpty()) {
                        tecity.setError("City is Empty");
                        tecity.requestFocus();
                        return;
                    }

                    if (Mall.isEmpty()) {
                        temall.setError("Mall is Empty");
                        temall.requestFocus();
                        return;
                    }
                    if (parknum.isEmpty()) {
                        teparksnumber.setError("Parknum is Empty");
                        teparksnumber.requestFocus();
                        return;

                    }

                    else {
                        reff.child(tecity.getText().toString()).child(temall.getText().toString()).push().setValue(mallinfo1);
                        reff.child(tecity.getText().toString()).child(temall.getText().toString()).child("space").setValue(mylist);
                        Toast.makeText(cities.this, "Successful Insert", Toast.LENGTH_SHORT).show();
                        saveInFirebase();
                        saveInFirebase2();
                    }


                }

        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(cities.this, com.example.myapplication.User.userMain.class);
                startActivity(myIntent);
            }
        });

    }

    private void saveInFirebase2() {
        if(imgUri2 != null){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("please waite...");
            progressDialog.show();
            StorageReference reference = storageReferenceMall.child(temall.getText().toString()/*+ UUID.randomUUID().toString()*/);
           // reference.child("City/");
            reference.putFile(imgUri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(cities.this, "Saved successful", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(cities.this, "ERROR... "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress=(100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    progressDialog.setMessage("saved" + (int) progress + "%");

                }
            });
        }
    }


    private void requestImage2() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select picture"),img_request_id2);
    }


    ////////////////////////////////////////
    //insert city photo

    private void saveInFirebase() {
        if(imgUri != null){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("please waite...");
            progressDialog.show();
            StorageReference reference = storageReferenceCity.child(tecity.getText().toString()/*+ UUID.randomUUID().toString()*/);

            reference.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(cities.this, "Saved successful", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(cities.this, "ERROR... "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress=(100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    progressDialog.setMessage("saved" + (int) progress + "%");

                }
            });
        }
    }

    private void requestImage() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select picture"),img_request_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == img_request_id && resultCode == RESULT_OK && data != null && data.getData() !=null){
            imgUri=data.getData();
            try {
                Bitmap bitmapimg=MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
                imageView.setImageBitmap(bitmapimg);
               // insertcityphoto.setEnabled(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == img_request_id2 && resultCode == RESULT_OK && data != null && data.getData() !=null){
            imgUri2=data.getData();
            try {
                Bitmap bitmapimg2=MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri2);
                imageView2.setImageBitmap(bitmapimg2);
               // insertmallphoto.setEnabled(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }






}











