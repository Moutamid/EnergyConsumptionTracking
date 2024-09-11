package com.moutamid.energyconsumptiontracking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.energyconsumptiontracking.R;
import com.moutamid.energyconsumptiontracking.model.AppliancesModel;

import java.util.ArrayList;

public class StastAdapter extends RecyclerView.Adapter<StastAdapter.ApplianceVH> {
    Context context;
    ArrayList<AppliancesModel> list;
    int period;
    double cost;

    public StastAdapter(Context context, ArrayList<AppliancesModel> list, int period, double cost) {
        this.context = context;
        this.list = list;
        this.cost = cost;
        this.period = period;
    }

    @NonNull
    @Override
    public ApplianceVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ApplianceVH(LayoutInflater.from(context).inflate(R.layout.appliances_stats, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ApplianceVH holder, int position) {
        AppliancesModel model = list.get(holder.getAdapterPosition());
        holder.name.setText(model.getName());
        holder.hour.setText(model.getHours() + " H");
        holder.power.setText(model.getPower() + " W");
        double consumption = model.calculateEnergyConsumption(period);
        holder.consumption.setText(String.format("%.2f", consumption) + " kWh");
        double value = consumption * cost;
        holder.cost.setText("$" + String.format("%.2f", value));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ApplianceVH extends RecyclerView.ViewHolder {
        TextView name, power, hour, consumption, cost;

        public ApplianceVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            power = itemView.findViewById(R.id.power);
            hour = itemView.findViewById(R.id.hour);
            consumption = itemView.findViewById(R.id.consumption);
            cost = itemView.findViewById(R.id.cost);
        }
    }

}
