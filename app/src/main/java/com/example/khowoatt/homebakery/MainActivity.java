package com.example.khowoatt.homebakery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.khowoatt.homebakery.Database.MemberTable;
import com.example.khowoatt.homebakery.Database.MenuTable;
import com.example.khowoatt.homebakery.Database.OrderTable;
import com.example.khowoatt.homebakery.Database.OrderlistTable;

public class MainActivity extends AppCompatActivity {
    private MemberTable objMemberTable;
    private MenuTable objMenuTable;
    private OrderTable objOrderTable;
    private OrderlistTable objOrderListTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cdatabase();
    }
    private void Cdatabase() {//open database
        objMemberTable = new MemberTable(this);
        objMenuTable = new MenuTable(this);
        objOrderTable = new OrderTable(this);
        objOrderListTable = new OrderlistTable(this);
    }
    public void onClickPastry (View view){
        Intent intent = new Intent(MainActivity.this,PastryMenu.class);
        startActivity(intent);
    }//ลิ้งไปหน้า Menu pastry
    public void onClickCake (View view){
        Intent intent= new Intent(MainActivity.this,CakeMenu.class);
        startActivity(intent);
    }//ลิ้งไปหน้า Menu cake
    public void onClickSnack (View view){
        Intent intent= new Intent(MainActivity.this,SnackMenu.class);
        startActivity(intent);
    }//ลิ้งไปหน้า Menu snack
    public void onClickBread (View view){
        Intent intent= new Intent(MainActivity.this,BreadMenu.class);
        startActivity(intent);
    }//ลิ้งไปหน้า Menu snack
    public void onClickMenu (View view){
        new Intent(MainActivity.this,MainMenu.class);
        finish();
    }//ลิ้งไปหน้า Menu snack

    public void onClickcart(View view) {
        Intent intent = new  Intent(MainActivity.this, ConfirmOrder.class);
        startActivity(intent);
    }
}
