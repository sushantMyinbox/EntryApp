package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mim.entryapp.R;

public class PayGasActivity extends AppCompatActivity {

    private Button btn_gail_gas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_gas);

        btn_gail_gas = findViewById(R.id.btn_gail_gas);
        btn_gail_gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayGasActivity.this,DummyActivity.class));
            }
        });
    }
}