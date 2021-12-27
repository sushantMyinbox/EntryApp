package com.mim.entryapp.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mim.entryapp.Activities.LoginActivity;
import com.mim.entryapp.Activities.SettingsActivity;
import com.mim.entryapp.R;
import com.mim.entryapp.SharedPreferenceManager;
import com.mim.entryapp.utils.SharedPrefsUtils;

public class NotificationsFragment extends Fragment {

    Context context;
    private ImageView iv_close;
    private TextView tv_shareApp,tv_logout, tv_rateUs;

    private String api_key = "FB4E63A6EE314B8B9485FAC4A2B71FCF";
    String phone;
    SharedPreferenceManager sharedPreferenceManager;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View v = inflater.inflate(R.layout.fragment_notifications, container, false);

        sharedPreferenceManager = new SharedPreferenceManager(getActivity().getApplicationContext());

        sharedPreferences = this.getActivity().getSharedPreferences("login",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        phone = SharedPrefsUtils.getStringPreference(getActivity(),"mobile");
        api_key = SharedPrefsUtils.getStringPreference(getActivity(),"key");

        tv_logout = v.findViewById(R.id.tv_logout);
        tv_logout.setOnClickListener(v1 -> logoutUser());

//        final TextView textView = root.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
////            public void onChanged(@Nullable String s) {
////                textView.setText(s);
////            }
//        });
        return v;
    }
    private void logoutUser() {
        sharedPreferenceManager.logout();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        Toast.makeText(context, "You have been logged out", Toast.LENGTH_SHORT).show();
    }
}