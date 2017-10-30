package com.example.khowoatt.homebakery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.khowoatt.homebakery.Adapter.AdapterBread;
import com.example.khowoatt.homebakery.Database.MenuTable;

public class BreadMenu extends AppCompatActivity {
    private MenuTable objMenuTable;
    private ListView MenuListView;
    private String[] name,price,image,detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bread_menu);

        BindWidget();
        Cdatabase();
        createListView();
    }

    private void createListView() {

        final String[] namebread = objMenuTable.readALLMenuTable3(1);
        final String[] pricebread = objMenuTable.readALLMenuTable3(3);
        final String[] imagebread = objMenuTable.readALLMenuTable3(4);
        final String[] detilbread = objMenuTable.readALLMenuTable3(2);

        AdapterBread objMyAdapterBread = new AdapterBread(BreadMenu.this,namebread,pricebread,imagebread);
        MenuListView.setAdapter(objMyAdapterBread);
        MenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                name = namebread;
                price = pricebread;
                image = imagebread;
                detail = detilbread;

                Intent intent = new Intent(BreadMenu.this, DetailMenu.class);

                intent.putExtra("name", namebread[position]);
                intent.putExtra("price",pricebread[position]);
                intent.putExtra("image",imagebread[position]);
                intent.putExtra("detail", detilbread[position]);
                startActivity(intent);
            }
        });

    }

    private void Cdatabase() {
        objMenuTable = new MenuTable(this);
    }

    private void BindWidget() {
        MenuListView = (ListView) findViewById(R.id.livBread);
    }
    public void onClickBack (View view){
        new Intent(BreadMenu.this,MainActivity.class);
        finish();
    }//ลิ้งไปหน้า Menu snack

    public void onClickcart(View view) {
        Intent intent = new Intent(BreadMenu.this, ConfirmOrder.class);
        startActivity(intent);
    }

}
