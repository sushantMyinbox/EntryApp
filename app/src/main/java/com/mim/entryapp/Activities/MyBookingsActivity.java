package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mim.entryapp.Adapter.BookingStatusAdapter;
import com.mim.entryapp.Adapter.ResidentStatusAdapter;
import com.mim.entryapp.R;
import com.mim.entryapp.models.BookingStatusModel;
import com.mim.entryapp.models.BookingStatusResponse;
import com.mim.entryapp.models.LoginResult;
import com.mim.entryapp.models.ResidentResponse;
import com.mim.entryapp.retrofit.RetrofitClient;
import com.mim.entryapp.utils.SharedPrefsUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBookingsActivity extends AppCompatActivity {

    ImageButton backBtn;
    private RecyclerView rv_bookings;

    Context context;

    String phone;

    private String api_key = "FB4E63A6EE314B8B9485FAC4A2B71FCF";

    // Arraylist for storing data
    private ArrayList<BookingStatusModel> bookingStatusModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        context = this;

        phone = SharedPrefsUtils.getStringPreference(this,"mobile");

        rv_bookings = findViewById(R.id.rv_bookings);
        getBookingStatusApi();
        //        bookingStatusModelArrayList.add(new BookingStatusModel("1st October,2021","7th October,2021",
//                "Anniversary", "The place should be decorated very well","BK0005"));
//        bookingStatusModelArrayList.add(new BookingStatusModel("9th October,2021","10th October,2021",
//                "Bachelor's Party", "The place should be decorated very well","BK0006"));
//
//        bookingStatusModelArrayList.add(new BookingStatusModel("21st October,2021","27th October,2021",
//                "Wedding", "The place should be decorated very well","BK0007"));
//
//        bookingStatusModelArrayList.add(new BookingStatusModel("28th October,2021","7th October,2021",
//                "Others", "The place should be decorated very well","BK0008"));
//
//        bookingStatusModelArrayList.add(new BookingStatusModel("28th October,2021","7th October,2021",
//                "Birthday party", "The place should have balloons","BK0009"));


        // we are initializing our adapter class and passing our arraylist to it.
//        BookingStatusAdapter bookingStatusAdapter = new BookingStatusAdapter(this, bookingStatusModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        rv_bookings.setLayoutManager(linearLayoutManager);
//        rv_bookings.setAdapter(bookingStatusAdapter);


        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> onBackPressed());
    }

    private void getBookingStatusApi(){
        RetrofitClient.getApiService().getBookingStatus(api_key, "9773937533").enqueue(new Callback<BookingStatusResponse>() {
            @Override
            public void onResponse(Call<BookingStatusResponse> call, Response<BookingStatusResponse> response) {
                if (response.isSuccessful()){
                    BookingStatusResponse bookingStatusResponse = response.body();

                    Log.d( "onResponse: ",bookingStatusResponse.toString()+":"+ call.toString());
                    BookingStatusAdapter bookingStatusAdapter = new BookingStatusAdapter(MyBookingsActivity.this, bookingStatusResponse.getData().getBookingList());

                    rv_bookings.setAdapter(bookingStatusAdapter);

                } else {
                    Toast.makeText(context, "Invalid Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BookingStatusResponse> call, Throwable t) {
                Log.d( "onFailure: ",call.toString());
            }
        });
    }
}