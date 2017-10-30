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

public class AdapterPastry extends BaseAdapter{
    private Context objcontext;
    private String[] foodString,sourceString,priceString;

    public AdapterPastry(Context context, String[] foodString, String[] sourceString, String[] priceString) {
        this.objcontext = context;
        this.foodString = foodString; // ชื่อ
        this.sourceString = sourceString; // รูป
        this.priceString = priceString; // ราคา
    }

    @Override
    public int getCount() {
        return foodString.length;
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
        LayoutInflater objLayoutInflater = (LayoutInflater) objcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = objLayoutInflater.inflate(R.layout.listviewpastry, parent,false);

        // show food
        TextView foodTextView = (TextView) view1.findViewById(R.id.namepastry);
        //show price
        TextView priceTextView = (TextView) view1.findViewById(R.id.pricepastry);
        //show imagefood
        ImageView foodImageView = (ImageView) view1.findViewById(R.id.imvPastry);

        Picasso.with(objcontext).load(sourceString[position]).into(foodImageView);
        foodTextView.setText(foodString[position]);
        priceTextView.setText(priceString[position]);

        return view1;
    }
}
