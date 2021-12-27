package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mim.entryapp.BuildConfig;
import com.mim.entryapp.R;
import com.mim.entryapp.SharedPreferenceManager;
import com.mim.entryapp.utils.SharedPrefsUtils;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    private ImageView iv_close;
    private TextView tv_shareApp,tv_logout, tv_rateUs;

    private String api_key = "FB4E63A6EE314B8B9485FAC4A2B71FCF";
    String phone;
    SharedPreferenceManager sharedPreferenceManager;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());

        sharedPreferences = getSharedPreferences("login",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        init();
        listener();

        phone = SharedPrefsUtils.getStringPreference(this,"mobile");
        api_key = SharedPrefsUtils.getStringPreference(this,"key");
    }

    private void init() {
        context = this;
        iv_close = findViewById(R.id.iv_close);
        tv_logout = findViewById(R.id.tv_logout);
        tv_shareApp = findViewById(R.id.tv_shareApp);
        tv_rateUs = findViewById(R.id.tv_rateUs);



    }

    private void listener() {
        iv_close.setOnClickListener(this);
        tv_logout.setOnClickListener(this);
        tv_shareApp.setOnClickListener(this);
        tv_rateUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_close:
                onBackPressed();
                break;

            case R.id.tv_logout:
//                getLogoutApi();
                logoutUser();
                break;
            case R.id.tv_shareApp:
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT,
//                        "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
//                sendIntent.setType("text/plain");
//                startActivity(sendIntent);
                break;
            case R.id.tv_rateUs:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=PackageName")));

        }

    }

    private void logoutUser() {
        sharedPreferenceManager.logout();
        Intent intent = new Intent(SettingsActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        Toast.makeText(context, "You have been logged out", Toast.LENGTH_SHORT).show();
    }


 }