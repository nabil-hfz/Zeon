package com.example.volley.zeon.MenuActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.volley.zeon.Model.Division;
import com.example.volley.zeon.R;
import com.example.volley.zeon.RecyclerAdapter.AdpterDivions;

import java.util.ArrayList;
import java.util.List;

public class DivisionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);


        List <Division> divisionList = new ArrayList<Division>();
        for (int i=0;i<6;++i)
        divisionList.add(new Division("Mahmmod Trrooooooo","Web stack developer and Xeon's boss ",
                R.drawable.zeon_boss,
                "Mahmmod is very nice creative boss . he love his assistance in the Zeon team speacially nabel  "));

        AdpterDivions adapter = new AdpterDivions(this,divisionList );
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
