package com.example.volley.zeon.MenuActivity;

import android.annotation.SuppressLint;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.volley.zeon.MainActivity;
import com.example.volley.zeon.Model.Division;
import com.example.volley.zeon.R;
import com.example.volley.zeon.RecyclerAdapter.AdapterDivision;
import com.example.volley.zeon.Listeners.RecyclerItemClickListener;

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



    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);

        //set Toolbar - add the up button to display .
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final List<Division> divisionList = new ArrayList<Division>();

        for (int i = 0; i < 6; ++i)
            divisionList.add(new Division("Mahmmod Trrooooooo", "Web stack developer and Xeon's boss ",
                    R.drawable.zeon_boss,
                    "Mahmmod is very nice creative boss . he love his assistance in the Zeon team speacially nabel  "));

        AdapterDivision adapter = new AdapterDivision(this, divisionList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // make a listener for all elements recycle View .

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Division division = divisionList.get(position);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getApplicationContext(), "NABIL", Toast.LENGTH_LONG).show();
                    }
                })
        );
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
