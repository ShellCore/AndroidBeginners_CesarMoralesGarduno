package com.shellcore.android.flashstudy.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.shellcore.android.flashstudy.R;
import com.shellcore.android.flashstudy.model.Question;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionDetailActivity extends AppCompatActivity {

    // Constantes
    public static final String QUESTION_KEY = "question";

    // Variables
    private Question question;

    // Components
    @BindView(R.id.txt_question)
    TextView txtQuestion;
    @BindView(R.id.txt_answer)
    TextView txtAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getQuestionInformation();
    }

    public void getQuestionInformation() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            question = (Question) extras.getSerializable(QUESTION_KEY);
            txtQuestion.setText(question.getQuestion());
            txtAnswer.setText(question.getAnswer());
        }
    }
}
