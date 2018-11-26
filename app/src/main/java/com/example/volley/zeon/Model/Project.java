package com.example.volley.zeon.Model;


/**
 * Author of this class is Nabil  in 2018/03/11
 **/

/**
 * {@link Project} represents a project of team  .
 * <p>
 * It contains  Title of Project for  Team in String var .
 * <p>
 * It contains  Short Info about this Project for  Team in String var .
 * <p>
 * It contains Image Photo Of this project in int var .
 */
public class Project {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = Project.class.getSimpleName();

    private int id;
    private String projectName;
    private String projectBrief;

    public Project() {
    }

    public Project(int id, String projectName, String projectBrief) {
        this.id = id;
        this.projectName = projectName;
        this.projectBrief = projectBrief;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectBrief() {
        return projectBrief;
    }

    public void setProjectBrief(String projectBrief) {
        this.projectBrief = projectBrief;
    }
}
