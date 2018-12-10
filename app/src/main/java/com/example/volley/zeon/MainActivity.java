package com.example.volley.zeon;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
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
import com.example.volley.zeon.MenuActivity.ContactActivity;
import com.example.volley.zeon.MenuActivity.DivisionActivity;
import com.example.volley.zeon.MenuActivity.FutureVisionActivity;
import com.example.volley.zeon.MenuActivity.ProjectActivity;
import com.example.volley.zeon.MenuActivity.aboutUsActivity;
import com.example.volley.zeon.Model.MainNews;
import com.example.volley.zeon.RecyclerAdapter.AdapterMainNews;
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


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, InternetConnectivityListener {
    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private InternetAvailabilityChecker mInternetAvailabilityChecker;

    private NavigationView mNavigationView;

    private DrawerLayout mDrawer;

    private ImageView mImageZeonHeaderMain;

    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;

    private RequestQueue mRequestQueue;

    private List<MainNews> mainNewsList = new ArrayList<MainNews>();
    /**
     * Progress Bar that for two sec
     */
    private ProgressWheel mProgressWheel;
    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;
    private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        mDrawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mDrawer.addDrawerListener(toggle);

        toggle.syncState();

        mNavigationView = findViewById(R.id.nav_view);

        mNavigationView.setNavigationItemSelectedListener(this);

        imageZeonListener();

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

        mainNewsList.clear();

        recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AdapterMainNews(getApplicationContext(), mainNewsList);
        recyclerView.setAdapter(adapter);
        // First, hide loading indicator so error message will be visible
        mProgressWheel = findViewById(R.id.progress_wheel_main);

        mEmptyStateTextView = findViewById(R.id.empty_textView_main);

        mWaveSwipeRefreshLayout = findViewById(R.id.mainNewsSwipe);
        mWaveSwipeRefreshLayout.setWaveColor(Color.argb(255, 220, 160, 60));

        getCalculateMaxDropHeightAndListenerForWaveSwipeRefreshLayout();
    }


    void getCalculateMaxDropHeightAndListenerForWaveSwipeRefreshLayout() {
        // Checks the orientation of the screen
        int newConfig = getApplicationContext().getResources().getConfiguration().orientation;
        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        int height = metrics.heightPixels;

        if (newConfig == Configuration.ORIENTATION_PORTRAIT)
            mWaveSwipeRefreshLayout.setMaxDropHeight(((height - 56) / 2));

        else if (newConfig == Configuration.ORIENTATION_LANDSCAPE)
            mWaveSwipeRefreshLayout.setMaxDropHeight(((height - 56) / 2));

        mWaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {

        if (isConnected) {
            mWaveSwipeRefreshLayout.setVisibility(View.VISIBLE);

            mProgressWheel.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setVisibility(View.GONE);

            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
            getJsonNews();

        } else if ((mainNewsList == null || mainNewsList.isEmpty()) && !isConnected) {
            mWaveSwipeRefreshLayout.setVisibility(View.GONE);

            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            mProgressWheel.setVisibility(ProgressWheel.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            mEmptyStateTextView.setVisibility(View.VISIBLE);
        }
    }

    private void getJsonNews() {
        mainNewsList.clear();
        recyclerView.setVisibility(View.GONE);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.MAIN_NEWS_URL,
                (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("result");

                    int id = -1;
                    String name = null;
                    String brief = null;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        if (jsonObject.has("ID"))
                            id = jsonObject.getInt("ID");

                        if (jsonObject.has("Name"))
                            name = jsonObject.getString("Name");

                        if (jsonObject.has("Brief"))
                            brief = jsonObject.getString("Brief");

                        MainNews mainNews = new MainNews(id, name, brief);

                        mainNewsList.add(mainNews);
                        mProgressWheel.setVisibility(ProgressWheel.GONE);
                        // If there is a valid list of {@link  Divisions }s, then add them to the adapter's
                        // data set. This will trigger the RecyclerView to update.
                        if (mainNewsList != null && !mainNewsList.isEmpty()) {

                            mWaveSwipeRefreshLayout.setRefreshing(false);

                            adapter.notifyDataSetChanged();

                            recyclerView.setVisibility(View.VISIBLE);
                        }
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
                Log.v(LOG_TAG, "\n\n<<<<<<<<<" + error.getMessage() + ">>>>>>>>>>>>>\n\n");
            }
        });
        // Set the tag on the request.
        jsonObjectRequest.setTag(Constants.TAG);
        mRequestQueue.add(jsonObjectRequest);
    }

    private void refresh() {
        getJsonNews();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mWaveSwipeRefreshLayout.setRefreshing(false);
            }
        }, 4000);
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
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        // Instead of all setChecked :: mNavigationView.setCheckedItem(item.getItemId());

        switch (item.getItemId()) {
            case (R.id.nav_main):
                item.setChecked(true);
                mDrawer.closeDrawer(GravityCompat.START);
                break;
            case (R.id.nav_division):
                Intent divisionIntent = new Intent(MainActivity.this, DivisionActivity.class);
                startActivity(divisionIntent);
                break;
            case (R.id.nav_project):

                Intent projectsIntent = new Intent(MainActivity.this, ProjectActivity.class);
                startActivity(projectsIntent);
                break;
            case (R.id.nav_vision):

                Intent visionIntent = new Intent(MainActivity.this, FutureVisionActivity.class);
                startActivity(visionIntent);

                break;

            case (R.id.nav_contact_us):

                Intent contactUsIntent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(contactUsIntent);
                break;

            case (R.id.nav_about_us):
                Intent aboutUsIntent = new Intent(MainActivity.this, aboutUsActivity.class);
                startActivity(aboutUsIntent);
                break;

            default:

                mDrawer.closeDrawer(GravityCompat.START);
                break;
        }
        mDrawer.closeDrawer(GravityCompat.START);

        return false;
    }

    private void imageZeonListener() {

        View header = mNavigationView.getHeaderView(0);

        mImageZeonHeaderMain = header.findViewById(R.id.zeon_header_main);

        mImageZeonHeaderMain.setOnClickListener(new ZeonImageClickListener());

        mDrawer.closeDrawer(GravityCompat.START);
    }


    public class ZeonImageClickListener implements View.OnClickListener {

        ZeonImageClickListener() {
        }

        @Override
        public void onClick(View view) {
            Intent intent = UtilTools.makeIntentOfEmail(Constants.T_EMAIL);
            if (intent.resolveActivity(getPackageManager()) != null) {
                Toast.makeText(MainActivity.this, R.string.Greeting_client, Toast.LENGTH_SHORT).show();
                startActivity(intent);
                mDrawer.closeDrawer(GravityCompat.START);
            }
        }
    }
}
