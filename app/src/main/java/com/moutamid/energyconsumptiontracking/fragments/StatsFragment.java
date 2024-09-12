package com.moutamid.energyconsumptiontracking.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fxn.stash.Stash;
import com.moutamid.energyconsumptiontracking.Constants;
import com.moutamid.energyconsumptiontracking.adapters.StastAdapter;
import com.moutamid.energyconsumptiontracking.databinding.FragmentStatsBinding;
import com.moutamid.energyconsumptiontracking.model.AppliancesModel;

import java.util.ArrayList;

public class StatsFragment extends Fragment {
    FragmentStatsBinding binding;

    public StatsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStatsBinding.inflate(getLayoutInflater(), container, false);

        binding.rc.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rc.setHasFixedSize(false);

        binding.calculate.setOnClickListener(v -> {
            if (binding.cost.getEditText().getText().toString().isEmpty()) {
                Toast.makeText(requireContext(), "Cost is empty", Toast.LENGTH_SHORT).show();
            } else {
                InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                ArrayList<AppliancesModel> list = Stash.getArrayList(Constants.APPLIANCES, AppliancesModel.class);
                int period = binding.daily.isChecked() ? 1 :
                        binding.week.isChecked() ? 7 :
                        binding.month.isChecked() ? 30 : -1;
                double cost = Double.parseDouble(binding.cost.getEditText().getText().toString());
                StastAdapter adapter = new StastAdapter(requireContext(), list, period, cost);
                binding.rc.setAdapter(adapter);
            }
        });

        return binding.getRoot();
    }
}