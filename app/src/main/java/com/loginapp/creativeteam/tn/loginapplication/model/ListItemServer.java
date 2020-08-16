package com.loginapp.creativeteam.tn.loginapplication.model;

public class ListItemServer {
    private String name;

    public String getName() {
        return name;
    }
    private ListItemServer(){}
    public String getOrganization_name() {
        return organization_name;
    }

    public String getField_of_interest() {
        return field_of_interest;
    }

    public String getUid() {
        return uid;
    }

    public String getObjectid() {
        return objectid;
    }

    public String getEmail() {
        return email;
    }

    public ListItemServer(String name, String organization_name, String field_of_interest, String uid, String objectid, String email) {
        this.name = name;
        this.organization_name = organization_name;
        this.field_of_interest = field_of_interest;
        this.uid = uid;
        this.objectid = objectid;
        this.email = email;
    }

    private String organization_name, field_of_interest, uid, objectid, email;

}