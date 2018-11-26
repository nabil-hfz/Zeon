package com.example.volley.zeon;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    /**
     * TEAM EMAIL for send the  messages to it .
     */

    private NavigationView mNavigationView;

    private DrawerLayout mDrawer;

    private ImageView mImageZeonHeaderMain;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RequestQueue queue;
    private List<MainNews>mainNewsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        mDrawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mDrawer.addDrawerListener(toggle);

        toggle.syncState();

        mNavigationView = findViewById(R.id.nav_view);

        mNavigationView.setNavigationItemSelectedListener(this);

        imageZeonListener();


        queue= Volley.newRequestQueue(this);

        mainNewsList=new ArrayList<>();
        recyclerView=findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getJsonNews();
    }

    private void getJsonNews() {
        mainNewsList.clear();
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

                        MainNews mainNews=new MainNews(id,name,brief);

                        mainNewsList.add(mainNews);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter=new AdapterMainNews(getApplicationContext(),mainNewsList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
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
