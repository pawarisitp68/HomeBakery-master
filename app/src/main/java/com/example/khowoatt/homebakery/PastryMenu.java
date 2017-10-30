package com.example.khowoatt.homebakery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.khowoatt.homebakery.Adapter.AdapterPastry;
import com.example.khowoatt.homebakery.Database.MenuTable;

public class PastryMenu extends AppCompatActivity {
    private MenuTable objMenutable;
    private ListView MenuListview;
    private String[] name,price,image,detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastry_menu);

        BindWidget();
        Cdatabase();
        createListView();
    }
    public void Cdatabase(){ // Open DataBase
        objMenutable = new MenuTable(this);
    } // เปิดใช้งาน sqlite

    private void createListView() {
        //MenuTable objCasephone = new MenuTable(this);

        final String[] strC_Name = objMenutable.readALLMenuTable1(1);
        final String[] strC_Pic = objMenutable.readALLMenuTable1(3);
        final String[] strC_Price = objMenutable.readALLMenuTable1(4);
        final String[] detailpastry = objMenutable.readALLMenuTable1(2);

        AdapterPastry objMyAdapterpastry = new AdapterPastry(PastryMenu.this, strC_Name,strC_Price,strC_Pic);
        MenuListview.setAdapter(objMyAdapterpastry);
        MenuListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                name = strC_Name;
                price = strC_Pic;
                image = strC_Price;
                detail = detailpastry;

                Intent intent = new Intent(PastryMenu.this, DetailMenu.class);

                intent.putExtra("name", strC_Name[position]);
                intent.putExtra("price",strC_Pic[position]);
                intent.putExtra("image",strC_Price[position]);
                intent.putExtra("detail", detailpastry[position]);
                startActivity(intent);

            }
        });

    }

    private void BindWidget() {
        MenuListview = (ListView) findViewById(R.id.livpastry);
    }


    public void onClickBack (View view){
        new Intent(PastryMenu.this,MainActivity.class);
        finish();
    }//ลิ้งไปหน้า Menu snack

    public void onClickcart(View view) {
        Intent intent = new Intent(PastryMenu.this, ConfirmOrder.class);
        startActivity(intent);
    }

}

