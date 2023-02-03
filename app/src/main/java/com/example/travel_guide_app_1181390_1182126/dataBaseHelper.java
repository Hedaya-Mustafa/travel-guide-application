package com.example.travel_guide_app_1181390_1182126;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

public class dataBaseHelper extends android.database.sqlite.SQLiteOpenHelper {
    public dataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE TRAVELLER(EMAIL TEXT PRIMARY KEY  NOT NULL , FIRSTNAME TEXT, LASTNAME TEXT, PASSWORD TEXT, GCONFIRM TEXT, PREFERRED TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertTraveller(Traveller traveller) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("ID", traveller.getId());
        contentValues.put("EMAIL", traveller.getEmail());
        contentValues.put("FIRSTNAME", traveller.getFname());
        contentValues.put("LASTNAME", traveller.getLname());
        contentValues.put("PASSWORD", traveller.getPassword());
        contentValues.put("GCONFIRM", traveller.getConfirmpassword());
        contentValues.put("PREFERRED", traveller.getPreferred());
        sqLiteDatabase.insert("TRAVELLER", null, contentValues);
    }

    public Cursor getAllTravellers() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TRAVELLER", null);
    }
    //----------------------------check_email function ----------------------------------
    public boolean check_email(String email) {
        String[] selectionArgs = {email};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TRAVELLER WHERE EMAIL=?", selectionArgs);
        int count = cursor.getCount();
        cursor.close();
        close();

        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
    //-----------------------------------------------------------------------------------
    public boolean check_User_Exist(String email, String password) {
        String[] selectionArgs = {email, password};

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TRAVELLER WHERE EMAIL=? and PASSWORD =?", selectionArgs);
        int count = cursor.getCount();
        cursor.close();
        close();

        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }



}

