package com.example.orderdish;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orderdish.model.Dish;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class ListDish extends Fragment {
    ListView lsvDish;
    ArrayList<Dish> arr;

    DishAdapter adapter;
    DatabaseReference databaseReference;
    FloatingActionButton createTxt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_list_dish, container, false);
        createTxt = v.findViewById(R.id.createDish);
        createTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CreateDish.class));
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("monan");
        lsvDish = v.findViewById(R.id.lsvDish);

        arr = new ArrayList<>();

        adapter = new DishAdapter(getContext(), R.layout.sub_item_list, arr);
        lsvDish.setAdapter(adapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    Dish d = snapshot.getValue(Dish.class);
                    arr.add(d);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lsvDish.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                xoa(i);
                return false;
            }
        });

        lsvDish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent =  new Intent(getActivity().getBaseContext(), DetailListItem.class);
              intent.putExtra("dish",arr.get(i));
              startActivity(intent);
            }
        });



        return v;
    }
    public void xoa(int pos){

        AlertDialog.Builder alert  =new AlertDialog.Builder(getContext());
        alert.setTitle("Thông Báo!! ");
        alert.setMessage("Bạn có muốn xóa không ?");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                databaseReference.child("monan").child(arr.get(pos).idMon).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getContext(),"Remove succeed",Toast.LENGTH_SHORT)
                                        .show();
                                arr.remove(pos);
                                adapter.notifyDataSetChanged();
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                Toast.makeText(getContext(),"Remove fail",Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
            }
        });
        alert.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();


    }



}

