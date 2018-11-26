package com.example.volley.zeon.MenuActivity;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.volley.zeon.Listeners.RecyclerItemClickListener;
import com.example.volley.zeon.MainActivity;
import com.example.volley.zeon.Model.MainNews;
import com.example.volley.zeon.Model.Project;
import com.example.volley.zeon.R;
import com.example.volley.zeon.RecyclerAdapter.AdapterMainNews;
import com.example.volley.zeon.RecyclerAdapter.AdapterProject;
import com.example.volley.zeon.Util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Author of this class is Louay .
 * <p>
 * Update this class Nabil in in 2018/05/11
 */

public class ProjectActivity extends AppCompatActivity {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = ProjectActivity.class.getSimpleName();
    /**
     * Progress Bar that for two sec
     */
    //ProgressBar mSimpleProgressBar;

    private List<Project> projectList;
    private AdapterProject adapter;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        // First, hide loading indicator so error message will be visible
       // mSimpleProgressBar = findViewById(R.id.simpleProgressBar);
       // mSimpleProgressBar.setVisibility(View.GONE);

        projectList = new ArrayList<>();

        requestQueue= Volley.newRequestQueue(this);


        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getJsonProject();

        // make a listener for all elements recycle View .

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Project project = projectList.get(position);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getApplicationContext(), "NABIL", Toast.LENGTH_LONG).show();
                    }
                })
        );
    }


    private void getJsonProject() {
        projectList.clear();
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Constants.MAIN_NEWS_URL,
                (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray=response.getJSONArray("result");

                    int id=-1;
                    String name=null;
                    String brief=null;
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        if(jsonObject.has("ID"))
                            id=jsonObject.getInt("ID");

                        if(jsonObject.has("Name"))
                            name=jsonObject.getString("Name");

                        if(jsonObject.has("Brief"))
                            brief=jsonObject.getString("Brief");

                        Project project=new Project(id,name,brief);

                        projectList.add(project);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter=new AdapterProject(getApplicationContext(),projectList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
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
        NavUtils.navigateUpTo(this, intent);    }
}
