package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mim.entryapp.HomeScreenActivity;
import com.mim.entryapp.R;

public class CongratulationActivity extends AppCompatActivity {

    private final int Splash_time = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(CongratulationActivity.this, HomeScreenActivity.class));
            CongratulationActivity.this.finish();

        },Splash_time);
    }
}