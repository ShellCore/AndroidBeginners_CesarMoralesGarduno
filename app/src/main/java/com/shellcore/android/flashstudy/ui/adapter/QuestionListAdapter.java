package com.shellcore.android.flashstudy.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shellcore.android.flashstudy.R;
import com.shellcore.android.flashstudy.model.Question;
import com.shellcore.android.flashstudy.ui.activity.QuestionDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cesar on 21/06/2017.
 */

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.ViewHolder> {

    private Context context;
    private List<Question> questions;

    public QuestionListAdapter(Context context, List<Question> questions) {
        this.context = context;
        this.questions = questions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindView(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txt_card)
        TextView txtCard;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Question question = questions.get(getAdapterPosition());
            Intent intent = new Intent(context, QuestionDetailActivity.class);
            intent.putExtra(QuestionDetailActivity.QUESTION_KEY, question);
            context.startActivity(intent);
        }

        private void bindView(Question question) {
            txtCard.setText(question.getQuestion());
        }
    }
}
