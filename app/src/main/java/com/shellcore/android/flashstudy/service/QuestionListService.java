package com.shellcore.android.flashstudy.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shellcore.android.flashstudy.data.QuestionContract.QuestionEntry;
import com.shellcore.android.flashstudy.model.Question;

/**
 * Created by Cesar on 21/06/2017.
 */

public class QuestionListService extends IntentService {

    public static final String QUESTION = "question";
    private int OK = 10;

    public QuestionListService() {
        super("QuestionListService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Bundle bundle = intent.getExtras();
        Question question = (Question) bundle.getSerializable(QUESTION);

        ContentValues contentValues = new ContentValues();
        contentValues.put(QuestionEntry.COLUMN_QUESTION, question.getQuestion());
        contentValues.put(QuestionEntry.COLUMN_ANSWER, question.getAnswer());

        getContentResolver().insert(QuestionEntry.CONTENT_URI, contentValues);
    }
}
