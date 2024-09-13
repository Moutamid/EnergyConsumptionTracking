package com.moutamid.energyconsumptiontracking.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
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

        setHourValidator(binding.mon.getEditText(), "Monday");
        setHourValidator(binding.tue.getEditText(), "Tuesday");
        setHourValidator(binding.wed.getEditText(), "Wednesday");
        setHourValidator(binding.thu.getEditText(), "Thursday");
        setHourValidator(binding.fri.getEditText(), "Friday");
        setHourValidator(binding.sat.getEditText(), "Saturday");
        setHourValidator(binding.sun.getEditText(), "Sunday");

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
                    ArrayList<Integer> hours = new ArrayList<>();
                    hours.add(Integer.parseInt(binding.mon.getEditText().getText().toString()));
                    hours.add(Integer.parseInt(binding.tue.getEditText().getText().toString()));
                    hours.add(Integer.parseInt(binding.wed.getEditText().getText().toString()));
                    hours.add(Integer.parseInt(binding.thu.getEditText().getText().toString()));
                    hours.add(Integer.parseInt(binding.fri.getEditText().getText().toString()));
                    hours.add(Integer.parseInt(binding.sat.getEditText().getText().toString()));
                    hours.add(Integer.parseInt(binding.sun.getEditText().getText().toString()));
                    model.setHours(hours);
                    model.setPower(Double.parseDouble(binding.power.getEditText().getText().toString()));
                    model.setNumberOfUnits(Double.parseDouble(binding.units.getEditText().getText().toString()));
                    list.set(index, model);
                    Toast.makeText(this, "Appliance Updated", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayList<Integer> hours = new ArrayList<>();
                    hours.add(Integer.parseInt(binding.mon.getEditText().getText().toString()));
                    hours.add(Integer.parseInt(binding.tue.getEditText().getText().toString()));
                    hours.add(Integer.parseInt(binding.wed.getEditText().getText().toString()));
                    hours.add(Integer.parseInt(binding.thu.getEditText().getText().toString()));
                    hours.add(Integer.parseInt(binding.fri.getEditText().getText().toString()));
                    hours.add(Integer.parseInt(binding.sat.getEditText().getText().toString()));
                    hours.add(Integer.parseInt(binding.sun.getEditText().getText().toString()));
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

    private boolean isValidHour(String input) {
        if (input.isEmpty()) return false;
        try {
            int value = Integer.parseInt(input);
            return value >= 0 && value <= 24;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void setHourValidator(EditText editText, String dayName) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if (!isValidHour(input)) {
                    editText.setError(dayName + " must be between 0 and 24");
                }
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

        EditText[] dayFields = {
                binding.mon.getEditText(),
                binding.tue.getEditText(),
                binding.wed.getEditText(),
                binding.thu.getEditText(),
                binding.fri.getEditText(),
                binding.sat.getEditText(),
                binding.sun.getEditText()
        };

        String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for (int i = 0; i < dayFields.length; i++) {
            String input = dayFields[i].getText().toString();
            if (input.isEmpty()) {
                dayFields[i].setError(dayNames[i] + " is empty");
                return false;
            }
        }

        for (int i = 0; i < dayFields.length; i++) {
            String input = dayFields[i].getText().toString();
            if (!isValidHour(input)) {
                dayFields[i].setError(dayNames[i] + " must be between 0 and 24");
                Toast.makeText(this, dayNames[i] + " has an invalid input", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        if (binding.units.getEditText().getText().toString().isEmpty()) {
            Toast.makeText(this, "Units is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}