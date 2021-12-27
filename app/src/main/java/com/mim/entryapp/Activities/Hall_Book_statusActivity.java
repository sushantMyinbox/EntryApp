package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mim.entryapp.R;
import com.mim.entryapp.models.LoginResult;
import com.mim.entryapp.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Hall_Book_statusActivity extends AppCompatActivity {

    TextView tv_book_date_from, tv_book_date_to, tv_remarks, tv_booked_for, tv_booking_no;
    ImageButton backBtn;

    private String api_key = "FB4E63A6EE314B8B9485FAC4A2B71FCF";

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall__book_status);

        context = this;

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> onBackPressed());

        tv_booking_no = findViewById(R.id.tv_booking_no);

        tv_book_date_from = findViewById(R.id.tv_book_date_from);
        tv_book_date_to = findViewById(R.id.tv_book_date_to);
        tv_remarks = findViewById(R.id.tv_remarks);
        tv_booked_for = findViewById(R.id.tv_booked_for);

        Intent intent = getIntent();

        String str = intent.getStringExtra("message");
        String str1 = intent.getStringExtra("message1");
        String str2 = intent.getStringExtra("message2");
        String str3 = intent.getStringExtra("message3");
        tv_book_date_from.setText(str);
        tv_book_date_to.setText(str1);
        tv_booked_for.setText(str2);
        tv_remarks.setText(str3);
    }

//    private void getBookingHallStatusApi(String number){
//        RetrofitClient.getApiService().getBookingStatus(api_key, number).enqueue(new Callback<LoginResult>() {
//            @Override
//            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
//                if (response.isSuccessful()){
//                    LoginResult loginResult1 = response.body();
//                    if (loginResult1.getData().getResult().equals("success"));
//                    Intent intent = new Intent(Hall_Book_statusActivity.this,Hall_Book_statusActivity.class);
//                    intent.putExtra("phone",number);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(context, "This number is not registered", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResult> call, Throwable t) {
//
//            }
//        });
//    }
}