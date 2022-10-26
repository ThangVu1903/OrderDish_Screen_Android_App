package com.example.orderdish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class DishAdapter extends BaseAdapter {
    private Context ct;
    private int layout;
    private ArrayList<Dish> arr;

    public DishAdapter(Context ct, int layout, ArrayList<Dish> arr) {
        this.ct = ct;
        this.layout = layout;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size() ;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
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
