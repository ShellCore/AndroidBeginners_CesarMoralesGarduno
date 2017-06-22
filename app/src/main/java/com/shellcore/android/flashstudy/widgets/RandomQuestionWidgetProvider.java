package com.shellcore.android.flashstudy.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.shellcore.android.flashstudy.R;
import com.shellcore.android.flashstudy.dao.QuestionDao;
import com.shellcore.android.flashstudy.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Cesar on 20/06/2017.
 */

public class RandomQuestionWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Get the layout for the App Widget and attach the onClick listener
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            Question question = getRandomName(context);

            views.setTextViewText(R.id.txt_question, question.getQuestion());
            views.setTextViewText(R.id.txt_answer, question.getAnswer());

            // Create an Intent to update Widget
            Intent intent = new Intent(context, RandomQuestionWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.btn_random, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    /**
     * We are faking data, but this method should be a call to a ContentProvider or Database to get real data
     * @return
     */
    private Question getRandomName(Context context) {
        List<Question> questions = QuestionDao.getQuestionList(context);

        Random random = new Random();
        int position = random.nextInt(questions.size());
        return questions.get(position);
    }
}
