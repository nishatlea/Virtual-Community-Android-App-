package com.loginapp.creativeteam.tn.loginapplication.model;

public class DoctorUser {


    private String id;
    private String doctorusername;
    private String imageURL;
    private String status;
    private String search;

    public DoctorUser(String id, String doctorusername, String imageURL, String status, String search) {
        this.id = id;
        this.doctorusername = doctorusername;
        this.imageURL = imageURL;
        this.status = status;
        this.search = search;
    }

    public DoctorUser() {

    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorUsername() {
        return doctorusername;
    }

    public void setDoctorUsername(String username) {
        this.doctorusername = doctorusername;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
