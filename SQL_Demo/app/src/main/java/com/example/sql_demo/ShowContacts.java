package com.example.sql_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class ShowContacts extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<DataModel> myData = new ArrayList<>();

    DbHelper db = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contacts);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor c = db.fetchData();

        while(c.moveToNext()){

            DataModel dm = new DataModel(c.getString(1),c.getString(2),c.getString(3));
            myData.add(dm);

        }

        MyAdapter myAdapter = new MyAdapter(myData);

        recyclerView.setAdapter(myAdapter);



    }
}