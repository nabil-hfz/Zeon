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
import com.example.volley.zeon.Model.FutureVision;
import com.example.volley.zeon.R;
import com.example.volley.zeon.RecyclerAdapter.AdapterFutureVision;
import com.example.volley.zeon.Util.Constants;
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
public class FutureVisionActivity extends AppCompatActivity {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = FutureVisionActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private AdapterFutureVision adapter;
    private List<FutureVision> futureVisionList = new ArrayList<>();
    private RequestQueue requestQueue;
    private boolean check=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision);

        //This for Enabling https connections with SSL HTTp ...
        new UtilTools.handleSSLHandshake().nuke();

        recyclerView = findViewById(R.id.vision_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = Volley.newRequestQueue(this);

        if (futureVisionList.size() == 0)
            Toast.makeText(this, "loading...", Toast.LENGTH_SHORT).show();

        //while (!check)
        getJsonVision();


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


    //get data from Json URL to fill up our future vision recycler
    public void getJsonVision() {
        futureVisionList.clear();
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

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                check=true;
                adapter = new AdapterFutureVision(getApplicationContext(), futureVisionList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(getApplicationContext(), "please check the connection", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonObjectRequest);

    }

}
