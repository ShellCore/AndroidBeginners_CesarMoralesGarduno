package com.shellcore.android.flashstudy.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shellcore.android.flashstudy.R;
import com.shellcore.android.flashstudy.ui.fragments.OptionsFragment;

public class PreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_preference);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new OptionsFragment())
                .commit();
    }
}
