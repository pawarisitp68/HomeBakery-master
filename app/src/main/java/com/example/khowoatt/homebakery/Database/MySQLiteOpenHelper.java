package com.example.khowoatt.homebakery.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.os.StrictMode;
import android.util.Log;

/**
 *
 * Created by khowoatt on 8/29/2017.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper{

    private final String TAG = getClass().getSimpleName();
    private SQLiteDatabase sqLiteDatabase;

    public static final String DataBase_Name = "Homebakery.db";
    public static final int DataBase_Version = 1;

    public static final String Member_TABLE = "membertable";
    public static final String Member_ID = "id_member";
    public static final String Member_USER = "user";
    public static final String Member_PASSWORD = "pass";
    public static final String Member_EMAIL = "email";
    public static final String Member_PHONE = "phone";
    public static final String Member_TYPE = "type";
    public String CREATE_MEMBER_TABLE ="create table "+Member_TABLE+" ("+Member_ID+" text primary key, " + " "+Member_USER+" text, "
            +Member_PASSWORD+" text, "+Member_EMAIL+" text, "+Member_PHONE+" text, "+Member_TYPE+" text);";

    public static final String Menu_TABLE = "menutable";
    public static final String Menu_ID = "id_menu";
    public static final String Menu_NAME = "name_menu";
    public static final String Menu_DETAIL = "detail_menu";
    public static final String Menu_PRICE = "price_menu";
    public static final String Menu_PICTURE = "picture_menu";
    public static final String Menu_TYPE = "type";
    private static final String CREATE_MENU_TABLE = "create table "+Menu_TABLE+" ("+Menu_ID+" integer primary key," +
            " "+Menu_NAME+" text,"+Menu_DETAIL+" text, "+Menu_PRICE+" text, "+Menu_PICTURE+" text, "+Menu_TYPE+" text);";

    public static final String Order_TABLE = "ordertable";
    public static final String Order_ID = "id_order";
    public static final String Order_ID_MEMBER = "id_member";
    public static final String Order_DATE = "date_order";
    public static final String Order_PRICE = "price_order";
    public static final String Order_STATUS = "status";
    private static final String CREATE_ORDER_TABLE = "create table "+Order_TABLE+" ("+Order_ID+" text primary key," +
            " "+Order_ID_MEMBER+" text, "+Order_DATE+" text, "+Order_PRICE+" text, "+Order_STATUS+" text);";

    public static final String OrderList_TABLE = "orderlisttable";
    public static final String OrderList_ID_ORDER = "id_order";
    public static final String OrderList_ID_MENU = "id_menu";
    public static final String OrderList_AMOUNT = "amount";
    public static final String OrderLIST_TOTAL = "total";
    private static final String CREATE_ORDERLIST_TABLE = "create table "+OrderList_TABLE+" ("+OrderList_ID_ORDER+" integer primary key," +
            " "+OrderList_ID_MENU+" text,"+OrderList_AMOUNT+" text, "+OrderLIST_TOTAL+" text);";

    /*public static final String Basketcheck = "basket";
    public static final String Id_menu = "id_menu";
    public static final String Name_menu = "name_menu";
    public static final String Amount = "amount";
    public static final String Total = "total";
    private static final String CREATE_BASKET_TABLE = "create table "+Basketcheck+" ("+Id_menu+" integer primary key," +
            " "+Name_menu+" text,"+Amount+" text, "+Total+" text);";*/



    public MySQLiteOpenHelper(Context context) {
        super(context, DataBase_Name, null, DataBase_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, CREATE_MEMBER_TABLE);
        Log.i(TAG, CREATE_MENU_TABLE);
        Log.i(TAG, CREATE_ORDER_TABLE);
        Log.i(TAG, CREATE_ORDERLIST_TABLE);
        //Log.i(TAG, CREATE_BASKET_TABLE);
        //db.execSQL(CREATE_BASKET_TABLE);
        db.execSQL(CREATE_MEMBER_TABLE);
        db.execSQL(CREATE_MENU_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);
        db.execSQL(CREATE_ORDERLIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_MEMBER_TABLE = "DROP TABLE IF EXISTS " + Member_TABLE;

        db.execSQL(DROP_MEMBER_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);
    }
}
