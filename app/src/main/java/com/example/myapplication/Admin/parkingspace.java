package com.example.myapplication.Admin;

public class parkingspace   {
    private String id;
    private boolean status=true;

    public parkingspace() {
    }

    public String getId() {
        return id;
    }

    public String setId(String id) {
        this.id = id;
        return id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}