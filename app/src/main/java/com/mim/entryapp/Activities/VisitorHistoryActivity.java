package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.mim.entryapp.Adapter.CarStatusAdapter;
import com.mim.entryapp.R;
import com.mim.entryapp.models.CarStatusModel;

import java.util.ArrayList;

public class VisitorHistoryActivity extends AppCompatActivity {

    private RecyclerView rv_car;
    private ImageButton backBtn;

    // ArrayList for storing data
    private ArrayList<CarStatusModel> courseModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_history);

        backBtn= findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> onBackPressed());

        rv_car = findViewById(R.id.rv_car);

        // here we have created new array list and added data to it.
        courseModelArrayList = new ArrayList<>();
        courseModelArrayList.add(new CarStatusModel("Sushant","2 Adult/0 child","Gurgoan, Sector 3",999999999,"HR 26 I 009"));
        courseModelArrayList.add(new CarStatusModel("John","3 Adult/0 child","Noida, Sector 3",989097908,"HR 51 F 009"));
        courseModelArrayList.add(new CarStatusModel("Prince","1 Adult/1 child","Faridabad, Sector 9",998978909,"HR 03 F 009"));
        courseModelArrayList.add(new CarStatusModel("Varun","4 Adult/3 child","Noida, Sector 9",999907909,"HR 51 F 009"));


        // we are initializing our adapter class and passing our arraylist to it.
        CarStatusAdapter carStatusAdapter = new CarStatusAdapter(this, courseModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        rv_car.setLayoutManager(linearLayoutManager);
        rv_car.setAdapter(carStatusAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeItem(carStatusAdapter));
        itemTouchHelper.attachToRecyclerView(rv_car);

    }
}