package com.moutamid.energyconsumptiontracking.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fxn.stash.Stash;
import com.moutamid.energyconsumptiontracking.Constants;
import com.moutamid.energyconsumptiontracking.R;
import com.moutamid.energyconsumptiontracking.databinding.ActivityAddBinding;
import com.moutamid.energyconsumptiontracking.model.AppliancesModel;

import java.util.ArrayList;
import java.util.UUID;

public class AddActivity extends AppCompatActivity {
    ActivityAddBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int index = getIntent().getIntExtra("pos",-1);

        binding.toolbar.back.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        ArrayList<AppliancesModel> list = Stash.getArrayList(Constants.APPLIANCES, AppliancesModel.class);

        if (index != -1) {
            binding.toolbar.title.setText("Update Appliance");
            AppliancesModel appliance = list.get(index);
            binding.name.getEditText().setText(appliance.getName());
            binding.number.getEditText().setText(String.valueOf(appliance.getNumber()));
            binding.power.getEditText().setText(String.valueOf(appliance.getPower()));
            binding.mon.getEditText().setText(String.valueOf(appliance.getHours().get(0)));
            binding.tue.getEditText().setText(String.valueOf(appliance.getHours().get(1)));
            binding.wed.getEditText().setText(String.valueOf(appliance.getHours().get(2)));
            binding.thu.getEditText().setText(String.valueOf(appliance.getHours().get(3)));
            binding.fri.getEditText().setText(String.valueOf(appliance.getHours().get(4)));
            binding.sat.getEditText().setText(String.valueOf(appliance.getHours().get(5)));
            binding.sun.getEditText().setText(String.valueOf(appliance.getHours().get(6)));
            binding.units.getEditText().setText(String.valueOf(appliance.getNumberOfUnits()));
        } else {
            binding.toolbar.title.setText("New Appliance");
        }

        binding.save.setOnClickListener(v -> {
            if (valid()) {
                if (index != -1) {
                    AppliancesModel model = list.get(index);
                    model.setName(binding.name.getEditText().getText().toString());
                    model.setNumber(Integer.parseInt(binding.number.getEditText().getText().toString()));
                    ArrayList<Double> hours = new ArrayList<>();
                    hours.add(Double.parseDouble(binding.mon.getEditText().getText().toString()));
                    hours.add(Double.parseDouble(binding.tue.getEditText().getText().toString()));
                    hours.add(Double.parseDouble(binding.wed.getEditText().getText().toString()));
                    hours.add(Double.parseDouble(binding.thu.getEditText().getText().toString()));
                    hours.add(Double.parseDouble(binding.fri.getEditText().getText().toString()));
                    hours.add(Double.parseDouble(binding.sat.getEditText().getText().toString()));
                    hours.add(Double.parseDouble(binding.sun.getEditText().getText().toString()));
                    model.setHours(hours);
                    model.setPower(Double.parseDouble(binding.power.getEditText().getText().toString()));
                    model.setNumberOfUnits(Double.parseDouble(binding.units.getEditText().getText().toString()));
                    list.set(index, model);
                    Toast.makeText(this, "Appliance Updated", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayList<Double> hours = new ArrayList<>();
                    hours.add(Double.parseDouble(binding.mon.getEditText().getText().toString()));
                    hours.add(Double.parseDouble(binding.tue.getEditText().getText().toString()));
                    hours.add(Double.parseDouble(binding.wed.getEditText().getText().toString()));
                    hours.add(Double.parseDouble(binding.thu.getEditText().getText().toString()));
                    hours.add(Double.parseDouble(binding.fri.getEditText().getText().toString()));
                    hours.add(Double.parseDouble(binding.sat.getEditText().getText().toString()));
                    hours.add(Double.parseDouble(binding.sun.getEditText().getText().toString()));
                    AppliancesModel model = new AppliancesModel(
                            UUID.randomUUID().toString(),
                            binding.name.getEditText().getText().toString(),
                            Integer.parseInt(binding.number.getEditText().getText().toString()),
                            hours,
                            Double.parseDouble(binding.power.getEditText().getText().toString()),
                            Double.parseDouble(binding.units.getEditText().getText().toString())
                    );
                    list.add(model);
                    Toast.makeText(this, "Appliance Added", Toast.LENGTH_SHORT).show();
                }
                Stash.put(Constants.APPLIANCES, list);
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }

    private boolean valid() {
        if (binding.name.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(this, "Name is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.power.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(this, "Power is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.mon.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(this, "Monday is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.tue.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(this, "Tuesday is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.wed.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(this, "Wednesday is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.thu.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(this, "Thursday is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.fri.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(this, "Friday is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.sat.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(this, "Saturday is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.sun.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(this, "Sunday is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.units.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(this, "Units is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}