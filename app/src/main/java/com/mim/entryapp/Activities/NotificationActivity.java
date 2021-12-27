package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.mim.entryapp.Adapter.NotificationAdapter;
import com.mim.entryapp.R;
import com.mim.entryapp.models.NotificationModel;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    ImageButton backBtn;
    Context context;
    private RecyclerView rv_notify;


    // Arraylist for storing data
    private ArrayList<NotificationModel> notificationModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        context = this;

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> onBackPressed());

        rv_notify = findViewById(R.id.rv_notify);

        notificationModelArrayList = new ArrayList<>();
        notificationModelArrayList.add(new NotificationModel("Sushant","There is a meeting in the club","22 Oct,2021"
        ,"12.30 PM"));
        notificationModelArrayList.add(new NotificationModel("John","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in sollicitudin magna, vitae dictum quam. Phasellus ut commodo leo, iaculis maximus odio.commodo leo, iaculis.","22 Oct,2021"
                ,"12.30 PM"));
        notificationModelArrayList.add(new NotificationModel("Jasmine","Myntra order has reached your place","22 Oct,2021"
                ,"12.30 PM"));
        notificationModelArrayList.add(new NotificationModel("Varun","Amazon order is standing at your doorstep","22 Oct,2021"
                ,"12.30 PM"));
        notificationModelArrayList.add(new NotificationModel("Lisha","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in sollicitudin magna, vitae dictum quam. Phasellus ut commodo leo, iaculis maximus odio.commodo leo, iaculis.","22 Oct,2021"
                ,"12.30 PM"));
        notificationModelArrayList.add(new NotificationModel("Taniya","Diwali mela celebrations","22 Oct,2021"
                ,"12.30 PM"));

        // we are initializing our adapter class and passing our arraylist to it.
        NotificationAdapter notificationAdapter = new NotificationAdapter(this, notificationModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        rv_notify.setLayoutManager(linearLayoutManager);
        rv_notify.setAdapter(notificationAdapter);
    }
}