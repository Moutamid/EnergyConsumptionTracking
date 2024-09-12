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
        holder.number.setText(String.valueOf(model.getNumber()));
        holder.power.setText(model.getPower() + " W");

        holder.mon.setText(model.getHours().get(0) + " H");
        holder.tue.setText(model.getHours().get(1) + " H");
        holder.wed.setText(model.getHours().get(2) + " H");
        holder.thu.setText(model.getHours().get(3) + " H");
        holder.fri.setText(model.getHours().get(4) + " H");
        holder.sat.setText(model.getHours().get(5) + " H");
        holder.sun.setText(model.getHours().get(6) + " H");

        holder.monconsumption.setText(String.format("%.2f", model.calculateEnergyConsumption(-1, model.getHours().get(0))));
        holder.tueconsumption.setText(String.format("%.2f", model.calculateEnergyConsumption(-1, model.getHours().get(1))));
        holder.wedconsumption.setText(String.format("%.2f", model.calculateEnergyConsumption(-1, model.getHours().get(2))));
        holder.thuconsumption.setText(String.format("%.2f", model.calculateEnergyConsumption(-1, model.getHours().get(3))));
        holder.friconsumption.setText(String.format("%.2f", model.calculateEnergyConsumption(-1, model.getHours().get(4))));
        holder.satconsumption.setText(String.format("%.2f", model.calculateEnergyConsumption(-1, model.getHours().get(5))));
        holder.sunconsumption.setText(String.format("%.2f", model.calculateEnergyConsumption(-1, model.getHours().get(6))));

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
        TextView name, power, number;
        TextView mon, tue, wed, thu, fri, sat, sun;
        TextView monconsumption, tueconsumption, wedconsumption, thuconsumption, friconsumption, satconsumption, sunconsumption;
        MaterialButton edit, delete;

        public ApplianceVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            power = itemView.findViewById(R.id.power);
            number = itemView.findViewById(R.id.number);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);

            mon = itemView.findViewById(R.id.mon);
            tue = itemView.findViewById(R.id.tue);
            wed = itemView.findViewById(R.id.wed);
            thu = itemView.findViewById(R.id.thu);
            fri = itemView.findViewById(R.id.fri);
            sat = itemView.findViewById(R.id.sat);
            sun = itemView.findViewById(R.id.sun);

            monconsumption = itemView.findViewById(R.id.monconsumption);
            tueconsumption = itemView.findViewById(R.id.tueconsumption);
            wedconsumption = itemView.findViewById(R.id.wedconsumption);
            thuconsumption = itemView.findViewById(R.id.thuconsumption);
            friconsumption = itemView.findViewById(R.id.friconsumption);
            satconsumption = itemView.findViewById(R.id.satconsumption);
            sunconsumption = itemView.findViewById(R.id.sunconsumption);
        }
    }

}
