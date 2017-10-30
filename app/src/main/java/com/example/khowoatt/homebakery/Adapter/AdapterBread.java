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

public class AdapterBread extends BaseAdapter {

    private Context objContextbread;
    private String[] namebread,pricebread,imagebread;

    public AdapterBread(Context context,String[] namebread,String[] pricebread,String[] imagebread) {
        this.objContextbread = context;
        this.namebread = namebread;
        this.pricebread = pricebread;
        this.imagebread = imagebread;
    }

    @Override
    public int getCount() {
        return namebread.length;
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
        LayoutInflater objLayoutInflater = (LayoutInflater) objContextbread .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = objLayoutInflater.inflate(R.layout.listviewbread,parent,false);

        TextView foodTextView = (TextView) view.findViewById(R.id.namebread);
        TextView priceTextView = (TextView) view.findViewById(R.id.pricebread);
        ImageView foodImageView = (ImageView) view.findViewById(R.id.imvbread);

        Picasso.with(objContextbread).load(imagebread[position]).into(foodImageView);
        foodTextView.setText(namebread[position]);
        priceTextView.setText(pricebread[position]);
        return view;
    }
}
