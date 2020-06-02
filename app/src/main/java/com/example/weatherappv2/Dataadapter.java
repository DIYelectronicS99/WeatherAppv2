package com.example.weatherappv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Dataadapter extends RecyclerView.Adapter<DataViewHolder> {

    ArrayList<Datalist> list;
    Context context;

    public Dataadapter() {
    }

    public Dataadapter(ArrayList<Datalist> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new DataViewHolder(LayoutInflater.from(context).inflate(R.layout.rowlayout, parent, false ));
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

        Datalist newlist = list.get(position);
        holder.temperature_mean.setText(newlist.getTemperature_mean());
        holder.pressure.setText(newlist.getPressure());
        holder.month.setText(newlist.getMonth());
        holder.precipitation.setText(newlist.getPrecipitation());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
