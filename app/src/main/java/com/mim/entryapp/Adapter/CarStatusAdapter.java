package com.mim.entryapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mim.entryapp.R;
import com.mim.entryapp.models.CarStatusModel;

import java.util.ArrayList;

public class CarStatusAdapter extends RecyclerView.Adapter<CarStatusAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CarStatusModel> carStatusModelArrayList;

    // Constructor
    public CarStatusAdapter(Context context, ArrayList<CarStatusModel> carStatusModelArrayList) {
        this.context = context;
        this.carStatusModelArrayList = carStatusModelArrayList;
    }

    @NonNull
    @Override
    public CarStatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_status_new_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        CarStatusModel model = carStatusModelArrayList.get(position);
        holder.tv_name.setText(model.getFull_name());
//        holder.tv_mob_no.setText(model.getMobile_number());
        holder.tv_adult.setText(model.getAccompanied_by());
        holder.tv_location.setText(model.getComing_from());
        holder.tv_vehicle_reg_no.setText(model.getVehicle_number());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return carStatusModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_mob_no, tv_adult,tv_location, tv_vehicle_reg_no;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_mob_no = itemView.findViewById(R.id.tv_mob_no);
            tv_adult = itemView.findViewById(R.id.tv_adult);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_vehicle_reg_no = itemView.findViewById(R.id.tv_vehicle_reg_no);
        }
    }
    public void deleteItem(int position){
        this.carStatusModelArrayList.remove(position);
        notifyItemRemoved(position);
    }
}
