package com.example.khowoatt.homebakery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.khowoatt.homebakery.Adapter.AdapterSnack;
import com.example.khowoatt.homebakery.Database.MenuTable;

public class SnackMenu extends AppCompatActivity {
    private MenuTable objMenutable;
    private ListView MenuListview;
    private String[] name,price,image,detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_menu);
        BindWidget();
        Cdatabase();
        createListView();
    }
    private void createListView() {

        final String[] strnamesnack = objMenutable.readALLMenuTable4(1);
        final String[] strpicsnack = objMenutable.readALLMenuTable4(3);
        final String[] strpricesnack = objMenutable.readALLMenuTable4(4);
        final String[] detailsnack = objMenutable.readALLMenuTable4(2);

        AdapterSnack objMyAdapterSnack = new AdapterSnack(SnackMenu.this, strnamesnack,strpicsnack,strpricesnack);
        MenuListview.setAdapter(objMyAdapterSnack);
        MenuListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                name = strnamesnack;
                price = strpicsnack;
                image = strpricesnack;
                detail = detailsnack;
                Intent intent = new Intent(SnackMenu.this, DetailMenu.class);
                intent.putExtra("name", strnamesnack[position]);
                intent.putExtra("price",strpicsnack[position]);
                intent.putExtra("image",strpricesnack[position]);
                intent.putExtra("detail", detailsnack[position]);
                startActivity(intent);
            }
        });
    }

    public void Cdatabase(){ // Open DataBase
        objMenutable = new MenuTable(this);
    } // เปิดใช้งาน sqlite

    private void BindWidget() {
        MenuListview = (ListView) findViewById(R.id.livsnack);
    }

    public void onClickBack (View view){
        new Intent(SnackMenu.this,MainActivity.class);
        finish();
    }

    public void onClickcart(View view) {
        Intent intent = new Intent(SnackMenu.this, ConfirmOrder.class);
        startActivity(intent);
    }

}

