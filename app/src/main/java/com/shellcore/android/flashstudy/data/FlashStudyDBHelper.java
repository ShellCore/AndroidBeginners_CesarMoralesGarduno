package com.shellcore.android.flashstudy.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shellcore.android.flashstudy.data.QuestionContract.*;

/**
 * Created by Cesar on 21/06/2017.
 */

public class FlashStudyDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "flashstudy.db";

    public FlashStudyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createQuestionTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private String createQuestionTable() {
        String query = "CREATE TABLE " + QuestionEntry.TABLE_NAME +
                " ( " +
                QuestionEntry._ID + " INTEGER PRIMARY KEY, " +
                QuestionEntry.COLUMN_QUESTION + " TEXT NOT NULL, " +
                QuestionEntry.COLUMN_ANSWER + " TEXT NOT NULL, " +
                " UNIQUE ( " +
                QuestionEntry.COLUMN_QUESTION +
                " ) ON CONFLICT IGNORE " +
                " ); ";

        return query;
    }
}
