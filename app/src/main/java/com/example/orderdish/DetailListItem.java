package com.example.orderdish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.orderdish.model.Dish;
import com.google.firebase.database.DatabaseReference;

public class DetailListItem extends AppCompatActivity {
    TextView textView;
    TextView nameDetail,bonusDetail,addressDetail,priceDetail,infoDetail;
    ImageView imgDetail;
    Dish d = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list_item);
        textView = findViewById(R.id.backtolist);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mapping();
        if (getIntent() != null && getIntent().getSerializableExtra("dish") != null && getIntent().hasExtra("dish")){
            d = (Dish) getIntent().getSerializableExtra("dish");
            nameDetail.setText(d.tenMon);
            infoDetail.setText(d.thongTin);
            bonusDetail.setText(d.monAnThem);
            addressDetail.setText(d.diaChi);
            priceDetail.setText(d.giaTien);
            Glide.with(getApplicationContext()).load(d.anh).into(imgDetail);
        }

    }
    public void BackListDish(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

    }

    public void mapping(){
        nameDetail = findViewById(R.id.nameDetail);
        infoDetail = findViewById(R.id.infoDetail);
        bonusDetail = findViewById(R.id.bonusDetail);
         imgDetail = findViewById(R.id.imgDetail);
        priceDetail = findViewById(R.id.priceDetail);
        addressDetail = findViewById(R.id.addressDetail);
    }
}


