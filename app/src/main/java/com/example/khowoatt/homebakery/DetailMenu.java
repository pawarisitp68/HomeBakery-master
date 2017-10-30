package com.example.khowoatt.homebakery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khowoatt.homebakery.Database.OrderlistTable;
import com.squareup.picasso.Picasso;

public class DetailMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);

        EditText editText = (EditText) findViewById(R.id.addmenu);
        ImageView detailimageView = (ImageView) findViewById(R.id.imvDetailmenu);
        TextView pricetextView = (TextView) findViewById(R.id.pricedetail);
        TextView nametextView = (TextView) findViewById(R.id.menudetail);
        TextView detailtextView = (TextView) findViewById(R.id.detail);

        nametextView.setText(getIntent().getStringExtra("name"));
        pricetextView.setText(getIntent().getStringExtra("price"));
        detailtextView.setText(getIntent().getStringExtra("detail"));
        String image = getIntent().getStringExtra("image");
        Picasso.with(DetailMenu.this).load(image).into(detailimageView);


    }

    public void onClickBack (View view){
        finish();
    }

    public void onClickcart(View view) {
        Intent intent = new Intent(DetailMenu.this, ConfirmOrder.class);
        startActivity(intent);
    }
}
