package com.mim.entryapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mim.entryapp.Activities.AddFamilyActivity;
import com.mim.entryapp.Activities.AddVisitorActivity;
import com.mim.entryapp.Activities.CommunityHallActivity;
import com.mim.entryapp.Activities.GymActivity;
import com.mim.entryapp.Activities.MyBookingsActivity;
import com.mim.entryapp.Activities.NotificationActivity;
import com.mim.entryapp.Activities.ParkingMapActivity;
import com.mim.entryapp.Activities.PayGasActivity;
import com.mim.entryapp.Activities.ResidentListActivity;
import com.mim.entryapp.Activities.SearchCityActivity;
import com.mim.entryapp.Activities.SettingsActivity;
import com.mim.entryapp.Activities.SwimActivity;
import com.mim.entryapp.R;

public class HomeFragment extends Fragment {

    private Button btn_add_home,btn_pay_gas_bill, btn_add_vehicle, btn_add_guest, btn_add_family, btn_resident,
            btn_community_hall, btn_gym, btn_swim_book;
    private ImageView iv_setting, iv_notification;

    private HomeViewModel homeViewModel;
    TextView tv_name, btn_my_bookings;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        btn_swim_book = v.findViewById(R.id.btn_swim_book);
        btn_swim_book.setOnClickListener(v112 -> startActivity(new Intent(getActivity(), SwimActivity.class)));

        btn_gym = v.findViewById(R.id.btn_gym);
        btn_gym.setOnClickListener(v111 -> startActivity(new Intent(getActivity(), GymActivity.class)));

        iv_notification = v.findViewById(R.id.iv_notification);
        iv_notification.setOnClickListener(v110 -> startActivity(new Intent(getActivity(), NotificationActivity.class)));

        btn_my_bookings = v.findViewById(R.id.btn_my_bookings);
        btn_my_bookings.setOnClickListener(v19 -> startActivity(new Intent(getActivity(), MyBookingsActivity.class)));

        btn_community_hall = v.findViewById(R.id.btn_community_hall);
        btn_community_hall.setOnClickListener(v18 -> startActivity(new Intent(getActivity(), CommunityHallActivity.class)));

        btn_resident = v.findViewById(R.id.btn_resident);
        btn_resident.setOnClickListener(v17 -> startActivity(new Intent(getActivity(), ResidentListActivity.class)));


        btn_add_family = v.findViewById(R.id.btn_add_family);
        btn_add_family.setOnClickListener(v16 -> {
            startActivity(new Intent(getActivity(), AddFamilyActivity.class));
        });

        btn_add_guest = v.findViewById(R.id.btn_add_guest);
        btn_add_guest.setOnClickListener(v15 -> { startActivity(new Intent(getActivity(), AddVisitorActivity.class));

        });

        btn_add_vehicle = v.findViewById(R.id.btn_add_vehicle);
        btn_add_vehicle.setOnClickListener(v14 -> {
            startActivity(new Intent(getActivity(), ParkingMapActivity.class));
        });

        btn_add_home = v.findViewById(R.id.btn_add_home);
        btn_add_home.setOnClickListener(v1 -> startActivity(new Intent(getActivity(), SearchCityActivity.class)));

        iv_setting = v.findViewById(R.id.iv_setting);
        iv_setting.setOnClickListener(v13 -> startActivity(new Intent(getActivity(), SettingsActivity.class)));

        btn_pay_gas_bill = v.findViewById(R.id.btn_pay_gas_bill);
        btn_pay_gas_bill.setOnClickListener(v12 -> startActivity(new Intent(getActivity(), PayGasActivity.class)));


//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return v;
    }
}