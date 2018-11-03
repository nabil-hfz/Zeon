package com.example.volley.zeon.MenuActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.volley.zeon.Model.Project;
import com.example.volley.zeon.R;
import com.example.volley.zeon.RecyclerAdapter.AdapterProject;

import java.util.ArrayList;
import java.util.List;

public class ProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);

        List<Project> projectList = new ArrayList<Project>();

        for (int i = 0; i < 6; ++i)
            projectList.add(new Project("Zeon Application " + (i + 1),
                    " Project numbers is :  " + i, R.drawable.project_photo));

        AdapterProject adapter = new AdapterProject(this, projectList);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
    }
}
