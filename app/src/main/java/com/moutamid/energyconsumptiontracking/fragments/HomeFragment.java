package com.moutamid.energyconsumptiontracking.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fxn.stash.Stash;
import com.moutamid.energyconsumptiontracking.Constants;
import com.moutamid.energyconsumptiontracking.R;
import com.moutamid.energyconsumptiontracking.adapters.ApplianceAdapter;
import com.moutamid.energyconsumptiontracking.databinding.FragmentHomeBinding;
import com.moutamid.energyconsumptiontracking.model.AppliancesModel;
import com.moutamid.energyconsumptiontracking.ui.AddActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);

        binding.add.setOnClickListener(v -> startActivity(new Intent(requireContext(), AddActivity.class)));

        binding.rc.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rc.setHasFixedSize(false);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayList<AppliancesModel> list = Stash.getArrayList(Constants.APPLIANCES, AppliancesModel.class);
        ApplianceAdapter adapter = new ApplianceAdapter(requireContext(), list);
        binding.rc.setAdapter(adapter);
    }
}