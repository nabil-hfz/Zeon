package com.example.volley.zeon.RecyclerAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.volley.zeon.Model.Project;
import com.example.volley.zeon.R;
import java.util.List;

/**
 * Author of this class is Nabil  in 2018/01/11
 */

public class AdapterProject extends RecyclerView.Adapter<AdapterProject.Holder> {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = AdapterProject.class.getSimpleName();

    Context context;

    List<Project> projectList;

    /**
     * Create a new {@link AdapterProject} object.
     *
     * @param context     is the current context (i.e. Activity) that the adapter is being created in.
     * @param projectList is the list of {@link Project  }s to be displayed.
     */
    public AdapterProject(Context context, List<Project> projectList) {
        this.context = context;
        this.projectList = projectList;
    }

    @Override
    public AdapterProject.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_project, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(AdapterProject.Holder holder, int position) {

        Project currentProject = projectList.get(position);

        // Get the  short info about project from the currentProject object and set this text on
        // the ShortInfo TextView.

        holder.mTitleProject.setText(currentProject.getProjectName());

        // Get project image from the currentProject object and set this image on
       // holder.mImageProject.setImageResource(currentProject.getImageProject());

        holder.mShortInfo.setText(currentProject.getProjectBrief());
        // Make sure the view is visible
       // holder.mImageProject.setVisibility(View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

     class Holder extends RecyclerView.ViewHolder {

        TextView mTitleProject;

         TextView mShortInfo;

        //private ImageView mImageProject;

         Button mSeeMoreProject;

        Holder(View view) {

            super(view);

            mTitleProject = view.findViewById(R.id.name_project);

            //mImageProject = view.findViewById(R.id.project_photo);

            mShortInfo = view.findViewById(R.id.summary_project);

            mSeeMoreProject = view.findViewById(R.id.button_see_more);
        }
    }
}
