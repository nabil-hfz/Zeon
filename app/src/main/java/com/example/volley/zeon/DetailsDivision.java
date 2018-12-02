package com.example.volley.zeon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.volley.zeon.widget.ProgressWheel.ProgressWheel;

public class DetailsDivision extends AppCompatActivity {

    private ProgressWheel progressWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_division);

        progressWheel = findViewById(R.id.progress_wheel);
        progressWheel.setCircleRadius(40);
        progressWheel.setVisibility(View.VISIBLE);
    }
}
