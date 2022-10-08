package com.example.orderdish;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class ListDish extends AppCompatActivity {
    ListView lsvDish;
    ArrayList<Dish> arr;
    DishAdapter adapter;
    TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dish);
        back = findViewById(R.id.bactohome);

        lsvDish = findViewById(R.id.lsvDish);
        lsvDish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(ListDish.this,DetailListItem.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListDish.this,Home.class));
            }
        });
        arr = new ArrayList<>();

        Random r = new Random();

        arr.add(new Dish(R.drawable.mon_1,r.nextInt(10)*2,"Cơm gà Vip"));
        arr.add(new Dish(R.drawable.mon_2,r.nextInt(10)*2,"Lẩu Hàn"));
        arr.add(new Dish(R.drawable.mon_3,r.nextInt(10)*2,"Bò Hấp"));
        arr.add(new Dish(R.drawable.mon_4,r.nextInt(10)*2,"Canh hấp"));
        arr.add(new Dish(R.drawable.mon_5,r.nextInt(10)*2,"Bánh Kẹp"));
        arr.add(new Dish(R.drawable.mon_6,r.nextInt(10)*2,"Gà Rán"));
        arr.add(new Dish(R.drawable.mon_1,r.nextInt(10)*2,"Cơm Vip"));
        arr.add(new Dish(R.drawable.mon_2,r.nextInt(10)*2,"Canh Thái"));
        arr.add(new Dish(R.drawable.mon_3,r.nextInt(10)*2,"Bò Hà Lan"));
        arr.add(new Dish(R.drawable.mon_4,r.nextInt(10)*2,"Lẩu"));


        adapter = new DishAdapter(this,0,arr);
        lsvDish.setAdapter(adapter);
    }

}