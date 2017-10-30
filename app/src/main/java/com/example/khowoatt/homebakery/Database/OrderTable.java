package com.example.khowoatt.homebakery.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by khowoatt on 8/29/2017.
 */

public class OrderTable {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase;
    private SQLiteDatabase readSqLiteDatabase;

    public static final String Order_TABLE = "ordertable";
    public static final String Order_ID = "id_order";
    public static final String Order_ID_MEMBER = "id_member";
    public static final String Order_DATE = "date_order";
    public static final String Order_PRICE = "price_order";
    public static final String Order_STATUS = "status";

    public OrderTable(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }

    public long AddNewOrderTable(String id_order, String id_member, String date_order,String price_order,String status) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(objMySQLiteOpenHelper.Order_ID,id_order);
        objContentValues.put(objMySQLiteOpenHelper.Order_ID_MEMBER, id_member);
        objContentValues.put(objMySQLiteOpenHelper.Order_DATE, date_order);
        objContentValues.put(objMySQLiteOpenHelper.Order_PRICE, price_order);
        objContentValues.put(objMySQLiteOpenHelper.Order_STATUS, status);

        return readSqLiteDatabase.insert(objMySQLiteOpenHelper.Order_TABLE, null, objContentValues);
    }

    public String[] readALLOrderTable(int intColume){
        try {
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(Order_TABLE, new String[]{Order_ID,Order_ID_MEMBER,Order_DATE,Order_PRICE,Order_STATUS},null,null,null,null,null);
            if(objCursor != null){
                if(objCursor.moveToFirst()){
                    strResult = new String[5];
                    for(int i =0;i<5;i++){
                        strResult[i] = objCursor.getString(intColume);
                        objCursor.moveToNext();
                    }
                }
            }
            objCursor.close();
            return strResult;
        }catch (Exception e){
            return null;
        }
        //return new String[0];
    }

}
