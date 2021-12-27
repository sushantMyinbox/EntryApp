package com.mim.entryapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.mim.entryapp.Fragment.ReceivedFragment;
import com.mim.entryapp.Fragment.ApprovedFragment;
import com.mim.entryapp.Fragment.MoviesFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    private static final int CARD_ITEM_SIZE = 10;


    // tab titles
    private String[] titles = new String[]{"Movies", "Events", "Tickets"};
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ReceivedFragment();
            case 1:
                return new ApprovedFragment();
            case 2:
                return new MoviesFragment();
        }
        return ReceivedFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

}
