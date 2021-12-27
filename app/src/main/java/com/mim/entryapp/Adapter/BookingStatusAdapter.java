package com.mim.entryapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mim.entryapp.R;
import com.mim.entryapp.models.BookingStatusModel;
import com.mim.entryapp.models.BookingStatusResponse;

import java.util.ArrayList;
import java.util.List;

public class BookingStatusAdapter extends RecyclerView.Adapter<BookingStatusAdapter.Viewholder> {

    private Context context;
    private List<BookingStatusResponse.DataBean.BookingListBean> bookingStatusModelArrayList;

    public BookingStatusAdapter(Context context, List<BookingStatusResponse.DataBean.BookingListBean> bookingStatusModelArrayList) {
        this.context = context;
        this.bookingStatusModelArrayList = bookingStatusModelArrayList;
    }

    @NonNull
    @Override
    public BookingStatusAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_booking_card, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        BookingStatusResponse.DataBean.BookingListBean model = bookingStatusModelArrayList.get(position);
        holder.tv_booking_num.setText(model.getBookingNo());
        holder.tv_book_to.setText(model.getBookedTo());
        holder.tv_book_from.setText(model.getBookedFrom());
        holder.tv_booked_for.setText(model.getBookedFor());
        holder.tv_remarks.setText(model.getRemark());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return bookingStatusModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView tv_booking_num, tv_book_to, tv_book_from,tv_booked_for, tv_remarks;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_booking_num = itemView.findViewById(R.id.tv_booking_num);
            tv_book_to = itemView.findViewById(R.id.tv_book_to);
            tv_book_from = itemView.findViewById(R.id.tv_book_from);
            tv_booked_for = itemView.findViewById(R.id.tv_booked_for);
            tv_remarks = itemView.findViewById(R.id.tv_remarks);
        }
    }
}
