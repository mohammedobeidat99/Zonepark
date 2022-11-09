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

    public SpacePark(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String status;
}
