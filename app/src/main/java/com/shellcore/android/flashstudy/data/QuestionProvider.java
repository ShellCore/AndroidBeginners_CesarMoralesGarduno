package com.shellcore.android.flashstudy.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.shellcore.android.flashstudy.data.QuestionContract.*;

/**
 * Created by Cesar on 21/06/2017.
 */

public class QuestionProvider extends ContentProvider {

    public static final int QUESTION = 100;
    public static final int QUESTION_ID = 101;

    private static final UriMatcher uriMatcher = buildUriMatcher();
    private FlashStudyDBHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new FlashStudyDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor retCursor;
        switch (uriMatcher.match(uri)) {
            case QUESTION:
                retCursor = dbHelper.getReadableDatabase()
                        .query(
                                QuestionEntry.TABLE_NAME,
                                projection,
                                selection,
                                selectionArgs,
                                null,
                                null,
                                sortOrder
                        );
                break;
            case QUESTION_ID:
                retCursor = dbHelper.getReadableDatabase()
                        .query(
                                QuestionEntry.TABLE_NAME,
                                projection,
                                QuestionEntry._ID + " = '" + ContentUris.parseId(uri) + "'",
                                null,
                                null,
                                null,
                                sortOrder
                        );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case QUESTION:
                long _id = db.insert(QuestionEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = QuestionEntry.CONTENT_URI;
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver()
                .notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        int rowSelected;
        switch (match) {
            case QUESTION:
                rowSelected = db.delete(
                        QuestionEntry.TABLE_NAME,
                        selection,
                        selectionArgs
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Because a null delete all the rows
        if (selection == null || rowSelected != 0) {
            getContext().getContentResolver()
                    .notifyChange(uri, null);
        }
        return rowSelected;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        int rowsUpdated;
        switch (match) {
            case QUESTION:
                rowsUpdated = db.update(
                        QuestionEntry.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Because a null delete all the rows
        if (rowsUpdated != 0) {
            getContext().getContentResolver()
                    .notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        switch (match) {
            case QUESTION:
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(QuestionEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver()
                        .notifyChange(uri, null);
                return returnCount;
            default:
                return super.bulkInsert(uri, values);
        }
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CONTENT_AUTHORITY;

        matcher.addURI(authority, PATH_QUESTION, QUESTION);
        matcher.addURI(authority, PATH_QUESTION + "/#", QUESTION_ID);

        return matcher;
    }
}