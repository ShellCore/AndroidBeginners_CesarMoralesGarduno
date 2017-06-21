package com.shellcore.android.flashstudy.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Cesar on 21/06/2017.
 */

public class QuestionContract {

    public static final String CONTENT_AUTHORITY = "com.shellcore.android.flashstudy";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_QUESTION = "question";

    public static final class QuestionEntry implements BaseColumns {

        public static final String TABLE_NAME = "question";

        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_ANSWER = "answer";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_QUESTION).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_QUESTION;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_QUESTION;
    }
}
