package com.example.khowoatt.homebakery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khowoatt.homebakery.R;
import com.squareup.picasso.Picasso;

/**
 * Created by khowoatt on 8/29/2017.
 */

public class AdapterCake extends BaseAdapter{
    private Context objcontextcake;
    private String[] namecake,pricecake,imagecake;

    public AdapterCake(Context context,String[] namecake, String[] pricecake, String[] imagecake) {
        this.objcontextcake = context;
        this.namecake = namecake;
        this.pricecake = pricecake;
        this.imagecake = imagecake;
    }
    @Override
    public int getCount() {
        return namecake.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater objLayoutInflater = (LayoutInflater) objcontextcake.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = objLayoutInflater.inflate(R.layout.listviewcake,parent,false);

        TextView foodTextView = (TextView) view.findViewById(R.id.namecake);
        TextView priceTextView = (TextView) view.findViewById(R.id.pricecake);
        ImageView foodImageView = (ImageView) view.findViewById(R.id.imvcake);

        Picasso.with(objcontextcake).load(imagecake[position]).into(foodImageView);
        foodTextView.setText(namecake[position]);
        priceTextView.setText(pricecake[position]);
        return view;
    }
}
