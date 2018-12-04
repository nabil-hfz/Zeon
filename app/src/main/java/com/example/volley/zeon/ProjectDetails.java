package com.example.volley.zeon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.volley.zeon.widget.ProgressWheelFolder.ProgressWheel;

public class ProjectDetails extends AppCompatActivity {

    private ProgressWheel mProgressWheel;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_details);

        mTextView = findViewById(R.id.empty_text_view_project_Details);
        mTextView.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();

        mTextView.setText("The Project is : " + intent.getStringExtra(Intent.EXTRA_TEXT));

        mProgressWheel = findViewById(R.id.progress_wheel_project_Details);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mTextView.setVisibility(View.VISIBLE);
                mProgressWheel.setVisibility(View.INVISIBLE);

            }
        }, 3000);

    }
}
