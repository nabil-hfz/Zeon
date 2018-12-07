package com.example.volley.zeon.MenuActivity;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.volley.zeon.MainActivity;
import com.example.volley.zeon.Model.Division;
import com.example.volley.zeon.R;
import com.example.volley.zeon.RecyclerAdapter.AdapterDivision;
import com.example.volley.zeon.Util.Constants;
import com.example.volley.zeon.Util.UtilTools;
import com.example.volley.zeon.widget.ProgressWheelFolder.ProgressWheel;
import com.example.volley.zeon.widget.WaveSwipeRefreshFolder.WaveSwipeRefreshLayout;
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;
import com.treebo.internetavailabilitychecker.InternetConnectivityListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Author of this class is Louay .
 * <p>
 * Update this class Nabil in in 2018/03/11
 * and use ready library compile 'com.treebo:internetavailabilitychecker:1.0.1'
 * for Implement InternetConnectivityListener interface where ever you want to listen to
 * internet connectivity changes (E.g. in activity, fragment or service).
 */

public class DivisionActivity extends AppCompatActivity implements InternetConnectivityListener {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = DivisionActivity.class.getSimpleName();
    /**
     * Progress Bar that for two sec
     */
    private ProgressWheel mProgressWheel;
    /**
     * Adapter for the list of Division
     */
    private List<Division> mDivisionList = new ArrayList<>();
    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    private RequestQueue mRequestQueue;

    private AdapterDivision mDivisionAdapter;

    private RecyclerView mRecyclerView;

    private InternetAvailabilityChecker mInternetAvailabilityChecker;

    private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);

        mDivisionList.clear();
        GetUIMethod();
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

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));

        mDivisionAdapter = new AdapterDivision(getApplicationContext(), mDivisionList);

        mRecyclerView.setAdapter(mDivisionAdapter);

        mWaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }


    void GetUIMethod() {
        // First, hide loading indicator so error message will be visible
        mProgressWheel = findViewById(R.id.progress_wheel_division);

        mEmptyStateTextView = findViewById(R.id.empty_textView_division);

        mWaveSwipeRefreshLayout = findViewById(R.id.main_swipe);
        mWaveSwipeRefreshLayout.setWaveColor(Color.argb(255, 220, 160, 60));

        mRecyclerView = findViewById(R.id.recycler_view);
    }

    private void refresh() {
        getJsonDivision();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mWaveSwipeRefreshLayout.setRefreshing(false);
            }
        }, 4000);
    }

    //Implement InternetConnectivityListener interface
    // where ever you want to listen to internet connectivity changes (E.g. in activity, fragment or service).
    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {

        if (isConnected) {

            mProgressWheel.setVisibility(ProgressWheel.VISIBLE);
            mEmptyStateTextView.setVisibility(View.GONE);

            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
            getJsonDivision();

        } else if ((mDivisionList == null || mDivisionList.isEmpty()) && !isConnected) {

            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            mProgressWheel.setVisibility(ProgressWheel.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            mEmptyStateTextView.setVisibility(View.VISIBLE);
        }
    }

    private void getJsonDivision() {

        mDivisionList.clear();
        mRecyclerView.setVisibility(View.INVISIBLE);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.DIVISION_URL,
                (JSONObject) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        int id = 0;
                        String mMajourity = null;
                        String mNameDivision = null;

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        if (jsonObject.has("ID"))
                            id = jsonObject.getInt("ID");

                        if (jsonObject.has("Name"))
                            mNameDivision = jsonObject.getString("Name");

                        if (jsonObject.has("Department"))
                            mMajourity = jsonObject.getString("Department");

                        mDivisionList.add(new Division(id, mNameDivision, mMajourity, Constants.DIVISION_PHOTO + id + ".jpg"));
                    }

                    mProgressWheel.setVisibility(ProgressWheel.GONE);
                    // If there is a valid list of {@link  Divisions }s, then add them to the adapter's
                    // data set. This will trigger the RecyclerView to update.
                    if (mDivisionList != null && !mDivisionList.isEmpty()) {

                        mWaveSwipeRefreshLayout.setRefreshing(false);

                        // Update the adapter with new Inf
                        mDivisionAdapter.notifyDataSetChanged();

                        mRecyclerView.setVisibility(View.VISIBLE);
                    } else {
                        // Set empty state text to display "No Division found."
                        mEmptyStateTextView.setText(R.string.no_Division_Members);
                        mEmptyStateTextView.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                // Otherwise, display error
                // First, hide loading indicator so error message will be visible
                mProgressWheel.setVisibility(ProgressWheel.GONE);
                mEmptyStateTextView.setVisibility(View.VISIBLE);
                mEmptyStateTextView.setText(R.string.Error_Fetch_Data);
                Log.v(LOG_TAG, "\n\n<<<<<<<<<111" + error.getMessage() + ">>>>>>>>>>>>>\n\n");

            }
        });
        // Set the tag on the request.
        jsonObjectRequest.setTag(Constants.TAG);
        mRequestQueue.add(jsonObjectRequest);
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
        intent.setClass(this, MainActivity.class);
        NavUtils.navigateUpTo(this, intent);
    }
}
