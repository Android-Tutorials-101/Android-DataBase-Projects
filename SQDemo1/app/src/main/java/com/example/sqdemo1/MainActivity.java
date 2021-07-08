package com.example.sqdemo1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText medicineName,medicineTime;
    Button insertBtn,updateBtn,deleteBtn,viewBtn;

    DbHelper dbHelper = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medicineName = findViewById(R.id.medicineName);
        medicineTime = findViewById(R.id.medicineTime);

        insertBtn = findViewById(R.id.insertBtn);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        viewBtn = findViewById(R.id.viewBtn);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = medicineName.getText().toString();
                String time = medicineTime.getText().toString();

                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();

                contentValues.put("name",name);
                contentValues.put("time",time);

                long res = db.insert("medicine",null,contentValues);

                if(res>0){
                    Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = dbHelper.getWritableDatabase();

                String name = medicineName.getText().toString();
                String time = medicineTime.getText().toString();

                ContentValues contentValues = new ContentValues();

                contentValues.put("time",time);

                Cursor c = db.rawQuery("select * from medicine where name=?",new String[]{name});

                if(c.getCount()>0){
                    int res =db.update("medicine",contentValues,"name=?",new String[] {name});
                    if(res>0){
                        Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }



            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = medicineName.getText().toString();

                SQLiteDatabase db = dbHelper.getWritableDatabase();

                Cursor c = db.rawQuery("select * from medicine where name=?",new String[]{name});


                if(c.getCount()>0){
                    int res =db.delete("medicine","name=?",new String[] {name});

                    if(res>0){
                        Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }


            }
        });


        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = dbHelper.getReadableDatabase();

                Cursor cursor = db.rawQuery("select * from medicine",null);

                if(cursor.getCount()==0){
                    Toast.makeText(MainActivity.this, "No data", Toast.LENGTH_SHORT).show();
                }

                StringBuffer buffer = new StringBuffer();

                while(cursor.moveToNext()){
                    buffer.append("Tablet Name:"+cursor.getString(0)+"\n");
                    buffer.append("Time:"+cursor.getString(1)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });






    }
}