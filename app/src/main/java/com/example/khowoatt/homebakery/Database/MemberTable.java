package com.example.khowoatt.homebakery.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by khowoatt on 8/29/2017.
 */

public class MemberTable {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSQLiteDataBase, readSQLiteDataBase;

    public static final String Member_TABLE = "membertable";
    public static final String Member_ID = "id_member";
    public static final String Member_USER = "user";
    public static final String Member_PASSWORD = "pass";
    public static final String Member_EMAIL = "email";
    public static final String Member_PHONE = "phone";
    public static final String Member_TYPE = "type";

    public MemberTable(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSQLiteDataBase = objMySQLiteOpenHelper.getWritableDatabase();
        readSQLiteDataBase = objMySQLiteOpenHelper.getReadableDatabase();
    }
    public long AddNewMember(String stridmember,String struser,String strpass,String stremail,String strphone,String strtype){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(objMySQLiteOpenHelper.Member_ID,stridmember);
        objContentValues.put(objMySQLiteOpenHelper.Member_USER,struser);
        objContentValues.put(objMySQLiteOpenHelper.Member_PASSWORD,strpass);
        objContentValues.put(objMySQLiteOpenHelper.Member_EMAIL,stremail);
        objContentValues.put(objMySQLiteOpenHelper.Member_PHONE,strphone);
        objContentValues.put(objMySQLiteOpenHelper.Member_TYPE,strtype);
        return readSQLiteDataBase.insert(objMySQLiteOpenHelper.Member_TABLE,null,objContentValues);
    }


    public String[] searchUSERPASSWORD(String strUser) {
        try {
            String[] strResult = null;
            Cursor objCursor = readSQLiteDataBase.query(Member_TABLE, new String[]{Member_ID, Member_USER,
                    Member_PASSWORD, Member_EMAIL, Member_PHONE, Member_TYPE}, Member_USER + "=?", new String[]{String.valueOf(strUser)},null,null,null,null);
            if (objCursor != null) {
                if (objCursor.moveToFirst()) {
                    strResult = new String[6];
                    for (int i = 0; i < 6; i++) {
                        strResult[i] = objCursor.getString(i);
                    }
                }
            }
            objCursor.close();
            return strResult;
        } catch (Exception e) {
            return null;
        }
    }
    public String[] readUSERPASSWORD(String strUser) {
        try {
            String[] strResult = null;
            Cursor objCursor = readSQLiteDataBase.query(Member_TABLE, new String[]{Member_USER,
                    Member_PASSWORD, Member_EMAIL, Member_PHONE, Member_TYPE}, Member_USER + "=?", new String[]{String.valueOf(strUser)},null,null,null,null);
            if (objCursor != null) {
                if (objCursor.moveToFirst()) {
                    strResult = new String[6];
                    for (int i = 0; i < 6; i++) {
                        strResult[i] = objCursor.getString(i);
                    }
                }
            }
            objCursor.close();
            return strResult;
        } catch (Exception e) {
            return null;
        }
    }

}
