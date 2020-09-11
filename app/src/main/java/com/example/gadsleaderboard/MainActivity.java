package com.example.gadsleaderboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.example.gadsleaderboard.adapters.MyPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private static final int LEARNING_FRAGMENT = 0;
    private static final int SKILL_FRAGMENT = 1;

    private ViewPager mViewPager;

    private LearningFragment mlearningFragment;
    private SkillFragment mSkillFragment;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);



        mViewPager = findViewById(R.id.viewpager_container);

        Button submitButton = findViewById(R.id.submit_button1);

        submitButton.setOnClickListener(this);

       // init();

        setupViewPager();
    }

    private void setupViewPager(){
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        mlearningFragment = new LearningFragment();
        mSkillFragment = new SkillFragment();
        adapter.addFragment(mlearningFragment);
        adapter.addFragment(mSkillFragment);

        mViewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs_top);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(LEARNING_FRAGMENT).setText("Learning Leaders");
        tabLayout.getTabAt(SKILL_FRAGMENT).setText("Skill IQ Leaders");





    }

    private void init(){
        mlearningFragment = new LearningFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.replace(R.id.main_content_frame, mlearningFragment, getString(R.string.tag_fragment_learning));
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
