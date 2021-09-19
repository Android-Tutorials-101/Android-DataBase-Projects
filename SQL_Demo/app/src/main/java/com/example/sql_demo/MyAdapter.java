package com.example.sql_demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter <MyAdapter.MyViewHolder> {

    ArrayList<DataModel> myData;


    public MyAdapter(ArrayList<DataModel> myData) {
        this.myData = myData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.card_design,parent,false);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.n.setText(myData.get(position).name);
        holder.e.setText(myData.get(position).email);
        holder.p.setText(myData.get(position).phone);

    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView n,p,e;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            n = itemView.findViewById(R.id.name);
            p = itemView.findViewById(R.id.phone);
            e = itemView.findViewById(R.id.email);

        }
    }

}
