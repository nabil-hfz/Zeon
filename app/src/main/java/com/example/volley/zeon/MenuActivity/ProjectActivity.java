package com.example.volley.zeon.MenuActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.volley.zeon.Model.Project;
import com.example.volley.zeon.R;
import com.example.volley.zeon.RecyclerAdapter.AdapterProject;
import com.example.volley.zeon.RecyclerItemClickListener.RecyclerItemClickListener;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);

        final List<Project> projectList = new ArrayList<Project>();

        for (int i = 0; i < 6; ++i)
            projectList.add(new Project("Zeon Application " + (i + 1),
                    " Project numbers is :  " + i, R.drawable.project_photo));

        AdapterProject adapter = new AdapterProject(this, projectList);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

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
}
