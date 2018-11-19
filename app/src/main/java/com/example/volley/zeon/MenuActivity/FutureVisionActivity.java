package com.example.volley.zeon.MenuActivity;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.volley.zeon.MainActivity;
import com.example.volley.zeon.Model.FutureVision;
import com.example.volley.zeon.R;
import com.example.volley.zeon.RecyclerAdapter.AdapterFutureVision;
import java.util.ArrayList;
import java.util.List;

/**
 * Author of this class is Louay .
 * <p>
 * Update this class Nabil in in 2018/03/11
 */
public class FutureVisionActivity extends AppCompatActivity {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = FutureVisionActivity.class.getSimpleName();
    /**
     * Progress Bar that for two sec
     */
    ProgressBar mSimpleProgressBar;


    /**
     * const Article  for displying in Future Vision UI .
     */
    public static final String THE_FIRST_ARTICLE = "The team plans to expand and reach high levels of competition and strong on wide ranges, including the Arab Mashreq and then the world, through organized effort and fine work.";


    /**
     * const Article  for displying in Future Vision UI .
     */
    public static final String THE_SECOND_ARTICLE = "To succeed in the labor market, making the effort to stand by the mountain of talented youth and able to develop itself with an unrivaled ambition is an essential goal of the team by helping them find the right opportunities and receive the appropriate expertise and competencies to promote a strong society through training events and courses.And development in various areas of work.";

    /**
     * const Article  for displying in Future Vision UI .
     */
    public static final String THE_THIRD_ARTICLE = "The scientific research team gives an important aspect of its future vision as it seeks to develop the tools and means of work and find all that facilitates the work of individuals working in the field of software to ensure the durability and effectiveness";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);

        //set Toolbar - add the up button to display .
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // First, hide loading indicator so error message will be visible
        mSimpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        mSimpleProgressBar.setVisibility(View.GONE);

        List<FutureVision> futureVisionList = new ArrayList<FutureVision>();

        futureVisionList.add(new FutureVision(THE_FIRST_ARTICLE, R.drawable.future_vision));

        futureVisionList.add(new FutureVision(THE_SECOND_ARTICLE, R.drawable.future_vision));

        futureVisionList.add(new FutureVision(THE_THIRD_ARTICLE, R.drawable.future_vision));

        AdapterFutureVision adapter = new AdapterFutureVision(this, futureVisionList);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Press back to exit", Toast.LENGTH_SHORT).show();
    }
    //TODO : Advanced ...
    // This method for : MainActivity needs to know which album we want to display.
    // On TrackActivity, we need to override onPrepareSupportNavigateUpTaskStack to edit the intent
    // that will start the parent activity when pressing Up: specially to use when get Notification
    // and get back to mainActivity instead of go back to phone .

    @Override
    public void onPrepareNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onPrepareNavigateUpTaskStack(builder);
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        NavUtils.navigateUpTo(this, intent);    }
}
