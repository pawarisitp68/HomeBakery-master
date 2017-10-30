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

public class AdapterSnack extends BaseAdapter {
    private Context objContextSnack;
    private String[] namesnack,pricesnack,imagesnack;

    public AdapterSnack(Context context,String[] namesnack, String[] pricesnack, String[] imagesnack) {
        this.objContextSnack = context;
        this.namesnack = namesnack;
        this.pricesnack = pricesnack;
        this.imagesnack = imagesnack;

    }

    @Override
    public int getCount() {
        return namesnack.length;
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
        LayoutInflater objLayoutInflater = (LayoutInflater) objContextSnack.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = objLayoutInflater.inflate(R.layout.listviewsnack,parent,false);

        TextView foodTextView = (TextView) view.findViewById(R.id.namesnack);
        TextView priceTextView = (TextView) view.findViewById(R.id.pricesnack);
        ImageView foodImageView = (ImageView) view.findViewById(R.id.imvsnack);

        Picasso.with(objContextSnack).load(imagesnack[position]).into(foodImageView);
        foodTextView.setText(namesnack[position]);
        priceTextView.setText(pricesnack[position]);
        return view;
    }
}
