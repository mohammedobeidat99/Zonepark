package com.example.myapplication.User;

import android.net.Uri;

public class City {
    String amaan;
    Uri ImageUrl;

    City(String name, Uri result){

    }

    public Uri getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(Uri imageUrl) {
        ImageUrl = imageUrl;
    }





    public City(String amaan) {
        this.amaan = amaan;
    }

    public String getAmaan() {
        return amaan;
    }

    public void setAmaan(String amaan) {
        this.amaan = amaan;
    }



}
