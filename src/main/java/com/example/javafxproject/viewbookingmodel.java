// Booking
package com.example.javafxproject;

public class viewbookingmodel {
    String hid;
    String hname;
    String cindate;
    String coutdate;
    int nrooms;
    String huser;

    public viewbookingmodel() {}

    public viewbookingmodel(String hid, String hname, String cindate, String coutdate, int nrooms, String huser) {
        this.hid=hid;
        this.hname = hname;
        this.cindate = cindate;
        this.coutdate = coutdate;
        this.nrooms = nrooms;
        this.huser = huser;
    }

    public String getHid() {
        return hid;
    }

    public String getHname() {
        return hname;
    }

    public String getCindate() {
        return cindate;
    }

    public String getCoutdate() {
        return coutdate;
    }

    public Integer getNrooms() {
        return nrooms;
    }

    public String getHuser() {
        return huser;
    }
}
