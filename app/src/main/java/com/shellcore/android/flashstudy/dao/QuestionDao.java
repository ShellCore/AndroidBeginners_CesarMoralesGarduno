package com.shellcore.android.flashstudy.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shellcore.android.flashstudy.data.FlashStudyDBHelper;
import com.shellcore.android.flashstudy.model.Question;

import java.util.ArrayList;
import java.util.List;

import static com.shellcore.android.flashstudy.data.QuestionContract.*;

/**
 * Created by Cesar on 21/06/2017.
 */

public class QuestionDao {


    private static SQLiteOpenHelper dbHelper;

    public static int insertQuestion(Context context, Question question) {
        dbHelper = new FlashStudyDBHelper(context);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QuestionEntry.COLUMN_QUESTION, question.getQuestion());
        values.put(QuestionEntry.COLUMN_ANSWER, question.getAnswer());
        long id = db.insert(QuestionEntry.TABLE_NAME, null, values);
        return (int) id;
    }

    public static List<Question> getQuestionList(Context context) {
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

    public static int delete(Context context, int id) {
        String[] ids = new String[] {Integer.toString(id)};
        dbHelper = new FlashStudyDBHelper(context);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        int res = db.delete(QuestionEntry.TABLE_NAME, "_ID?", ids);
        return res;
    }
}
