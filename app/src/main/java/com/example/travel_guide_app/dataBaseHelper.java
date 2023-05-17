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
        sqLiteDatabase.execSQL("CREATE TABLE DESTINATION(CITY TEXT PRIMARY KEY  NOT NULL  , COUNTRY TEXT, CONTINENT TEXT, LONGITUDE TEXT, LATITUDE TEXT, COST TEXT,IMG TEXT,DESCRIPTION TEXT)");
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
    //--------------------------------------------------------------------
    public Cursor getAllInformation_ofOne_preferredContinent(String city_prefContinent){
        String[] selection = {city_prefContinent};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        String query = "SELECT * FROM DESTINATION WHERE CITY= +'"+city_prefContinent+"'";
        return sqLiteDatabase.rawQuery("SELECT * FROM DESTINATION WHERE CITY=?", selection);

//        return sqLiteDatabase.rawQuery(query, null);
    }

    //-------------------------------------------------------------------------------
    //this is fuction to insert the destination to data base helper
    public void insertDestination(Destination destination) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        System.out.println("city = " +destination.getCity());

        contentValues.put("CITY", destination.getCity());
        contentValues.put("COUNTRY", destination.getCountry());
        contentValues.put("CONTINENT", destination.getContinent());
        contentValues.put("LONGITUDE", destination.getLongitude());
        contentValues.put("LATITUDE", destination.getLatitude());
        contentValues.put("COST", destination.getCost());
        contentValues.put("IMG", destination.getImg());
        contentValues.put("DESCRIPTION", destination.getDescription());

        long res =sqLiteDatabase.insert("DESTINATION", null, contentValues);
        System.out.println("res = " +res);
    }

    public Cursor getAll() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM DESTINATION", null);

    }
    //    --------------------------------------------------------
    public Cursor getCitesByGivenContinent(String cont) {
        String[] selectionArgs = {cont};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT CITY FROM DESTINATION WHERE CONTINENT=?", selectionArgs);
    }





}

