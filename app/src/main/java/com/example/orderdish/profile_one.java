package com.example.orderdish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class profile_one extends AppCompatActivity {
    TextView back,backlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_one);
        back = findViewById(R.id.backTextview);
        backlogin = findViewById(R.id.backLogin);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile_one.this,Home.class));
            }
        });
        backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile_one.this,SignIn.class));
            }
        });
    }
}