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

public class DiagnosisAdapter extends RecyclerView.Adapter<DiagnosisAdapter.ViewHolder> {
    private Context mContext;
    private List<QuestionModel> listQuestion;

    public DiagnosisAdapter(Context mContext, List<QuestionModel> listQuestion) {
        this.mContext = mContext;
        this.listQuestion = listQuestion;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.accident, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final QuestionModel question = listQuestion.get(position);
        holder.question.setText(question.getQuestion());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DiagnosisStep3.class);
                intent.putExtra("question", question.getQuestion());
                intent.putExtra("yes", question.getYes());
                intent.putExtra("no", question.getNo());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listQuestion.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        public ViewHolder(View itemView) {
            super(itemView);
            question = (TextView) itemView.findViewById(R.id.accident);
        }
    }
}
