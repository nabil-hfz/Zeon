package com.example.volley.zeon.MenuActivity;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.volley.zeon.MainActivity;
import com.example.volley.zeon.Model.FutureVision;
import com.example.volley.zeon.R;
import com.example.volley.zeon.RecyclerAdapter.AdapterFutureVision;
import com.example.volley.zeon.Util.Constants;
import com.example.volley.zeon.Util.UtilTools;
import com.example.volley.zeon.widget.ProgressWheelFolder.ProgressWheel;
import com.squareup.picasso.Picasso;
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;
import com.treebo.internetavailabilitychecker.InternetConnectivityListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.volley.zeon.Util.UtilTools.makeIntentToOpenPhotoInGalleryBrowser;

/**
 * Author of this class is Louay .
 * <p>
 * Update this class Nabil in in 2018/03/11
 */
public class FutureVisionActivity extends AppCompatActivity implements InternetConnectivityListener {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = FutureVisionActivity.class.getSimpleName();

    private RecyclerView recyclerView;

    private AdapterFutureVision adapter;

    private List<FutureVision> futureVisionList = new ArrayList<FutureVision>();

    private RequestQueue mRequestQueue;

    private InternetAvailabilityChecker mInternetAvailabilityChecker;

    private ConstraintLayout mConstraintLayout;

    private ProgressWheel mProgressWheel;
    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision);

        futureVisionList.clear();
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

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        makeIntentToOpenPhotoInGalleryBrowser(
                                Constants.TEAM_INFO_IMAGE[0]));
            }
        });
    }

    void GetUIMethod() {

        // First, hide loading indicator so error message will be visible
        mProgressWheel = findViewById(R.id.progress_wheel_vision);

        mEmptyStateTextView = findViewById(R.id.empty_textView_vision);

        mImageView = findViewById(R.id.future_vision_image);

        mConstraintLayout = findViewById(R.id.Vision_ConstraintLayout);

        recyclerView = findViewById(R.id.vision_recycler);
    }

    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {

        if (isConnected) {

            mProgressWheel.setVisibility(ProgressWheel.VISIBLE);
            mEmptyStateTextView.setVisibility(View.GONE);

            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
            getJsonVision();

        } else if ((futureVisionList == null || futureVisionList.isEmpty()) && !isConnected) {

            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            mProgressWheel.setVisibility(ProgressWheel.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            mEmptyStateTextView.setVisibility(View.VISIBLE);
        }
    }

    //get data from Json URL to fill up our future vision recycler
    public void getJsonVision() {

        futureVisionList.clear();
        mConstraintLayout.setVisibility(View.INVISIBLE);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.FUTURE_VISION_URL, (JSONObject) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        if (i == 1) continue;
                        JSONObject object = jsonArray.getJSONObject(i);
                        FutureVision futureVision = new FutureVision();
                        if (object.has("Name"))
                            futureVision.setmTitle(object.getString("Name"));
                        if (object.has("Text"))
                            futureVision.setShortArticleFutureVision(object.getString("Text"));

                        futureVisionList.add(futureVision);
                    }
                    adapter = new AdapterFutureVision(getApplicationContext(), futureVisionList);

                    recyclerView.setAdapter(adapter);

                    mProgressWheel.setVisibility(View.GONE);

                    mConstraintLayout.setVisibility(View.VISIBLE);

                    // If there is a valid list of {@link  Divisions }s, then add them to the adapter's
                    // data set. This will trigger the RecyclerView to update.

                    Picasso.get().load(Uri.parse(Constants.TEAM_INFO_IMAGE[0])).fit().centerCrop().into(mImageView);

                    if (futureVisionList != null && !futureVisionList.isEmpty()) {

                        // Update the adapter with new Inf
                        adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.v(LOG_TAG, "\n<<<<<<<<<" + e.getMessage() + ">>>>>>>>>>>>>\n");

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
                // Otherwise, display error
                // First, hide loading indicator so error message will be visible
                mProgressWheel.setVisibility(View.GONE);
                mEmptyStateTextView.setVisibility(View.VISIBLE);
                mEmptyStateTextView.setText(R.string.Error_Fetch_Data);
                //     Log.v(LOG_TAG, "\n<<<<<<<<<" + error.getMessage() + ">>>>>>>>>>>>>\n");

            }
        });
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