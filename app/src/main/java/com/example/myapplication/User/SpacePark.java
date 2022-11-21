package com.example.myapplication.User;

public class SpacePark {




    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public SpacePark(String status) {
        this.status = status;
    }

    public String id;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SpacePark(String id, String status,String cname, String mname) {
        this.id = id;
        this.status = status;
        this.cname=cname;
        this.mname=mname;


    }

    public String status;

    public String mname;
    public String cname;

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}