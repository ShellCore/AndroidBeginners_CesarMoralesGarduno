package com.shellcore.android.flashstudy.service;

import android.app.Activity;
import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shellcore.android.flashstudy.data.QuestionContract.QuestionEntry;
import com.shellcore.android.flashstudy.model.Question;

import java.util.ArrayList;
import java.util.List;

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

    public List<Question> getQuestionList(Context context) {
        List<Question> questions = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(QuestionEntry.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(QuestionEntry._ID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionEntry.COLUMN_QUESTION)));
                question.setAnswer(cursor.getString(cursor.getColumnIndex(QuestionEntry.COLUMN_ANSWER)));
                questions.add(question);
            }
        }
        cursor.close();

        return questions;
    }
}
