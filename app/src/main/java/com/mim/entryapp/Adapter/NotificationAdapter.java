package com.mim.entryapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mim.entryapp.R;
import com.mim.entryapp.models.NotificationModel;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Viewholder> {

    private Context context;
    private ArrayList<NotificationModel> notificationModelArrayList;


    public NotificationAdapter(Context context, ArrayList<NotificationModel> notificationModelArrayList) {
        this.context = context;
        this.notificationModelArrayList = notificationModelArrayList;
    }

    @NonNull
    @Override
    public NotificationAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_card, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        NotificationModel model = notificationModelArrayList.get(position);
        holder.tv_identity.setText(model.getName());
        holder.tv_message.setText(model.getMessage());
        holder.tv_date.setText(model.getDate());
        holder.tv_time.setText(model.getTime());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.notify_dialog_box,null);

                ImageView dialog_box_imageView;
                TextView dialog_box_date;
                TextView dialog_box_time;
                TextView dialog_box_message;
                TextView dialog_box_user_desc;

                dialog_box_imageView = dialogView.findViewById(R.id.dialog_box_imageView);
                dialog_box_date = dialogView.findViewById(R.id.dialog_box_date);
                dialog_box_time = dialogView.findViewById(R.id.dialog_box_time);
                dialog_box_message = dialogView.findViewById(R.id.dialog_box_message);
                dialog_box_user_desc = dialogView.findViewById(R.id.dialog_box_user_desc);

                dialog_box_date.setText(model.getDate());
                dialog_box_time.setText(model.getTime());
                dialog_box_message.setText(model.getMessage());
                dialog_box_user_desc.setText(model.getName());
                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();

//                Toast.makeText(context, "hey", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return notificationModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView tv_identity, tv_message, tv_time,tv_date;
        CardView cardView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_identity = itemView.findViewById(R.id.tv_identity);
            tv_message = itemView.findViewById(R.id.tv_message);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_date = itemView.findViewById(R.id.tv_date);
            cardView = itemView.findViewById(R.id.cv1);
        }
    }
}
