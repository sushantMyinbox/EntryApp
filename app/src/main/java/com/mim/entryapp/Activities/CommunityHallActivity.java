package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.mim.entryapp.R;
import com.mim.entryapp.utils.SharedPrefsUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CommunityHallActivity extends AppCompatActivity {

    private EditText et_book_from, et_book_to, et_remarks;
    public ImageButton backBtn;
    AutoCompleteTextView sp_booking_for;
    private Button btn_submit;

    String phone;
    private SharedPreferences sharedPreferences;

    private String api_key = "FB4E63A6EE314B8B9485FAC4A2B71FCF";

    public static String BASE_URL="http://103.205.64.220:17250/societyAPI/";

    final Calendar myCalendar = Calendar.getInstance();

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_hall);

        context = this;

        phone = SharedPrefsUtils.getStringPreference(this,"mobile");

        et_remarks = findViewById(R.id.et_remarks);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> onBackPressed());

        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = et_book_from.getText().toString();
                String str1 = et_book_to.getText().toString();
                String str3 = et_remarks.getText().toString();
                String str2 = sp_booking_for.getText().toString();

                Intent intent =new Intent(getApplicationContext(),Hall_Book_statusActivity.class);
                intent.putExtra("message",str);
                intent.putExtra("message1",str1);
                intent.putExtra("message2",str2);
                intent.putExtra("message3",str3);
                startActivity(intent);

                postCommunityHallApi();
            }
        });

        String[] booking = { "Wedding", "Bachelor's Party" , "Birthday Party", "Personal Event", "Others"};
        sp_booking_for = findViewById(R.id.sp_booking_for);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, booking);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp_booking_for.setAdapter(adapter);

        et_book_to = findViewById(R.id.et_book_to);
        DatePickerDialog.OnDateSetListener date1 = (view, year, month, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel1();
        };
        et_book_to.setOnClickListener(v -> new DatePickerDialog(CommunityHallActivity.this, date1, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        et_book_from = findViewById(R.id.et_book_from);
        DatePickerDialog.OnDateSetListener date = (view, year, month, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };
        et_book_from.setOnClickListener(v ->
                new DatePickerDialog(CommunityHallActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());
    }

    private void updateLabel1() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et_book_to.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et_book_from.setText(sdf.format(myCalendar.getTime()));
    }

    private void postCommunityHallApi(){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("apiKey",api_key);
            jsonObject.put( "bookingFrom",et_book_from.getText().toString());
            jsonObject.put( "bookingTo",et_book_to.getText().toString());
            jsonObject.put( "bookingFor",sp_booking_for.getText().toString());
            jsonObject.put( "remark",et_remarks.getText().toString());
            jsonObject.put( "mobile",phone);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post( BASE_URL+ "api/societyApp/SaveBooking")
                .addJSONObjectBody(jsonObject)
                .setTag("Sushant")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("res",response.toString());
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("res",anError.getResponse().toString());

                    }

                });
    }
}