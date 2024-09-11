package com.moutamid.energyconsumptiontracking;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.moutamid.energyconsumptiontracking.databinding.ActivityMainBinding;
import com.moutamid.energyconsumptiontracking.fragments.HomeFragment;
import com.moutamid.energyconsumptiontracking.fragments.StatsFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.checkApp(this);

        binding.bottomNav.setItemActiveIndicatorColor(ColorStateList.valueOf(getColor(R.color.colorPrimaryLight)));
        binding.bottomNav.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
                return true;
            } else if (item.getItemId() == R.id.nav_stat) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new StatsFragment()).commit();
                return true;
            }
            return false;
        });
        binding.bottomNav.setSelectedItemId(R.id.nav_home);
    }
}