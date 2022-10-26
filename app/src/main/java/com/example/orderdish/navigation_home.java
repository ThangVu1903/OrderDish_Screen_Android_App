package com.example.orderdish;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class navigation_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_home);


        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_bottom);

        getSupportFragmentManager().beginTransaction().replace(R.id.F_layout_home,new Home()).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment= null;
                switch (item.getItemId()){
                    case R.id.nav_orders:
                        selectedFragment= new Home();

                        break;
                    case R.id.na_listView:
                        selectedFragment= new ListDish();

                        break;
//                    case R.id.nav_music_remix:
//                        selectedFragment= new FragmentMusicRemix();
//
//                        break;
//                    case R.id.nav_category:
//                        selectedFragment= new FragmentCategory();
//
//                        break;
                    case R.id.nav_profile:
                        selectedFragment= new profile_one();

                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.F_layout_home,selectedFragment).commit();
                return true;
            }
        });
    }
}