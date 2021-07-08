package com.example.sqdemo1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context, "medicine.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table medicine (name TEXT primary key,time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL("DROP TABLE IF EXISTS STUDENT");
    }

    public boolean insertData(String name,String usn,String email){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("name",name);
        contentValues.put("usn",usn);
        contentValues.put("email",email);

        long result = db.insert("STUDENT",null,contentValues);

        if(result==-1){
            return false;
        }
        else{
            return true;
        }

    }




    public boolean updateData(String name,String usn,String email){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("usn",usn);
        contentValues.put("email",email);

        Cursor c = db.rawQuery("select * from Student where name=?",new String[]{name});

        if(c.getCount()>0){
            long result = db.update("STUDENT",contentValues,"name=?",new String[]{name});

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }



    }

    public boolean deleteData(String name){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("select * from Student where name=?",new String[]{name});

        if(c.getCount()>0){
            long result = db.delete("STUDENT","name=?",new String[]{name});

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }

    }

    public Cursor readData(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("select * from Student",null);
        return c;

    }


















}
