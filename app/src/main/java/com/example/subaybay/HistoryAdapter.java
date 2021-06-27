package com.example.subaybay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.subaybay.dto.ScannedByUserByEstablisment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private List<ScannedByUserByEstablisment> histories;

    public HistoryAdapter(Context context){
        this.context = context;
    }

    public void setHistories(List<ScannedByUserByEstablisment> histories) {
        this.histories = histories;
    }

    @NonNull
    @NotNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HistoryAdapter.ViewHolder holder, int position) {
        holder.dateNTime.setText(histories.get(position).getDateScanned());
        holder.establisment.setText(histories.get(position).getScanByWhatEstablihsment().getEstablishmentName());
        holder.temperature.setText(histories.get(position).getTemperature()+" Celsius");
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView dateNTime, establisment, temperature;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            dateNTime = itemView.findViewById(R.id.txtdate);
            establisment = itemView.findViewById(R.id.txtestablishment);
            temperature = itemView.findViewById(R.id.txttemperature);
        }
    }
}
