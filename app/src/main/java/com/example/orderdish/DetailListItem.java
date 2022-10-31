package com.example.orderdish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailListItem extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list_item);
        textView = findViewById(R.id.backtolist);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackListDish();
            }
        });

    }
    public void BackListDish(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

    }
}