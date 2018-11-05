package com.example.volley.zeon.MenuActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
}
