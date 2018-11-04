package com.example.volley.zeon.MenuActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.volley.zeon.Model.Division;
import com.example.volley.zeon.R;
import com.example.volley.zeon.RecyclerAdapter.AdpterDivions;
import com.example.volley.zeon.RecyclerItemClickListener.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class DivisionActivity extends AppCompatActivity {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = DivisionActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);


        final List<Division> divisionList = new ArrayList<Division>();

        for (int i = 0; i < 6; ++i)
            divisionList.add(new Division("Mahmmod Trrooooooo", "Web stack developer and Xeon's boss ",
                    R.drawable.zeon_boss,
                    "Mahmmod is very nice creative boss . he love his assistance in the Zeon team speacially nabel  "));

        AdpterDivions adapter = new AdpterDivions(this, divisionList);
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
}
