package com.example.volley.zeon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.volley.zeon.widget.ProgressWheelFolder.ProgressWheel;

public class DivisionDetails extends AppCompatActivity {

    private ProgressWheel mProgressWheel;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_division);

        mTextView = findViewById(R.id.empty_text_view_Division_Details);
        mTextView.setVisibility(View.INVISIBLE);

        mProgressWheel = findViewById(R.id.progress_wheel_Division_Details);

        Intent intent = getIntent();

        mTextView.setText("The Division is : " + intent.getStringExtra(Intent.EXTRA_TEXT));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mTextView.setVisibility(View.VISIBLE);
                mProgressWheel.setVisibility(View.INVISIBLE);

            }
        }, 3000);
    }
}
