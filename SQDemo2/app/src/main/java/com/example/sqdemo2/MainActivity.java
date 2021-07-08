package com.example.sqdemo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText eventName;
    CalendarView calendarView;
    Button saveBtn;

    String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(this);

        eventName = findViewById(R.id.eventName);
        saveBtn = findViewById(R.id.saveBtn);
        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {



            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = Integer.toString(year)+Integer.toString(month)+Integer.toString(dayOfMonth);

                SQLiteDatabase db = dbHelper.getReadableDatabase();

                Cursor cursor = db.rawQuery("Select * from events where date=?",new String[]{selectedDate});

                if(cursor.getCount()==0){
                    Toast.makeText(MainActivity.this, "No events on this date 🕝", Toast.LENGTH_SHORT).show();
                }

                else{
                    StringBuffer events = new StringBuffer();

                    while(cursor.moveToNext()){
                        events.append(cursor.getString(1)+"\n\n");

                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage(events);
                    builder.show();
                }




            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String event = eventName.getText().toString();

                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();

                contentValues.put("date",selectedDate);
                contentValues.put("event",event);

                long res = db.insert("events",null,contentValues);

                if(res == -1) {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
                }

            }
        });





    }
}