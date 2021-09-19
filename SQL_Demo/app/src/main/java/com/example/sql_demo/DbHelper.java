package com.example.sql_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context, "Contacts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String qry = "CREATE TABLE CONTACTS_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,PHONE TEXT,EMAIL TEXT)";
        db.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContact(String name,String phone,String email){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("NAME",name);
        cv.put("PHONE",phone);
        cv.put("EMAIL",email);

        long res = db.insert("CONTACTS_TABLE",null,cv);

        if(res==-1){
            Log.e("MSG", "addContact: ERROR");
        }
        else{
            Log.d("MSG", "addContact: SUCCESS");
        }

    }

    public Cursor fetchData(){
        SQLiteDatabase db = this.getReadableDatabase();

        String qry = "SELECT * FROM CONTACTS_TABLE";

        Cursor c = db.rawQuery(qry,null);

        return c;
    }





}
