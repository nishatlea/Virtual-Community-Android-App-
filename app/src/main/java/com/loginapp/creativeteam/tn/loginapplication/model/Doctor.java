package com.loginapp.creativeteam.tn.loginapplication.model;

public class Doctor {
  private  String doctorname;
 public Doctor(String doctorname)
 {
     this.doctorname = doctorname;
 }

 public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public Doctor() {

    }

    public String getDoctorname() {
        return doctorname;
    }

}
