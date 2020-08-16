package com.loginapp.creativeteam.tn.loginapplication.model;

public class ListItem {
    private String name;
    private String project_name, project_idea, uid, objectid,email;
    private ListItem(){}

    public String getName() {
        return name;
    }

    public String getProject_name() {
        return project_name;
    }

    public String getProject_idea() {
        return project_idea;
    }

    public String getUid() {
        return uid;
    }
    public String getEmail() {
        return email;
    }

    public String getObjectid() {
        return objectid;
    }

    public ListItem(String name, String project_name, String project_idea, String uid, String objectid,String email) {
        this.name = name;
        this.project_name = project_name;
        this.project_idea = project_idea;
        this.uid = uid;
        this.objectid = objectid;
        this.email = email;
    }

}