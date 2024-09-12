package com.moutamid.energyconsumptiontracking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

        double consumption0 = model.calculateEnergyConsumption(-1, model.getHours().get(0));
        double consumption1 = model.calculateEnergyConsumption(-1, model.getHours().get(1));
        double consumption2 = model.calculateEnergyConsumption(-1, model.getHours().get(2));
        double consumption3 = model.calculateEnergyConsumption(-1, model.getHours().get(3));
        double consumption4 = model.calculateEnergyConsumption(-1, model.getHours().get(4));
        double consumption5 = model.calculateEnergyConsumption(-1, model.getHours().get(5));
        double consumption6 = model.calculateEnergyConsumption(-1, model.getHours().get(6));

        if (period == 1) {
            holder.daily.setVisibility(View.VISIBLE);
            holder.weekly.setVisibility(View.GONE);
            holder.monthly.setVisibility(View.GONE);

            holder.costMonday.setText("$" + String.format("%.2f", (consumption0 * cost)));
            holder.costTuesday.setText("$" + String.format("%.2f", (consumption1 * cost)));
            holder.costWednesday.setText("$" + String.format("%.2f", (consumption2 * cost)));
            holder.costThursday.setText("$" + String.format("%.2f", (consumption3 * cost)));
            holder.costFriday.setText("$" + String.format("%.2f", (consumption4 * cost)));
            holder.costSaturday.setText("$" + String.format("%.2f", (consumption5 * cost)));
            holder.costSunday.setText("$" + String.format("%.2f", (consumption6 * cost)));
        } else {
            double sum = consumption0 + consumption1 + consumption2 + consumption3 + consumption4 + consumption5 + consumption6;
            if (period == 7) {
                holder.daily.setVisibility(View.GONE);
                holder.weekly.setVisibility(View.VISIBLE);
                holder.monthly.setVisibility(View.GONE);
                double value = sum * cost;
                holder.costWeekly.setText("$" + String.format("%.2f", value));
            } else if (period == 30) {
                holder.daily.setVisibility(View.GONE);
                holder.weekly.setVisibility(View.GONE);
                holder.monthly.setVisibility(View.VISIBLE);
                double value = (sum * 4) * cost;
                holder.costMonthly.setText("$" + String.format("%.2f", value));
            } else {
                holder.daily.setVisibility(View.GONE);
                holder.weekly.setVisibility(View.GONE);
                holder.monthly.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ApplianceVH extends RecyclerView.ViewHolder {
        TextView name, power, cost, number;
        TextView mon, tue, wed, thu, fri, sat, sun;
        TextView monconsumption, tueconsumption, wedconsumption, thuconsumption, friconsumption, satconsumption, sunconsumption;
        TextView costMonday, costTuesday, costWednesday, costThursday, costFriday, costSaturday, costSunday, costWeekly, costMonthly;
        LinearLayout daily, weekly, monthly;

        public ApplianceVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            power = itemView.findViewById(R.id.power);
            number = itemView.findViewById(R.id.number);
            cost = itemView.findViewById(R.id.cost);

            daily = itemView.findViewById(R.id.daily);
            weekly = itemView.findViewById(R.id.weeklyLayout);
            monthly = itemView.findViewById(R.id.monthlyLayout);

            costWeekly = itemView.findViewById(R.id.costWeekly);
            costMonthly = itemView.findViewById(R.id.costMonthly);

            mon = itemView.findViewById(R.id.mon);
            tue = itemView.findViewById(R.id.tue);
            wed = itemView.findViewById(R.id.wed);
            thu = itemView.findViewById(R.id.thu);
            fri = itemView.findViewById(R.id.fri);
            sat = itemView.findViewById(R.id.sat);
            sun = itemView.findViewById(R.id.sun);

            costMonday = itemView.findViewById(R.id.costMonday);
            costTuesday = itemView.findViewById(R.id.costTuesday);
            costWednesday = itemView.findViewById(R.id.costWednesday);
            costThursday = itemView.findViewById(R.id.costThursday);
            costFriday = itemView.findViewById(R.id.costFriday);
            costSaturday = itemView.findViewById(R.id.costSaturday);
            costSunday = itemView.findViewById(R.id.costSunday);

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
