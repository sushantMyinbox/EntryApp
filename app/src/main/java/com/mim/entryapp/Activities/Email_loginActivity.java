package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mim.entryapp.R;
import com.mim.entryapp.models.LoginEmailResult;
import com.mim.entryapp.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Email_loginActivity extends AppCompatActivity {

    private TextView tv_create_account, tv_login_mobile;
    private Button btnEmail_Submit;
    private String api_key = "FB4E63A6EE314B8B9485FAC4A2B71FCF";
    private EditText et_email;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);

        context = this;

//        et_email.addTextChangedListener(emailLoginTextWatcher);

        et_email = findViewById(R.id.et_email);

        btnEmail_Submit = findViewById(R.id.btnEmail_Submit);
        btnEmail_Submit.setOnClickListener(v -> {
            String email = et_email.getText().toString();
            if (!email.isEmpty()) {
                getEmailLoginApi(email);
            } else {
                Toast.makeText(context, "Please enter the registered email id", Toast.LENGTH_SHORT).show();
            }
        });

        tv_login_mobile = findViewById(R.id.tv_login_mobile);
        tv_login_mobile.setOnClickListener(v ->
                startActivity(new Intent(Email_loginActivity.this,LoginActivity.class)));

        tv_create_account = findViewById(R.id.tv_create_account);
        tv_create_account.setOnClickListener(v ->
                startActivity(new Intent(Email_loginActivity.this,MainActivity.class)));
    }

    private void getEmailLoginApi(String email){
        RetrofitClient.getApiService().getEmailLoginResult(api_key, email).enqueue(new Callback<LoginEmailResult>() {
            @Override
            public void onResponse(Call<LoginEmailResult> call, Response<LoginEmailResult> response) {
                if (response.isSuccessful()){
                    LoginEmailResult loginEmailResult = response.body();
                    if (loginEmailResult.getEmailData().getResult().equals("success"));
                    Intent intent = new Intent(Email_loginActivity.this,OtpActivity.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                }else {
                    Toast.makeText(context, "This number is not registered", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginEmailResult> call, Throwable t) {

            }
        });
    }
    private TextWatcher emailLoginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String emailId = et_email.getText().toString().trim();
            btnEmail_Submit.setEnabled(!emailId.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}