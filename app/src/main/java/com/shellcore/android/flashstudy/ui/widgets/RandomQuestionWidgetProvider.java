package com.shellcore.android.flashstudy.ui.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.shellcore.android.flashstudy.R;
import com.shellcore.android.flashstudy.data.dao.QuestionDao;
import com.shellcore.android.flashstudy.model.Question;

import java.util.List;
import java.util.Random;

/**
 * Created by Cesar on 20/06/2017.
 */

public class RandomQuestionWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            Question question = getRandomName(context);

            views.setTextViewText(R.id.txt_question, question.getQuestion());
            views.setTextViewText(R.id.txt_answer, question.getAnswer());

            Intent intent = new Intent(context, RandomQuestionWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.btn_random, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    private Question getRandomName(Context context) {
        List<Question> questions = QuestionDao.getQuestionList(context);

        Random random = new Random();
        int position = random.nextInt(questions.size());
        return questions.get(position);
    }
}
