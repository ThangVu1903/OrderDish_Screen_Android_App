package com.example.orderdish;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orderdish.model.Dish;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Random;
import java.util.UUID;

public class CreateDish extends AppCompatActivity {
    TextView tenCreate, thongTinCreate, giaTienCreate, diaChiCreate, anKemCreate, backToList, addAnh;
    ImageView AddImg;
    Button btnAdd;
    DatabaseReference databaseReference  ;
    StorageReference strorageReference = FirebaseStorage.getInstance().getReference();
    private Uri imgUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dish);
        Anhxa();

        backToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("monan");

        addAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,2);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ten = tenCreate.getText().toString();
                final String thongTin = thongTinCreate.getText().toString();
                final String gia = giaTienCreate.getText().toString();
                final String diachi = diaChiCreate.getText().toString();
                final String ankem = anKemCreate.getText().toString();
                    UUID uuid = UUID.randomUUID();
                    Dish dish = new Dish(uuid.toString(),imgUri.toString(),ten,thongTin,gia,diachi,ankem);
                    uploadImg(dish,imgUri);

            }
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2  && resultCode == RESULT_OK && data != null){
            imgUri = data.getData();
            AddImg.setImageURI(imgUri);
        }
    }

    public void Anhxa(){
        tenCreate = findViewById(R.id.nameDishCreate);
        thongTinCreate = findViewById(R.id.thongTinDishCreate);
        giaTienCreate = findViewById(R.id.giaTienDishCreate);
        diaChiCreate = findViewById(R.id.diaChiDishCreate);
        anKemCreate = findViewById(R.id.monAnThemDishCreate);
        backToList = findViewById(R.id.backToListOne);
        addAnh = findViewById(R.id.addAnh);
        AddImg = findViewById(R.id.imageViewAdd);
        btnAdd = findViewById(R.id.btnCreteDish);
    }


    public void uploadImg(Dish dish,Uri uri){
        StorageReference reference = strorageReference.child(System.currentTimeMillis() + "." + getFileExtensionUri(uri) );
        reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        dish.anh = uri.toString();

                        databaseReference.child(dish.idMon).setValue(dish);
                        Toast.makeText(CreateDish.this, "upload successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateDish.this, "uploading file failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtensionUri(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap =MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }


}