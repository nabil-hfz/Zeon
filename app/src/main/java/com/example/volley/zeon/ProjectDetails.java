package com.example.volley.zeon;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.volley.zeon.MenuActivity.ProjectActivity;
import com.example.volley.zeon.Util.Constants;
import com.example.volley.zeon.Util.UtilTools;
import com.example.volley.zeon.widget.ProgressWheelFolder.ProgressWheel;
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;
import com.treebo.internetavailabilitychecker.InternetConnectivityListener;

public class ProjectDetails extends AppCompatActivity implements InternetConnectivityListener {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = ProjectDetails.class.getSimpleName();
    /**
     * Progress Bar that for two sec
     */
    private ProgressWheel mProgressWheel;
    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    private InternetAvailabilityChecker mInternetAvailabilityChecker;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_details);

        mProgressWheel = findViewById(R.id.progress_wheel_project_Details);

        mEmptyStateTextView = findViewById(R.id.empty_text_view_project_Details);

        // Initialise it in application’s onCreate() function.
        // This is necessary step before starting using the library because
        // it needs context to register connectivity broadcast receiver.
        // It stores only weakReference to the context, so no need to worry about memory leaks.
        // Also it does lazy registration of receiver; i.e. it registers receiver whenever
        // first listener attaches to listen to internet changes and unregister itself when last listener stops listening.
        InternetAvailabilityChecker.init(this);

        mInternetAvailabilityChecker = InternetAvailabilityChecker.getInstance();

        mInternetAvailabilityChecker.addInternetConnectivityListener(this);

        //This for Enabling https connections with SSL HTTp ...
        new UtilTools.handleSSLHandshake().nuke();
        GONE();
    }

    void GONE() {
        Log.v(LOG_TAG, "\n bbbbbbbbbbbbbbbbbbbbb \n");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();
                mEmptyStateTextView.setText("The Project is : " + intent.getStringExtra(Intent.EXTRA_TEXT));
                mEmptyStateTextView.setVisibility(View.VISIBLE);
                mProgressWheel.setVisibility(ProgressWheel.GONE);
            }
        }, 3000);
    }

    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(Constants.TAG);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mInternetAvailabilityChecker.removeInternetConnectivityChangeListener(this);
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
        intent.setClass(this, ProjectActivity.class);
        NavUtils.navigateUpTo(this, intent);
    }
}