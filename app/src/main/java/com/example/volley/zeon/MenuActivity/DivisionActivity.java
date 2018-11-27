package com.example.volley.zeon.MenuActivity;

import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
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
    ProgressBar mSimpleProgressBar;
    /**
     * Adapter for the list of Division
     */
    private List<Division> mDivisionList;

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    /**
     * TAG for cancel my request when go to main activity .
     */
    public static final String TAG = "TagCancel";


    private RequestQueue mRequestQueue;

    private AdapterDivision mDivisionAdapter;

    private RecyclerView mRecyclerView;

    private InternetAvailabilityChecker mInternetAvailabilityChecker;

    private View.OnClickListener onItemClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);

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

        // First, hide loading indicator so error message will be visible
        mSimpleProgressBar = findViewById(R.id.simpleProgressBar);

        mEmptyStateTextView = findViewById(R.id.empty_view);

        mDivisionList = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));

        // mRecyclerView.addItemDecoration(new DividerItemDecoration(this,RecyclerView.VERTICAL));

        mDivisionAdapter = new AdapterDivision(DivisionActivity.this, mDivisionList);

        mRecyclerView.setAdapter(mDivisionAdapter);


    }

    //Implement InternetConnectivityListener interface
    // where ever you want to listen to internet connectivity changes (E.g. in activity, fragment or service).
    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {
        if (isConnected) {
            // Get a reference to the ConnectivityManager to check state of network connectivity
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            // Get details on the currently active default data network
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            // If there is a network connection, fetch data
            if (networkInfo != null && networkInfo.isConnected()) {

                mSimpleProgressBar.setVisibility(ProgressBar.VISIBLE);

                mEmptyStateTextView.setVisibility(View.INVISIBLE);

                mRequestQueue = Volley.newRequestQueue(getApplicationContext());

                getJsonDivision();

            } else {

                // Otherwise, display error
                // First, hide loading indicator so error message will be visible
                mSimpleProgressBar.setVisibility(View.GONE);

                // Update empty state with no connection error message
                mEmptyStateTextView.setText(R.string.no_internet_connection);
            }
        } else {

            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            mSimpleProgressBar.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    private void getJsonDivision() {

        mDivisionList.clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.DIVISION_URL,
                (JSONObject) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("ID");
                        String mNameDivision = jsonObject.getString("Name");
                        String mMajourity = jsonObject.getString("Department");

                        mDivisionList.add(new Division(id, mNameDivision, mMajourity, Constants.TEAM_INFO_IMAGE[i]));
                    }

                    mSimpleProgressBar.setVisibility(View.GONE);

                    // If there is a valid list of {@link  Divisions }s, then add them to the adapter's
                    // data set. This will trigger the RecyclerView to update.
                    if (mDivisionList != null && !mDivisionList.isEmpty()) {

                        // Update the adapter with new Inf

                        mDivisionAdapter.notifyDataSetChanged();

                    } else {
                        mRecyclerView.setVisibility(View.GONE);
                        // Set empty state text to display "No Division found."
                        mEmptyStateTextView.setText(R.string.no_Division_Members);
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
                mSimpleProgressBar.setVisibility(View.GONE);
                mEmptyStateTextView.setText(R.string.Error_Divisions_Data);
            }
        });
        // Set the tag on the request.
        jsonObjectRequest.setTag(TAG);
        mRequestQueue.add(jsonObjectRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(TAG);
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
