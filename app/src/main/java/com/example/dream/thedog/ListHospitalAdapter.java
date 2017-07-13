package com.example.dream.thedog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dream on 7/13/17.
 */

public class ListHospitalAdapter extends RecyclerView.Adapter<ListHospitalAdapter.ViewHolder> {
    private Context mContext;
    private List<ListHospitalModel> listHospitalModels;

    public ListHospitalAdapter(Context mContext, List<ListHospitalModel> listHospitalModels) {
        this.mContext = mContext;
        this.listHospitalModels = listHospitalModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_hospital, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.hospital.setText(listHospitalModels.get(position).getName());
        holder.hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HospitalDetail.class);
                intent.putExtra("name", listHospitalModels.get(position).getName());
                intent.putExtra("address", listHospitalModels.get(position).getAddress());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHospitalModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView hospital;

        public ViewHolder(View itemView) {
            super(itemView);
            hospital = (TextView) itemView.findViewById(R.id.hospital);
        }
    }
}
