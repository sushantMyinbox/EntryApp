package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mim.entryapp.R;

import java.util.Calendar;

public class GymActivity extends AppCompatActivity {

    EditText et_time_from, et_time_to;
    Button btn_submit;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);

        et_time_to = findViewById(R.id.et_time_to);
        et_time_from = findViewById(R.id.et_time_from);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> onBackPressed());

        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(v -> Toast.makeText(GymActivity.this, "Time slot have been booked", Toast.LENGTH_SHORT).show());

        et_time_to.setOnClickListener(v -> {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(GymActivity.this, (timePicker, selectedHour, selectedMinute) ->
                    et_time_to.setText( selectedHour + ":" + selectedMinute), hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        });

        et_time_from.setOnClickListener(v -> {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(GymActivity.this, (timePicker, selectedHour, selectedMinute) ->
                    et_time_from.setText( selectedHour + ":" + selectedMinute), hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        });


    }
}