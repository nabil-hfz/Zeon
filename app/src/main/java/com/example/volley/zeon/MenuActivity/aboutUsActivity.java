package com.example.volley.zeon.MenuActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.volley.zeon.R;

public class aboutUsActivity extends AppCompatActivity {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = aboutUsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }
}