package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mim.entryapp.R;
import com.mim.entryapp.models.Data;
import com.mim.entryapp.models.RegistrationResult;
import com.mim.entryapp.models.SaveRegistrationModel;
import com.mim.entryapp.retrofit.RetrofitClient;
import com.mim.entryapp.utils.SharedPrefsUtils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "sushant" ;
    Context context;
    private TextView tv_signIn,tv_tnc;
    private Button btnSubmit;
    private EditText et_name,et_mobile,et_email;
    private CheckBox checkbox_tc;
    private String s_key ="89e1c93e0cb3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_tnc = findViewById(R.id.tv_tnc);
        tv_tnc.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
            View parentView = getLayoutInflater().inflate(R.layout.bottom_sheet_layout,null);
            bottomSheetDialog.setContentView(parentView);
//                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(parentView.getParent());
//                bottomSheetBehavior.setPeekHeight(int); TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,getResources().getDisplayMetrics());
            bottomSheetDialog.show();
        });

        init();
        listener();
    }

    private void init(){
        context = this;
        btnSubmit = findViewById(R.id.btnSubmit);
        et_name = findViewById(R.id.et_name);
        et_mobile = findViewById(R.id.et_mobile);
        et_email = findViewById(R.id.et_email);
        checkbox_tc = findViewById(R.id.checkbox_tc);
        tv_signIn = findViewById(R.id.tv_signIn);
        tv_tnc = findViewById(R.id.tv_tnc);
    }

    private void listener() {
        btnSubmit.setOnClickListener(this);
        et_name.setOnClickListener(this);
        et_mobile.setOnClickListener(this);
        et_email.setOnClickListener(this);
        checkbox_tc.setOnClickListener(this);
        tv_signIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnSubmit:
                // validating if the text field is empty or not
                final String email = et_email.getText().toString();
                if (!isValidEmail(email)){
                    et_email.setError("Invalid email");
                }

                final String name = et_name.getText().toString();
                if (name.isEmpty()){
                    et_name.setError("Enter a valid name");
                }

                final String mobile = et_mobile.getText().toString();
                if (!isValidMobileNumber(mobile)){
                    et_mobile.setError("Enter a valid mobile number");
                }

                    SaveRegistrationModel saveRegistrationModel = new SaveRegistrationModel();
                    saveRegistrationModel.setEmailId(et_email.getText().toString());
                    saveRegistrationModel.setMobile(et_mobile.getText().toString());
                    saveRegistrationModel.setName(et_name.getText().toString());
                    saveRegistrationModel.setSecret(s_key);
                    apiCall(saveRegistrationModel);

                    onCheckBoxClicked();
//                    Intent intent = new Intent(MainActivity.this,OtpActivity.class);
//                    intent.putExtra("phone",et_mobile.getText().toString());
//                    startActivity(intent);
                    return;

            case R.id.tv_signIn:
                startActivity(new Intent(MainActivity.this,LoginActivity.class));

        }
    }

    //validating mobile id
    private boolean isValidMobileNumber(String mobile) {
        if(!Pattern.matches("[a-zA-Z]+", mobile)) {
            return mobile.length() > 6 && mobile.length() <= 10;
        }
        return false;
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void apiCall(SaveRegistrationModel saveRegistrationModel) {
        RetrofitClient.getApiService().createUser(saveRegistrationModel,"application/json").enqueue(new Callback<RegistrationResult>() {
            @Override
            public void onResponse(Call<RegistrationResult> call, Response<RegistrationResult> response) {
                if(response.isSuccessful()){
                   Data data =  response.body().getData();

                    if(data.getResult().equals("success")){
                        SharedPrefsUtils.setStringPreference(getApplicationContext(),"mobile",""+et_mobile.getText().toString());

                        Intent intent = new Intent(MainActivity.this,OtpActivity.class);
                       intent.putExtra("phone",et_mobile.getText().toString());
//
                       startActivity(intent);
                   } else {
                        Toast.makeText(context, "This number is already registered", Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<RegistrationResult> call, Throwable t) {
                Log.i("sushant", "onFailure: "+t.getLocalizedMessage());
                Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void onCheckBoxClicked(){
        if (checkbox_tc.isChecked()){
        } else {
            Toast.makeText(context, "Please click the checkbox", Toast.LENGTH_SHORT).show();

        }

    }
}