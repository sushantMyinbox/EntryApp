package com.mim.entryapp.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.mim.entryapp.R;
import com.mim.entryapp.models.AddressResult;
import com.mim.entryapp.models.Data;
import com.mim.entryapp.models.SaveVisitorModel;
import com.mim.entryapp.retrofit.RetrofitClient;
import com.mim.entryapp.ui.home.HomeFragment;
import com.mim.entryapp.utils.SharedPrefsUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mim.entryapp.Activities.CommunityHallActivity.BASE_URL;

public class AddVisitorActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private Button btn_add_visitor, btn_share;
    private ImageView iv_history;
    private EditText et_visitor_name, et_visitor_mobile,et_coming_from,et_child,et_adult,et_vehicle, et_expected_time,
            et_expected_date;
    Context context;

    private SharedPreferences.Editor editor;

    final Calendar myCalendar = Calendar.getInstance();

    private String api_key = "FB4E63A6EE314B8B9485FAC4A2B71FCF";
    String phone;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visitor);

        sharedPreferences = getSharedPreferences("login",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        phone = SharedPrefsUtils.getStringPreference(this,"mobile");


        context = this;

        iv_history = findViewById(R.id.iv_history);
        iv_history.setOnClickListener(v -> {
            startActivity(new Intent(AddVisitorActivity.this,VisitorHistoryActivity.class));
        });

        et_expected_date = findViewById(R.id.et_expected_date);
        et_expected_date.setOnClickListener(v -> new DatePickerDialog(AddVisitorActivity.this, date1, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        et_expected_time = findViewById(R.id.et_expected_time);
        et_expected_time.setOnClickListener(v -> {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(AddVisitorActivity.this, (timePicker, selectedHour, selectedMinute) ->
                    et_expected_time.setText( selectedHour + ":" + selectedMinute), hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        });

        et_visitor_mobile = findViewById(R.id.et_visitor_mobile);
        et_visitor_name = findViewById(R.id.et_visitor_name);
        et_coming_from = findViewById(R.id.et_coming_from);
        et_child = findViewById(R.id.et_child);
        et_adult = findViewById(R.id.et_adult);
        et_vehicle = findViewById(R.id.et_vehicle);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> onBackPressed());

        btn_add_visitor = findViewById(R.id.btn_add_visitor);
        btn_add_visitor.setOnClickListener(v -> {

//            postSaveVisitorApi();

            SaveVisitorModel saveVisitorModel = new SaveVisitorModel();
            saveVisitorModel.setApikey(api_key);
            saveVisitorModel.setMobileNo(phone);
            saveVisitorModel.setVisitorName(et_visitor_name.getText().toString());
            saveVisitorModel.setVistorMobile(et_visitor_mobile.getText().toString());
            saveVisitorModel.setComingFrom(et_coming_from.getText().toString());
            saveVisitorModel.setVisitDate(et_expected_date.getText().toString());
            saveVisitorModel.setVisitTime(et_expected_time.getText().toString());
            saveVisitorModel.setAdult(et_adult.getText().toString());
            saveVisitorModel.setChild(et_child.getText().toString());
            saveVisitorModel.setVehicle(et_vehicle.getText().toString());
            saveVisitorPostApi(saveVisitorModel);

            AlertDialog alertDialog = new AlertDialog.Builder(AddVisitorActivity.this).create(); //Read Update

//            btn_share = findViewById(R.id.btn_share);

//            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//            sharingIntent.setType("text/plain");
//            String shareBody = "Here is the share content body";
//            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
//            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
//            startActivity(Intent.createChooser(sharingIntent, "Share via"));


            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.getWindow().setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(alertDialog.getWindow().getAttributes());
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            alertDialog.getWindow().setAttributes(layoutParams);


            final View customLayout = getLayoutInflater().inflate(R.layout.visitor_added_item, null);
            alertDialog.setView(customLayout);

            alertDialog.show();  //<-- See This!
        });
            }

//    private void postSaveVisitorApi(){
//
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("apiKey",api_key);
//            jsonObject.put( "visitorName",et_visitor_name.getText().toString());
//            jsonObject.put( "vistorMobile",et_visitor_mobile.getText().toString());
//            jsonObject.put( "visitDate",et_expected_date.getText().toString());
//            jsonObject.put( "visitTime",et_expected_time.getText().toString());
//            jsonObject.put( "comingFrom",et_coming_from.getText().toString());
//            jsonObject.put( "adult",et_adult.getText().toString());
//            jsonObject.put( "child",et_child.getText().toString());
//            jsonObject.put( "vehicle",et_vehicle.getText().toString());
//            jsonObject.put( "mobile",phone);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        AndroidNetworking.post( BASE_URL+ "api/societyApp/SaveVisitor")
//                .addJSONObjectBody(jsonObject)
//                .setTag("Sushant")
//                .setPriority(Priority.HIGH)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.e("res",response.toString());
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        Log.e("res",anError.getResponse().toString());
//
//                    }
//
//                });
//    }

            private void saveVisitorPostApi(SaveVisitorModel saveVisitorModel){
                RetrofitClient.getApiService().saveVisitor(saveVisitorModel,"application/json").enqueue(new Callback<AddressResult>() {
                    @Override
                    public void onResponse(Call<AddressResult> call, Response<AddressResult> response) {
                        if (response.isSuccessful()){
                            Data addressData = response.body().getData();
                            if (addressData.getResult() == "success")
                                Log.i("sushant", "onResponse: "+response.errorBody());
//                            startActivity(new Intent(AddVisitorActivity.this, HomeFragment.class));
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

    DatePickerDialog.OnDateSetListener date1 = (view, year, month, dayOfMonth) -> {
        // TODO Auto-generated method stub
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, month);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel1();
    };
    private void updateLabel1() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et_expected_date.setText(sdf.format(myCalendar.getTime()));
    }
}