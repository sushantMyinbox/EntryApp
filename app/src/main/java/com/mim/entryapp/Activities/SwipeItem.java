package com.mim.entryapp.Activities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.mim.entryapp.Adapter.CarStatusAdapter;

public class SwipeItem extends ItemTouchHelper.SimpleCallback {

    CarStatusAdapter carStatusAdapter;

    SwipeItem(CarStatusAdapter carStatusAdapter1){
        super(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
        this.carStatusAdapter = carStatusAdapter1;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        this.carStatusAdapter.deleteItem(position);
    }
}
