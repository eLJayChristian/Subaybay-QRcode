package com.example.subaybay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.subaybay.model.Address;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private List<Address> listOfAddress;
    private Context context;

    public AddressAdapter(Context context){
        this.context = context;
    }

    public void setListOfAddress(List<Address> listOfAddress) {
        this.listOfAddress = listOfAddress;
    }

    @NonNull
    @NotNull
    @Override
    public AddressAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_address, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AddressAdapter.ViewHolder holder, int position) {
        holder.region.setText(listOfAddress.get(position).getRegion());
        holder.province.setText(listOfAddress.get(position).getProvince());
        holder.city.setText(listOfAddress.get(position).getCity());
        holder.barangay.setText(listOfAddress.get(position).getBarangay());
//
//        holder.editBtn.setOnClickListener(v -> {
//            Toast.makeText(context, String.valueOf(listOfAddress.get(position).getUser().getId()), Toast.LENGTH_SHORT).show();
//        });
    }

    @Override
    public int getItemCount() {
        return listOfAddress.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView region, province, city, barangay;
//        private Button editBtn;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            region = itemView.findViewById(R.id.txtRegion);
            province = itemView.findViewById(R.id.txtProvince);
            city = itemView.findViewById(R.id.txtCity);
            barangay = itemView.findViewById(R.id.txtBarangay);

//            editBtn = itemView.findViewById(R.id.btnEdit);
        }
    }
}
