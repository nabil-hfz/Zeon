package com.example.volley.zeon.Model;

public class MainNews {
    private int id;
    private String projectName;
    private String projectBrief;

    public MainNews() {
    }

    public MainNews(int id, String projectName, String projectBrief) {
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
