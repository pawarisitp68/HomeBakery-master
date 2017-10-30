package com.example.khowoatt.homebakery.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by oatza on 10/5/2017.
 */

public class Basketcheck {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String Basketcheck = "basket";
    public static final String Id_menu = "id_menu";
    public static final String Name_menu = "name_menu";
    public static final String Amount = "amount";
    public static final String Total = "total";

    public Basketcheck(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();

    }

}
