package com.example.volley.zeon;


import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.volley.zeon.MenuActivity.DivisionActivity;
import com.example.volley.zeon.Util.Constants;
import com.example.volley.zeon.Util.UtilTools;
import com.example.volley.zeon.widget.ProgressWheelFolder.ProgressWheel;
import com.squareup.picasso.Picasso;
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;
import com.treebo.internetavailabilitychecker.InternetConnectivityListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;


public class DivisionDetails extends AppCompatActivity implements InternetConnectivityListener {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = DivisionDetails.class.getSimpleName();
    /**
     * Progress Bar that for two sec
     */
    private ProgressWheel mProgressWheel;
    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    private RequestQueue mRequestQueue;


    private Animation mAnimation;

    private CircleImageView mMemberPhoto;

    private TextView mMemberName;

    private TextView mMemberEmail;

    private TextView mMemberFaceAccount;

    private TextView mMemberPhoneNum;

    private TextView mMemberDepartment;

    private TextView mMemberSkills;

    private TextView mMemberBrief;

    private TextView mMemberProjects;

    private Integer mId;

    private String mStringPathImage;

    private String mMemberNameToCHeck = null;

    private ConstraintLayout mDetailsDivisionConstraintLayout;

    private ConstraintLayout mDetailCardsConstraintLayout;

    private InternetAvailabilityChecker mInternetAvailabilityChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_division);

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

        Log.v(LOG_TAG, "\n\n<<<<<<<<<111" + Constants.TEAM_NAME + ">>>>>>>>>>>>>\n\n");

        getJsonDetail();
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.from_right);
        mDetailCardsConstraintLayout.startAnimation(mAnimation);
    }

    private void GetUIMethod() {

        mDetailCardsConstraintLayout = findViewById(R.id.CardsBlockView);

        mProgressWheel = findViewById(R.id.progress_wheel_division_details);

        mEmptyStateTextView = findViewById(R.id.empty_textView_division_details);

        mDetailsDivisionConstraintLayout = findViewById(R.id.DetailsDivisionConstraintLayout);
        // mDetailsDivisionConstraintLayout.setVisibility(View.INVISIBLE);

        mMemberPhoto = findViewById(R.id.member_image);

        mMemberName = findViewById(R.id.member_name_detail);

        mMemberEmail = findViewById(R.id.member_email_detail);

        mMemberFaceAccount = findViewById(R.id.facebook_account_detail);

        mMemberPhoneNum = findViewById(R.id.phone_number_detail);

        mMemberDepartment = findViewById(R.id.departments_detail);

        mMemberSkills = findViewById(R.id.skills_detail);

        mMemberBrief = findViewById(R.id.brief_detail);

        mMemberProjects = findViewById(R.id.projects_detail);




        mId = getIntent().getExtras().getInt("ID");

        mStringPathImage = getIntent().getExtras().getString("IMAGE");
    }

     /* @Override
  public void onInternetConnectivityChanged(boolean isConnected) {
        if (isConnected) {
            Log.v(LOG_TAG, "\n\n<<<<<<<<<3333" + Constants.TEAM_NAME + ">>>>>>>>>>>>>\n\n");

            mProgressWheel.setVisibility(ProgressWheel.VISIBLE);
            mEmptyStateTextView.setVisibility(View.GONE);
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
            getJsonDetail();
            mAnimation = AnimationUtils.loadAnimation(this, R.anim.from_right);
            mNestedScrollView.startAnimation(mAnimation);

        } else if ((mMemberNameToCHeck == null || mMemberNameToCHeck.isEmpty()) && !isConnected) {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            mProgressWheel.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            mEmptyStateTextView.setVisibility(View.VISIBLE);
        }
    }
*/

    public void getJsonDetail() {
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mDetailsDivisionConstraintLayout.setVisibility(View.INVISIBLE);
        mMemberNameToCHeck = null;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.DIVISION_DETAIL_URL + mId,
                (JSONObject) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Picasso.get().load(mStringPathImage).fit().centerInside().into(mMemberPhoto);

                    JSONArray jsonArray = response.getJSONArray("result");

                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    if (jsonObject.has("Name")) {
                        mMemberName.setText(jsonObject.getString("Name"));
                        mMemberNameToCHeck = jsonObject.getString("Name");
                    }

                    if (jsonObject.has("Email"))
                        mMemberEmail.setText(jsonObject.getString("Email"));

                    if (jsonObject.has("Facebook_account"))
                        mMemberFaceAccount.setText(jsonObject.getString("Facebook_account"));

                    if (jsonObject.has("Phone_number"))
                        mMemberPhoneNum.setText(jsonObject.getString("Phone_number"));

                    if (jsonObject.has("Department"))
                        mMemberDepartment.setText(jsonObject.getString("Department"));

                    if (jsonObject.has("Skills"))
                        mMemberSkills.setText(jsonObject.getString("Skills"));

                    if (jsonObject.has("Brief"))
                        mMemberBrief.setText(jsonObject.getString("Brief"));

                    if (jsonObject.has("Projects"))
                        mMemberProjects.setText(jsonObject.getString("Projects"));
                    mProgressWheel.setVisibility(View.GONE);
                    mDetailsDivisionConstraintLayout.setVisibility(View.VISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.v(LOG_TAG, "\n\n<<<<<<<<<222" + e.getMessage() + ">>>>>>>>>>>>>\n\n");

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
                Log.v(LOG_TAG, "\n\n<<<<<<<<<666" + error.getMessage() + ">>>>>>>>>>>>>\n\n");
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
        intent.setClass(this, DivisionActivity.class);
        NavUtils.navigateUpTo(this, intent);
    }


    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {
        Log.v(LOG_TAG, "\n<<<<<<<<" + Constants.TEAM_NAME + ">>>>>>>>>>>>>\n");

    }
}
