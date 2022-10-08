package com.example.orderdish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class DishAdapter extends ArrayAdapter<Dish> {
    private Context ct;
    private ArrayList<Dish> arr;
    public DishAdapter(@NonNull ListDish context, int resource, @NonNull List<Dish> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }
    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
        if (convertView == null){
            LayoutInflater i = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = i.inflate(R.layout.sub_item_list,null );
        }
        if(arr.size()>0) {
            Dish d = arr.get(position);
            ImageView imageDish = convertView.findViewById(R.id.imgDish);
            TextView txvNameDish = convertView.findViewById(R.id.txvNameDish);
            TextView txvNumItem = convertView.findViewById(R.id.txvNumItem);

            imageDish.setImageResource(d.image);
            txvNameDish.setText(d.name);
            txvNumItem.setText(d.numItem+"Items");
        }
            return  convertView;
    }

}
