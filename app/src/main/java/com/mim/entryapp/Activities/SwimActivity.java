package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mim.entryapp.R;
import com.mim.entryapp.ui.home.HomeFragment;

import java.util.Calendar;

public class SwimActivity extends AppCompatActivity {

    ImageButton backBtn;
    private EditText et_swim_time_from, et_swim_time_to;
    private Button btn_Swim_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swim);

        btn_Swim_submit = findViewById(R.id.btn_Swim_submit);
        btn_Swim_submit.setOnClickListener(v -> {
//                startActivity(new Intent(SwimActivity.this, HomeFragment.class));
            Toast.makeText(SwimActivity.this, "Your slot has been booked", Toast.LENGTH_SHORT).show();
        });

        et_swim_time_to = findViewById(R.id.et_swim_time_to);
        et_swim_time_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(SwimActivity.this, (timePicker, selectedHour, selectedMinute) ->
                        et_swim_time_to.setText( selectedHour + ":" + selectedMinute), hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        et_swim_time_from = findViewById(R.id.et_swim_time_from);
        et_swim_time_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(SwimActivity.this, (timePicker, selectedHour, selectedMinute) ->
                        et_swim_time_from.setText( selectedHour + ":" + selectedMinute), hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> onBackPressed());
    }
}