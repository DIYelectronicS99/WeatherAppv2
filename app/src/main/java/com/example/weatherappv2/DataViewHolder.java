package com.example.weatherappv2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataViewHolder extends RecyclerView.ViewHolder {

    TextView temperature_mean, precipitation, pressure, month;
    public DataViewHolder(@NonNull View itemView) {
        super(itemView);
        temperature_mean = itemView.findViewById(R.id.wtv1);
        pressure = itemView.findViewById(R.id.wtv2);
        precipitation = itemView.findViewById(R.id.wtv3);
        month = itemView.findViewById(R.id.wtv4);
    }
}
