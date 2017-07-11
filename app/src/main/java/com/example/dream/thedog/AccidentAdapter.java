package com.example.dream.thedog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dream on 7/10/17.
 */

public class AccidentAdapter extends RecyclerView.Adapter<AccidentAdapter.ViewHolder> {
    private List<AccidentModel> accidentModels;
    private Context mContext;

    public AccidentAdapter(List<AccidentModel> accidentModels, Context mContext) {
        this.accidentModels = accidentModels;
        this.mContext = mContext;
    }

    @Override
    public AccidentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.accident, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        AccidentModel accidentModel = accidentModels.get(position);
        viewHolder.title.setText(accidentModel.getAccident());
    }

    @Override
    public int getItemCount() {
        return accidentModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.accident);
        }
    }
}