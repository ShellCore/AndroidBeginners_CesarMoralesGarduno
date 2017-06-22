package com.shellcore.android.flashstudy.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.shellcore.android.flashstudy.R;
import com.shellcore.android.flashstudy.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.shellcore.android.flashstudy.json.JsonLoader.loadJsonFromAsset;

/**
 * Created by Cesar on 21/06/2017.
 */

public class LoadQuestionTask extends AsyncTask<String, Void, List<Question>> {

    private Context context;
    private ProgressDialog dialog;
    private ToggleLoadQuestionListener listener;

    public LoadQuestionTask(Context context, ToggleLoadQuestionListener listener) {
        this.context = context;
        dialog = new ProgressDialog(context);
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage(context.getString(R.string.processDialog_wait));
    }

    @Override
    protected List<Question> doInBackground(String... params) {
        String filePath = params[0];

        String questionJson = loadJsonFromAsset(context, filePath);

        Gson gson = new Gson();
        Question[] questions = gson.fromJson(questionJson, Question[].class);

        return new ArrayList<>(Arrays.asList(questions));
    }

    @Override
    protected void onPostExecute(List<Question> questions) {
        super.onPostExecute(questions);
        listener.handleLoadQuestionTaskResult(questions);
        dialog.dismiss();
    }

    public interface ToggleLoadQuestionListener {
        void handleLoadQuestionTaskResult(List<Question> questions);
    }
}
