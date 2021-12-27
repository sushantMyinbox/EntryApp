package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mim.entryapp.R;
import com.mim.entryapp.models.LoginResult;
import com.mim.entryapp.retrofit.RetrofitClient;

import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText et_mobile;
    private Button btnSubmit;
    private TextView tv_create_account, tv_admin, tv_login_email;
    private String api_key = "FB4E63A6EE314B8B9485FAC4A2B71FCF";
    String phone;
    Context context;
    private List<LoginResult> logoutResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        et_mobile.addTextChangedListener(loginTextWatcher);

        tv_login_email = findViewById(R.id.tv_login_email);
        tv_login_email.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this,Email_loginActivity.class)));

        btnSubmit.setOnClickListener(v -> {
            String number = et_mobile.getText().toString();
            if (!number.isEmpty()) {
                getLoginApi(number);
            } else {
                Toast.makeText(context, "Please enter the mobile number", Toast.LENGTH_SHORT).show();
            }
        });

        tv_admin.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this,AdminTabActivity.class));

        });

        tv_create_account.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this,MainActivity.class)));
    }

    private void init() {
        context = this;
        et_mobile = findViewById(R.id.et_mobile);
        btnSubmit = findViewById(R.id.btnSubmit);
        tv_create_account = findViewById(R.id.tv_create_account);
        tv_admin = findViewById(R.id.tv_admin);
    }

    private void getLoginApi(String number) {
        RetrofitClient.getApiService().getLoginResult(api_key, number).enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.isSuccessful()) {
                    LoginResult loginResult = response.body();
                    if(loginResult.getData().getResult().equals("success")){
                        Intent intent = new Intent(LoginActivity.this,OtpActivity.class);
                        intent.putExtra("phone",number);
                        startActivity(intent);
                    } else {
                        Toast.makeText(context, "This number is not registered", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {

            }
        });
    }

    //validating mobile id
//    private boolean isValidMobileNumber(String mobile) {
//        if(!Pattern.matches("[a-zA-Z]+", mobile)) {
//            return mobile.length() > 6 && mobile.length() <= 10;
//        }
//        return false;
//    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String mobileNum = et_mobile.getText().toString().trim();
            btnSubmit.setEnabled(!mobileNum.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}