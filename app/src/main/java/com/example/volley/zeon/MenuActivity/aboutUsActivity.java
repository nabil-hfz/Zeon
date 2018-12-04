package com.example.volley.zeon.MenuActivity;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.example.volley.zeon.R;
import com.example.volley.zeon.Util.Constants;
import com.example.volley.zeon.Util.UtilTools;
import com.example.volley.zeon.widget.ProgressWheelFolder.ProgressWheel;
import com.squareup.picasso.Picasso;
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;
import com.treebo.internetavailabilitychecker.InternetConnectivityListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Author of this class is Louay .
 * <p>
 * Update this class Nabil in in 2018/03/11
 */

public class aboutUsActivity extends AppCompatActivity implements InternetConnectivityListener {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = aboutUsActivity.class.getSimpleName();
    /**
     * Progress Wheel that for two sec
     */
    private ProgressWheel mProgressWheel;
    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    private TextView EmailContactUsPressed;

    private TextView mInfoAboutZeonTeam;

    private TextView mFoundationZeonTeam;

    private ImageView mZeonTeamImage;

    private RequestQueue mRequestQueue;

    private CoordinatorLayout mCollapsingToolbar;

    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    private InternetAvailabilityChecker mInternetAvailabilityChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = findViewById(R.id.toolbar_aboutUs);
        setSupportActionBar(toolbar);

        mEmptyStateTextView = findViewById(R.id.empty_textView_aboutUs);
        mEmptyStateTextView.setVisibility(View.INVISIBLE);

        mProgressWheel = findViewById(R.id.progress_wheel__aboutUs);
        mProgressWheel.setVisibility(ProgressWheel.VISIBLE);

        mCollapsingToolbar = findViewById(R.id.main_content_About_us);
        mCollapsingToolbar.setVisibility(View.INVISIBLE);

        mInfoAboutZeonTeam = findViewById(R.id.Info_textView);

        mFoundationZeonTeam = findViewById(R.id.Condition_For_Foundation_textView);

        mZeonTeamImage = findViewById(R.id.Zeon_team_image);

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

        //This for Enabling https connections with SSL HTTp ...
        new UtilTools.handleSSLHandshake().nuke();

        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);

        mCollapsingToolbarLayout.setTitle(Constants.TEAM_NAME);

        openZeonImageInGalleryBrowser();

        textViewContactUsPressedEmail();
    }

    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {
        if (isConnected) {

            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
            getJsonInfoAboutUs();

        } else {

            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            mProgressWheel.setVisibility(View.GONE);
            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            mEmptyStateTextView.setVisibility(View.VISIBLE);
        }
    }

    private void openZeonImageInGalleryBrowser() {

        mZeonTeamImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(Constants.ALL_TEAM_PHOTO_URL), "image/*");
                startActivity(Intent.createChooser(intent, "Open [App] images"));
            }
        });

    }


    private void textViewContactUsPressedEmail() {

        EmailContactUsPressed = findViewById(R.id.contact_us_press);
        EmailContactUsPressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = UtilTools.makeIntentOfEmail("https://www.facebook.com/MahmoudTrro");
                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                }
            }
        });
    }

    //get data from Json URL to fill up our future vision recycler
    public void getJsonInfoAboutUs() {
        // Picasso.get().load(Constants.ALL_TEAM_PHOTO_URL).fit().centerCrop().into(mZeonTeamImage);
        //  Glide.with(this).load(Uri.parse(Constants.ALL_TEAM_PHOTO_URL)).apply(RequestOptions.centerCropTransform()).into(mZeonTeamImage);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.MAIN_NEWS_URL,
                (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("result");

                    //TODO : just this temporary till make a correct json data

                    String name = null;
                    String brief = null;
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    name = jsonObject.getString("Name");
                    brief = jsonObject.getString("Brief");

                    mProgressWheel.setVisibility(View.GONE);
                    mCollapsingToolbar.setVisibility(View.VISIBLE);
                    Picasso.get().load(Uri.parse(Constants.ALL_TEAM_PHOTO_URL)).fit().centerCrop().into(mZeonTeamImage);
                    mInfoAboutZeonTeam.setText(name);
                    mFoundationZeonTeam.setText(brief);

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
                mEmptyStateTextView.setText(R.string.Error_Fetch_Data);
                mEmptyStateTextView.setVisibility(View.VISIBLE);
                Log.v(LOG_TAG, "\n<<<<<<<<<" + error.getMessage() + ">>>>>>>>>>>>>\n");
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