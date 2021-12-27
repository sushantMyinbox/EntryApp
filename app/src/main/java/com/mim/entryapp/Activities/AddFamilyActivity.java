package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mim.entryapp.R;
import com.mim.entryapp.models.AddressResult;
import com.mim.entryapp.models.Data;
import com.mim.entryapp.models.FamilyModel;
import com.mim.entryapp.retrofit.RetrofitClient;
import com.mim.entryapp.ui.home.HomeFragment;
import com.mim.entryapp.utils.SharedPrefsUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFamilyActivity extends AppCompatActivity {

    private EditText et__family_mobile,et_family_name;
    private Button btn_save_family;
    private ImageButton backBtn;

    Context context;

    private SharedPreferences.Editor editor;

    private String api_key = "FB4E63A6EE314B8B9485FAC4A2B71FCF";
    String phone;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_family);

        context = this;

        sharedPreferences = getSharedPreferences("login",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        phone = SharedPrefsUtils.getStringPreference(this,"mobile");

        et_family_name = findViewById(R.id.et_family_name);
        et__family_mobile = findViewById(R.id.et__family_mobile);
        btn_save_family = findViewById(R.id.btn_save_family);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> onBackPressed());

        btn_save_family.setOnClickListener(v -> {

            String number = et__family_mobile.getText().toString();
            if (!number.isEmpty()){
                Toast.makeText(context, "Enter a valid number ", Toast.LENGTH_SHORT).show();
            }

            String name =et_family_name.getText().toString();
            if (!name.isEmpty()){
                Toast.makeText(context, "Enter the name", Toast.LENGTH_SHORT).show();
            }

            FamilyModel familyModel = new FamilyModel();
            familyModel.setApiKey(api_key);
            familyModel.setMobileNo(phone);
            familyModel.setMemberName(et_family_name.getText().toString());
            familyModel.setMemberPhone(et__family_mobile.getText().toString());
            saveFamilyMemberPostApi(familyModel);

        });


    }

    private void saveFamilyMemberPostApi(FamilyModel familyModel){
        RetrofitClient.getApiService().saveFamily(familyModel,"application/json").enqueue(new Callback<AddressResult>() {
            @Override
            public void onResponse(Call<AddressResult> call, Response<AddressResult> response) {
                if (response.isSuccessful()){
                    Data addressData = response.body().getData();
                    if (addressData.getResult() == "success");
                    Log.i("sushant", "onResponse: "+response.errorBody());
                    startActivity(new Intent(AddFamilyActivity.this, HomeFragment.class));
                    Toast.makeText(context, "Details have been saved", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddressResult> call, Throwable t) {
                Log.i("sushant", "onFailure: "+t.getLocalizedMessage());
                Toast.makeText(context, "Data is not saved", Toast.LENGTH_SHORT).show();

            }
        });

    }
}