package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mim.entryapp.Adapter.ResidentStatusAdapter;
import com.mim.entryapp.R;
import com.mim.entryapp.models.LoginResult;
import com.mim.entryapp.models.ResidentListModel;
import com.mim.entryapp.models.ResidentResponse;
import com.mim.entryapp.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResidentListActivity extends AppCompatActivity {

    public ImageButton backBtn;
    private TextView tv_call, call;
    private AutoCompleteTextView et_tower_name, et_flat_no;

    private String api_key = "FB4E63A6EE314B8B9485FAC4A2B71FCF";

    String phone;

//    ResidentStatusAdapter customAdapter;

    private EditText searchBar;

    List<ResidentResponse.DataBean.ResidentListBean> list = new ArrayList<>();

    private RecyclerView recyclerView_resident;
    private ArrayList<ResidentListModel> residentListModelArrayList;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_list);

        context = this;

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> onBackPressed());

        recyclerView_resident = findViewById(R.id.rv_resident_list);

//        getResidentListApi(phone);


        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_resident.setLayoutManager(linearLayoutManager);
        getResidentListApi();

        String[] flat_no = {"E101", "E102", "E103"};
        et_flat_no = findViewById(R.id.et_flat_no);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, flat_no);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        et_flat_no.setAdapter(adapter1);

        String[] tower = {"A", "B", "C"};
        et_tower_name = findViewById(R.id.et_tower_name);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, tower);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        et_tower_name.setAdapter(adapter);

        searchBar = findViewById(R.id.searchBar);

//        searchBar.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                filter(s.toString());
//            }
//        });

//        call = findViewById(R.id.call);
//        call.setOnClickListener(v -> {
//            Intent intent = new Intent(Intent.ACTION_DIAL);
//            intent.setData(Uri.parse("tel:"));
//            startActivity(intent);
//        });
//
//        tv_call = findViewById(R.id.tv_call);
//        tv_call.setOnClickListener(v -> {
//            Intent intent = new Intent(Intent.ACTION_DIAL);
//            intent.setData(Uri.parse("tel:0899889299"));
//            startActivity(intent);
//        });
    }

//    private void filter(String text){
//        ArrayList<ResidentResponse.DataBean.ResidentListBean> filterList = new ArrayList<>();
//        for (ResidentResponse.DataBean.ResidentListBean items : list) {
//            if (items.getName().toLowerCase().contains(text.toLowerCase())){
//                filterList.add(items);
//            }
//        }
//        customAdapter.filterList(filterList);
//    }

    private void getResidentListApi() {
        RetrofitClient.getApiService().getResidentList(api_key, "").enqueue(new Callback<ResidentResponse>() {
            @Override
            public void onResponse(Call<ResidentResponse> call, Response<ResidentResponse> response) {
                if (response.isSuccessful()) {
                    ResidentResponse residentResponse = response.body();

                    Log.d("onResponse: ", residentResponse.toString() + ":" + call.toString());
                    ResidentStatusAdapter residentStatusAdapter = new ResidentStatusAdapter(ResidentListActivity.this, residentResponse.getData().getResidentList());

                    recyclerView_resident.setAdapter(residentStatusAdapter);

                } else {
                    Toast.makeText(context, "Invalid Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResidentResponse> call, Throwable t) {
                Log.d("onFailure: ", call.toString());
            }
        });
    }

//    private void filter(String text) {
//        List<ResidentListModel> filterList = new ArrayList<>();
//        for (ResidentListModel items : list) {
//            if (items.getName().toLowerCase().contains(text.toLowerCase()));
//            filterList.add(items)
//        }
//
//    }
}