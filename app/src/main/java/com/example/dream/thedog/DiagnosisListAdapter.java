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
 * Created by dream on 7/12/17.
 */

public class DiagnosisListAdapter extends RecyclerView.Adapter<DiagnosisListAdapter.ViewHolder> {
    private List<String> DiagnosisList;
    private Context mContext;

    public DiagnosisListAdapter(List<String> diagnosisList, Context mContext) {
        DiagnosisList = diagnosisList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.accident, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final String list = DiagnosisList.get(position);
        holder.title.setText(list);
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DiagnosisStep2.class);
                intent.putExtra("type", list);
            }
        });
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.accident);

        }
    }
}
