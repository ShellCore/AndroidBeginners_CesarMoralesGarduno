package com.shellcore.android.flashstudy;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.shellcore.android.flashstudy.adapter.QuestionListAdapter;
import com.shellcore.android.flashstudy.data.QuestionContract;
import com.shellcore.android.flashstudy.data.QuestionContract.QuestionEntry;
import com.shellcore.android.flashstudy.dialog.AddQuestionDialogFragment;
import com.shellcore.android.flashstudy.model.Question;
import com.shellcore.android.flashstudy.service.QuestionListService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements AddQuestionDialogFragment.ToggleAddQuestionListener {

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

        loadQuestionsForDB();
    }

    private void loadQuestionsForDB() {
        questions.clear();
        questions.addAll(new QuestionListService().getQuestionList(this));
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
        if (id == R.id.action_settings) {
            return true;
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
}
