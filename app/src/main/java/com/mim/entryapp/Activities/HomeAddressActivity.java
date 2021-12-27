package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mim.entryapp.HomeScreenActivity;
import com.mim.entryapp.R;

public class HomeAddressActivity extends AppCompatActivity {
    private final int Splash_time = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_address);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(HomeAddressActivity.this, HomeScreenActivity.class));
            HomeAddressActivity.this.finish();

        },Splash_time);
    }
}