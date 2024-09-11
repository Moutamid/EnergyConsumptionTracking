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

        binding.toolbar.back.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
        binding.toolbar.title.setText("New Appliance");

        ArrayList<AppliancesModel> list = Stash.getArrayList(Constants.APPLIANCES, AppliancesModel.class);

        int index = getIntent().getIntExtra("pos",-1);
        if (index != -1) {
            AppliancesModel appliance = list.get(index);
            binding.name.getEditText().setText(appliance.getName());
            binding.power.getEditText().setText(String.valueOf(appliance.getPower()));
            binding.hour.getEditText().setText(String.valueOf(appliance.getHours()));
            binding.units.getEditText().setText(String.valueOf(appliance.getNumberOfUnits()));
        }

        binding.save.setOnClickListener(v -> {
            if (valid()) {
                if (index != -1) {
                    AppliancesModel model = list.get(index);
                    model.setName(binding.name.getEditText().getText().toString());
                    model.setHours(Double.parseDouble(binding.hour.getEditText().getText().toString()));
                    model.setPower(Double.parseDouble(binding.power.getEditText().getText().toString()));
                    model.setNumberOfUnits(Double.parseDouble(binding.units.getEditText().getText().toString()));
                    list.set(index, model);
                    Toast.makeText(this, "Appliance Updated", Toast.LENGTH_SHORT).show();
                } else {
                    AppliancesModel model = new AppliancesModel(
                            UUID.randomUUID().toString(),
                            binding.name.getEditText().getText().toString(),
                            Double.parseDouble(binding.hour.getEditText().getText().toString()),
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
        if (binding.hour.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(this, "Hour is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.units.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(this, "Units is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}