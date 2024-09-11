package com.moutamid.energyconsumptiontracking.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fxn.stash.Stash;
import com.google.android.material.button.MaterialButton;
import com.moutamid.energyconsumptiontracking.Constants;
import com.moutamid.energyconsumptiontracking.R;
import com.moutamid.energyconsumptiontracking.model.AppliancesModel;
import com.moutamid.energyconsumptiontracking.ui.AddActivity;

import java.util.ArrayList;

public class ApplianceAdapter extends RecyclerView.Adapter<ApplianceAdapter.ApplianceVH> {
    Context context;
    ArrayList<AppliancesModel> list;

    public ApplianceAdapter(Context context, ArrayList<AppliancesModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ApplianceVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ApplianceVH(LayoutInflater.from(context).inflate(R.layout.appliances_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ApplianceVH holder, int position) {
        AppliancesModel model = list.get(holder.getAdapterPosition());
        holder.name.setText(model.getName());
        holder.hour.setText(model.getHours() + " H");
        holder.power.setText(model.getPower() + " W");
        holder.consumption.setText(String.format("%.2f", model.calculateEnergyConsumption(-1)) + " kWh");

        holder.edit.setOnClickListener(v -> {
            context.startActivity(new Intent(context, AddActivity.class).putExtra("pos", holder.getAdapterPosition()));
        });

        holder.delete.setOnClickListener(v -> {
            list.remove(holder.getAdapterPosition());
            Stash.put(Constants.APPLIANCES, list);
            notifyItemRemoved(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ApplianceVH extends RecyclerView.ViewHolder {
        TextView name, power, hour, consumption;
        MaterialButton edit, delete;

        public ApplianceVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            power = itemView.findViewById(R.id.power);
            hour = itemView.findViewById(R.id.hour);
            consumption = itemView.findViewById(R.id.consumption);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }

}
