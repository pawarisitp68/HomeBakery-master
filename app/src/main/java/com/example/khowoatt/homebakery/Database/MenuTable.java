package com.example.khowoatt.homebakery.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by khowoatt on 8/29/2017.
 */

public class MenuTable {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase;
    private SQLiteDatabase readSqLiteDatabase;


    public static final String Menu_TABLE = "menutable";
    public static final String Menu_ID = "id_menu";
    public static final String Menu_NAME = "name_menu";
    public static final String Menu_DETAIL = "detail_menu";
    public static final String Menu_PRICE = "price_menu";
    public static final String Menu_PICTURE = "picture_menu";
    public static final String Menu_TYPE = "type";

    public MenuTable(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }

    public long AddNewMenuTable(String id_menu,String name_menu, String detail_menu, String price_menu,String picture_menu,String type) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(objMySQLiteOpenHelper.Menu_ID,id_menu);
        objContentValues.put(objMySQLiteOpenHelper.Menu_NAME, name_menu);
        objContentValues.put(objMySQLiteOpenHelper.Menu_DETAIL, detail_menu);
        objContentValues.put(objMySQLiteOpenHelper.Menu_PRICE, price_menu);
        objContentValues.put(objMySQLiteOpenHelper.Menu_PICTURE, picture_menu);
        objContentValues.put(objMySQLiteOpenHelper.Menu_TYPE, type);
        return readSqLiteDatabase.insert(objMySQLiteOpenHelper.Menu_TABLE, null, objContentValues);
    }
    public String[] readALLMenuTable4(int int4) {
        try {
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(Menu_TABLE,
                    new String[]{Menu_ID,Menu_NAME,Menu_DETAIL,Menu_PRICE,Menu_PICTURE,Menu_TYPE},Menu_TYPE+"='สแน็คบ็อกซ์'",null,null,null,null,null);
            if(objCursor != null){
                if(objCursor.moveToFirst()){
                    int num = objCursor.getCount();
                    strResult = new String[num];
                    for(int i =0;i<num;i++){
                        strResult[i] = objCursor.getString(int4);
                        objCursor.moveToNext();
                    }
                }
            }
            objCursor.close();
            return strResult;
        }catch (Exception e){
            return null;
        }
    }//อ่านข้อมูลจากฐาน database
    public String[] readALLMenuTable3(int int3) {
        try {
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(Menu_TABLE,
                    new String[]{Menu_ID,Menu_NAME,Menu_DETAIL,Menu_PRICE,Menu_PICTURE,Menu_TYPE},Menu_TYPE+"='ขนมปัง'",null,null,null,null,null);
            if(objCursor != null){
                if(objCursor.moveToFirst()){
                    int num = objCursor.getCount();
                    strResult = new String[num];
                    for(int i =0;i<num;i++){
                        strResult[i] = objCursor.getString(int3);
                        objCursor.moveToNext();
                    }
                }
            }
            objCursor.close();
            return strResult;
        }catch (Exception e){
            return null;
        }
    }//อ่านข้อมูลจากฐาน database
    public String[] readALLMenuTable2(int int2) {
        try {
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(Menu_TABLE,
                    new String[]{Menu_ID,Menu_NAME,Menu_DETAIL,Menu_PRICE,Menu_PICTURE,Menu_TYPE},Menu_TYPE+"='ขนมอบ'",null,null,null,null,null);
            if(objCursor != null){
                if(objCursor.moveToFirst()){
                    int num = objCursor.getCount();
                    strResult = new String[num];
                    for(int i =0;i<num;i++){
                        strResult[i] = objCursor.getString(int2);
                        objCursor.moveToNext();
                    }
                }
            }
            objCursor.close();
            return strResult;
        }catch (Exception e){
            return null;
        }
    }//อ่านข้อมูลจากฐาน database
    public String[] readALLMenuTable1(int int1){
        try {
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(Menu_TABLE,
                    new String[]{Menu_ID,Menu_NAME,Menu_DETAIL,Menu_PRICE,Menu_PICTURE,Menu_TYPE},Menu_TYPE+"='เค้ก'",null,null,null,null,null);
            if(objCursor != null){
                if(objCursor.moveToFirst()){
                    int num = objCursor.getCount();
                    strResult = new String[num];
                    for(int i =0;i<num;i++){
                        strResult[i] = objCursor.getString(int1);
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
    }//อ่านข้อมูลจากฐาน database


}
