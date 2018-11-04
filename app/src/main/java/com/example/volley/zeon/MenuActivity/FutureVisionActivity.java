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

public class FutureVisionActivity extends AppCompatActivity {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = FutureVisionActivity.class.getSimpleName();


    /**
     * const Article  for displying in Future Vision UI .
     */
    public static final String THE_FIRST_ARTICL = "The team plans to expand and reach\n" +
            "high levels of competition and strong\n" +
            "on wide ranges, including the Arab\n" +
            "Mashreq and then the world, through\n" +
            "organized effort and fine work.";


    /**
     * const Article  for displying in Future Vision UI .
     */
    public static final String THE_SECOND_ARTICL = "To succeed in the labor market, making\n" +
            "the effort to stand by the mountain of\n" +
            "talented youth and able to develop\n" +
            "itself with an unrivaled ambition is an\n" +
            "essential goal of the team by helping\n" +
            "them find the right opportunities and\n" +
            "receive the appropriate expertise and\n" +
            "competencies to promote a strong\n" +
            "society through training events and\n" +
            "courses.And development in various\n" +
            "areas of work.";

    /**
     * const Article  for displying in Future Vision UI .
     */
    public static final String THE_THIRD_ARTICL = "The scientific research team gives an\n" +
            "important aspect of its future vision as\n" +
            "it seeks to develop the tools and means\n" +
            "of work and find all that facilitates the\n" +
            "work of individuals working in the field\n" +
            "of software to ensure the durability\n" +
            "and effectiveness";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_future_vision);

        List<FutureVision> futureVisionList = new ArrayList<FutureVision>();

        futureVisionList.add(new FutureVision(THE_FIRST_ARTICL, R.drawable.future_vision));

        futureVisionList.add(new FutureVision(THE_SECOND_ARTICL, R.drawable.future_vision));

        futureVisionList.add(new FutureVision(THE_THIRD_ARTICL, R.drawable.future_vision));

        AdapterFutureVision adapter = new AdapterFutureVision(this, futureVisionList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
