package com.moutamid.energyconsumptiontracking;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moutamid.energyconsumptiontracking.databinding.ActivityMainBinding;
import com.moutamid.energyconsumptiontracking.fragments.HomeFragment;
import com.moutamid.energyconsumptiontracking.fragments.SettingsFragment;
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
        binding.bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
                    return true;
                } else if (item.getItemId() == R.id.nav_stat) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new StatsFragment()).commit();
                    return true;
                } else if (item.getItemId() == R.id.nav_setting) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new SettingsFragment()).commit();
                    return true;
                }
                return false;
            }
        });
        binding.bottomNav.setSelectedItemId(R.id.nav_home);
    }
}