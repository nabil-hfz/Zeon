package com.example.volley.zeon.Model;

public class Project {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = Project.class.getSimpleName();

    /**
     * Title (name) of project resource for the Project
     */
    private String mTitleProject;

    /**
     * small Info resource for the Project
     */
    private String mShortInfoProject;

    /**
     * Image  resource ID for the Project
     */
    private int mImageProject;

    public Project(String mTitleProject, String mShortInfoProject, int mImageProject) {
        this.mTitleProject = mTitleProject;
        this.mShortInfoProject = mShortInfoProject;
        this.mImageProject = mImageProject;
    }

    public String getmTitleProject() {
        return mTitleProject;
    }

    public void setmTitleProject(String mTitleProject) {
        this.mTitleProject = mTitleProject;
    }

    public String getShortInfo() {
        return mShortInfoProject;
    }

    public void setShortInfo(String mShortInfo) {
        this.mShortInfoProject = mShortInfo;
    }

    public int getImageProject() {
        return mImageProject;
    }

    public void setImageProject(int mImageProject) {
        this.mImageProject = mImageProject;
    }
}
