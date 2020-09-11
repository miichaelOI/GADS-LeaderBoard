package com.example.gadsleaderboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.gads_toolbar);

        Button submitButton = findViewById(R.id.submit_button1);

        submitButton.setOnClickListener(this);

        init();
    }

    private void init(){
        LearningFragment learningFragment = new LearningFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content_frame, learningFragment, getString(R.string.tag_fragment_learning));
        transaction.addToBackStack(getString(R.string.tag_fragment_learning));
        transaction.commit();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submit_button1){
            Log.d(TAG, "onClick: navigating to submission activity");

            Intent intent = new Intent(this, SubmissionActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        }

    }
}
