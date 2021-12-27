package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.mim.entryapp.R;
import com.mim.entryapp.models.Data;
import com.mim.entryapp.models.OtpData;
import com.mim.entryapp.models.OtpResult;
import com.mim.entryapp.models.OtpVerifyModel;
import com.mim.entryapp.retrofit.RetrofitClient;
import com.mim.entryapp.utils.SharedPrefsUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnVerify;
    private PinView pinView;
    private TextView tv_mobile_num,tv_timer, tv_resendOtp;
    Context context;
    private String api_key = "FB4E63A6EE314B8B9485FAC4A2B71FCF";
    private String s_key ="89e1c93e0cb3";
    String phone;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        tv_mobile_num = findViewById(R.id.tv_mobile_num);
        phone = getIntent().getStringExtra("phone");
        tv_mobile_num.setText(phone);
        SharedPrefsUtils.setStringPreference(this,"phone",phone);

        tv_resendOtp = findViewById(R.id.tv_resendOtp);

        tv_timer = findViewById(R.id.tv_timer);
        new CountDownTimer(179900,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                NumberFormat f = new DecimalFormat("00");

                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                tv_timer.setText(f.format(min)+ ":" + f.format(sec)+ " sec ");
            }

            @Override
            public void onFinish() {
                tv_timer.setText("Resend OTP");
            }
        }.start();

         sharedPreferences = getSharedPreferences("login",Context.MODE_PRIVATE);


        init();
        listener();


    }

    private void init() {
        context = this;
        pinView = findViewById(R.id.pinView);
        btnVerify = findViewById(R.id.btnVerify);

    }

    private void listener() {
        btnVerify.setOnClickListener(this);
        pinView.setOnClickListener(this);
        tv_mobile_num.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(OtpActivity.this, CongratulationActivity.class));

        if (v.getId() == R.id.btnVerify) {
            OtpVerifyModel otpVerifyModel = new OtpVerifyModel();
            otpVerifyModel.setOtp(pinView.getText().toString());
            otpVerifyModel.setApiKey(api_key);
            otpVerifyModel.setSecret(s_key);
            otpVerifyModel.setMobile(phone);
//            otpVerifyApi(otpVerifyModel);
            return;
        }
    }

    private void otpVerifyApi(OtpVerifyModel otpVerifyModel) {
        RetrofitClient.getApiService().verifyOtp(otpVerifyModel, "application/json").enqueue(new Callback<OtpResult>() {
            @Override
            public void onResponse(Call<OtpResult> call, Response<OtpResult> response) {
                if (response.isSuccessful()){
                    Data otpData =  response.body().getData();
                    if(otpData.getResult().equals( "Verified successfully")) {
                        Log.i("sushant", "result is:" + response.body().toString());
                        startActivity(new Intent(OtpActivity.this, CongratulationActivity.class));

                    }
                    else {
                        Toast.makeText(context, "Enter the correct otp", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<OtpResult> call, Throwable t) {
                Log.i("sushant", "onFailure: "+t.getLocalizedMessage());
                Toast.makeText(context, "Enter the correct otp", Toast.LENGTH_SHORT).show();


            }
        });
    }
}