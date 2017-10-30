package com.example.khowoatt.homebakery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.khowoatt.homebakery.Adapter.AdapterCake;
import com.example.khowoatt.homebakery.Database.MenuTable;

public class CakeMenu extends AppCompatActivity {
    private MenuTable objMenuTable;
    private ListView listView;
    private String[] name,price,image,detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_menu);

        BindWidget();
        Cdatabase();
        createListView();
    }
    private void createListView() {
        //MenuTable objCasephone = new MenuTable(this);

        final String[] strname = objMenuTable.readALLMenuTable2(1);
        final String[] strprice = objMenuTable.readALLMenuTable2(3);
        final String[] strimage = objMenuTable.readALLMenuTable2(4);
        final String[] detailcake = objMenuTable.readALLMenuTable2(2);

        AdapterCake objMyAdapterCake = new AdapterCake(CakeMenu.this ,strname,strprice,strimage);
        listView.setAdapter(objMyAdapterCake);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                name = strname;
                price = strprice;
                image = strimage;
                detail = detailcake;

                Intent intent = new Intent(CakeMenu.this, DetailMenu.class);

                intent.putExtra("name", strname[position]);
                intent.putExtra("price",strprice[position]);
                intent.putExtra("image",strimage[position]);
                intent.putExtra("detail", detailcake[position]);
                startActivity(intent);

            }
        });
    }

    private void Cdatabase() {
        objMenuTable = new MenuTable(this);
    }

    private void BindWidget() {
        listView = (ListView) findViewById(R.id.livcake);
    }

    public void onClickBack (View view){
        new Intent(CakeMenu.this,MainActivity.class);
        finish();
    }

    public void onClickcart(View view) {
        Intent intent = new Intent(CakeMenu.this, ConfirmOrder.class);
        startActivity(intent);
    }
}