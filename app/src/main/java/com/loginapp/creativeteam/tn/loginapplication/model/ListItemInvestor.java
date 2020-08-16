package com.loginapp.creativeteam.tn.loginapplication.model;

public class ListItemInvestor {
    private String investor_name,money,interest,uid,objectid,email;

    public String getInvestor_name() {
        return investor_name;
    }

    public String getMoney() {
        return money;
    }

    public String getInterest() {
        return interest;
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

    public ListItemInvestor(String investor_name, String money, String interest, String uid, String objectid, String email) {
        this.investor_name = investor_name;
        this.money = money;
        this.interest = interest;
        this.uid = uid;
        this.objectid = objectid;
        this.email = email;
    }

    private ListItemInvestor() {
    }
}