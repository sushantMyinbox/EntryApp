package com.mim.entryapp.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.mim.entryapp.R;

public class SearchCityActivity extends AppCompatActivity {

    private Button btn_delhi;
    private ImageView iv_close;
    private ImageButton backBtn;
    Context context;
    ArrayAdapter<String> adapter;
    ListView listView;

    String[] names = {"Delhi","Faridabad", "Ghaziabad","Gurugram","Indrapuram", "Noida"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> onBackPressed());

        iv_close = findViewById(R.id.iv_close);
//        iv_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

        btn_delhi = findViewById(R.id.btn_delhi);
        btn_delhi.setOnClickListener(v -> {
            openDialog(v);

//                startActivity(new Intent(SearchCityActivity.this,AddHomeActivity.class));
        });
    }
    public void openDialog(View v){
        AlertDialog.Builder alertDialog = new
                AlertDialog.Builder(this);
        View rowList = getLayoutInflater().inflate(R.layout.city_item, null);
        listView = rowList.findViewById(R.id.listView);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String itemStr = adapter.getItem(position);
            startActivity(new Intent(SearchCityActivity.this,AddHomeActivity.class));
            Toast.makeText(SearchCityActivity.this, "" + names[position], Toast.LENGTH_SHORT).show();
        });
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        alertDialog.setView(rowList);
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }
}