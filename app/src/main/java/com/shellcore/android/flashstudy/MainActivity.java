package com.shellcore.android.flashstudy;

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
import com.shellcore.android.flashstudy.model.Question;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initializeData() {
        /**
         * TODO Si no existe información en base de datos, leer desde el archivo. En caso contrario,
         * se deberá leer desde el archivo Json.
         */
        questions = new ArrayList<>();

        // TODO Hardcoded
        questions.add(new Question("¿Cuanto es 2 + 2 en R4?", "Puede ser 2, pero depende del teseracto"));
    }

    private void setupRecyclerView() {
        adapter = new QuestionListAdapter(this, questions);

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
}
