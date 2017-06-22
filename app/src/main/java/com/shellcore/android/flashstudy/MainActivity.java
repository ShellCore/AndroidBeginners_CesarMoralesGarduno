package com.shellcore.android.flashstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.shellcore.android.flashstudy.data.dao.QuestionDao;
import com.shellcore.android.flashstudy.model.Question;
import com.shellcore.android.flashstudy.service.ReminderService;
import com.shellcore.android.flashstudy.task.LoadQuestionTask;
import com.shellcore.android.flashstudy.ui.activity.AboutMeActivity;
import com.shellcore.android.flashstudy.ui.activity.PreferenceActivity;
import com.shellcore.android.flashstudy.ui.adapter.QuestionListAdapter;
import com.shellcore.android.flashstudy.ui.dialog.AddQuestionDialogFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements AddQuestionDialogFragment.ToggleAddQuestionListener, LoadQuestionTask.ToggleLoadQuestionListener {

    // Servicios
    private QuestionListAdapter adapter;

    // Variables
    private List<Question> questions;

    // Components
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rec_card_list)
    RecyclerView recCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        initializeData();
        setupRecyclerView();
    }

    private void initializeData() {
        /**
         * TODO Si no existe información en base de datos, leer desde el archivo. En caso contrario,
         * se deberá leer desde el archivo Json.
         */
        questions = new ArrayList<>();
        adapter = new QuestionListAdapter(this, questions);

        loadQuestionsForJson();
        setupReminder();
//        loadQuestionsForDB();
    }

    private void setupReminder() {
        ReminderService.setupReminder(this);
    }

    private void loadQuestionsForJson() {
        LoadQuestionTask task = new LoadQuestionTask(this, this);
        task.execute("questionlist.json");
    }

    private void loadQuestionsForDB() {
        questions.clear();
        questions.addAll(QuestionDao.getQuestionList(this));
        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView() {
        recCardList.setLayoutManager(new LinearLayoutManager(this));
        recCardList.setHasFixedSize(true);
        recCardList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                startActivity(new Intent(this, PreferenceActivity.class));
                break;
            case R.id.action_about_me:
                startActivity(new Intent(this, AboutMeActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void addQuestion() {
        AddQuestionDialogFragment dialog = new AddQuestionDialogFragment();
        dialog.setListener(this);
        dialog.show(getSupportFragmentManager(), "addQuestion");
    }

    @Override
    public void handleResult() {
        loadQuestionsForDB();
    }

    @Override
    public void handleLoadQuestionTaskResult(List<Question> questions) {
        QuestionDao.insertAllQuestions(this, questions);
        loadQuestionsForDB();
    }
}
