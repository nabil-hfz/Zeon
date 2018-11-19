package com.example.volley.zeon.MenuActivity;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.volley.zeon.Util.UtilTools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Author of this class is Louay .
 * <p>
 * Update this class Nabil in in 2018/03/11
 */

public class DivisionActivity extends AppCompatActivity {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = DivisionActivity.class.getSimpleName();

    public final static String TEAM_INFO_DIVISION = "https://mtmt2141.000webhostapp.com/getDepartments.php";

    public final static String[] TEAM_INFO_IMAGE = {
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwx4VqD0s57NhTo1PwPjz4tA0jr6_W6bS3pP7yYkNrxXR0z7BCSA",
            "http://ift.tt/1ri4IVk",
            "https://static.twentytwowords.com/wp-content/uploads/Beautiful-Women-FB-5.jpg",
            "https://static.twentytwowords.com/wp-content/uploads/You-Can-Achieve-These-20-Gorgeous-Makeup-Looks.jpg",
            "https://www.carthageplus.net/wp-content/uploads/2018/08/100-most-beautiful-women-ever.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcShH8_wf6-YxxA19JEppeOEJkVgm2NCXcn2YWEMJK89vPIS9haekA" ,
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS54WkD_5_qVJhqr5kcWRoNJYnR-YaYBZp4mRhzEfLlbhsMqmGc",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSVm4ValOpFVfEbqwyNOJJfKKqAbQSJnvZe2DdbcNthTM6nyqn",
            "http://www.scalsys.com/wallpapers/beautiful-wallpaper/beautiful-wallpaper_32407.jpg",
    };

    private RequestQueue mRequestQueue;
    private List<Division> mDivisionList;
    private AdapterDivision mDivisionAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);

        //set Toolbar - add the up button to display .
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**
         * This for Enabling https connections
         */
        new UtilTools.handleSSLHandshake().nuke();

        mRecyclerView = findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDivisionList = new ArrayList<Division>();

        mDivisionAdapter = new AdapterDivision(DivisionActivity.this, mDivisionList);

        mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        mRecyclerView.setAdapter(mDivisionAdapter);

        parseJSON();
    }

    private void parseJSON() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, TEAM_INFO_DIVISION,
                (JSONObject) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String mNameDivision = jsonObject.getString("Name");
                        String mMajourity = jsonObject.getString("Text");
                        String mSummarry = jsonObject.getString("ID");

                        mDivisionList.add(new Division(mNameDivision, mMajourity, TEAM_INFO_IMAGE[i],
                                mNameDivision + " is very nice creative boss " +
                                        ". he love his assistance in the Zeon team specially Nabel "));
                    }

                    mRecyclerView.setAdapter(mDivisionAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(jsonObjectRequest);
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
