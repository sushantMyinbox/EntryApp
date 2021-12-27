package com.mim.entryapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mim.entryapp.R;
import com.mim.entryapp.models.SocietyListModel;

import java.util.ArrayList;

public class SocietyListAdapter extends RecyclerView.Adapter<SocietyListAdapter.Viewholder> {


    private Context context;
    private ArrayList<SocietyListModel> societyListModelArrayList;

    public SocietyListAdapter(Context context, ArrayList<SocietyListModel> societyListModelArrayList) {
        this.context = context;
        this.societyListModelArrayList = societyListModelArrayList;


    }

    @NonNull
    @Override
    public SocietyListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.society_list_card, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        SocietyListModel model = societyListModelArrayList.get(position);
        holder.tv_owner_name.setText(model.getOwner_name());
        holder.tv_mob_num.setText(model.getMobile_number());
        holder.tv_email.setText(model.getEmail_id());
        holder.tv_aadhar_num.setText(model.getAadhar_num());
        holder.tv_voter_id.setText(model.getVoter_id());
        holder.tv_flat_address.setText(model.getFlat_address());

    }


    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return societyListModelArrayList.size();
    }

    // View holder class for initializing of
// your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView tv_owner_name, tv_mob_num, tv_email, tv_aadhar_num, tv_voter_id, tv_flat_address;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_owner_name = itemView.findViewById(R.id.tv_owner_name);
            tv_mob_num = itemView.findViewById(R.id.tv_mob_num);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_aadhar_num = itemView.findViewById(R.id.tv_aadhar_num);
            tv_voter_id = itemView.findViewById(R.id.tv_voter_id);
            tv_flat_address = itemView.findViewById(R.id.tv_flat_address);
        }
    }
}
