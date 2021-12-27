package com.mim.entryapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mim.entryapp.Activities.CallingActivity;
import com.mim.entryapp.R;
import com.mim.entryapp.models.ResidentListModel;
import com.mim.entryapp.models.ResidentResponse;
import com.sinch.android.rtc.SinchClient;

import java.util.ArrayList;
import java.util.List;

public class ResidentStatusAdapter extends RecyclerView.Adapter<ResidentStatusAdapter.Viewholder> {

    Context context;
    private List<ResidentResponse.DataBean.ResidentListBean> residentListModelArrayList;

    interface CallingCallBack{
        void call(String number);
    }

    String phone = "";

    // Constructor
    public ResidentStatusAdapter(Context context, List<ResidentResponse.DataBean.ResidentListBean> residentListModelArrayList ) {
        this.context = context;
        this.residentListModelArrayList = residentListModelArrayList;

    }

    @NonNull
    @Override
    public ResidentStatusAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resident_list_card, parent, false);
        return new Viewholder(view);
    }

//    public void filterList(List<ResidentResponse.DataBean.ResidentListBean> filterList) {
//        residentListModelArrayList = filterList;
//        notifyDataSetChanged();
//    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        ResidentResponse.DataBean.ResidentListBean model = residentListModelArrayList.get(position);

        holder.tv_identity.setText(model.getName());
        holder.tv_tower_name.setText(model.getTowerName());
        holder.tv_flat_no.setText(model.getFlatNo());
        holder.call.setText(model.getMobileNo());
        holder.call.setOnClickListener(v -> {
/*
            callback.call(model.getMobileNo());
*/
            Intent intent = new Intent(context, CallingActivity.class);
            intent.putExtra("number",model.getMobileNo());
            context.startActivity(intent);
         /*   Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + model.getMobileNo()));
        context.startActivity(intent);*/
        });
    }


    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return residentListModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView tv_identity, tv_tower_name, tv_flat_no, call , sinchClient;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_identity = itemView.findViewById(R.id.tv_identity);
            tv_tower_name = itemView.findViewById(R.id.tv_tower_name);
            tv_flat_no = itemView.findViewById(R.id.tv_flat_no);
            call = itemView.findViewById(R.id.call);

        }
    }
}

