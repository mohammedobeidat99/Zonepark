package com.example.myapplication.User;

import android.net.Uri;

public class City {
    String amaan;
    Uri ImageUrl;

    City(String name, Uri result){
        this.amaan = name;
        this.ImageUrl = result;

    }

    public Uri getImageUrl() {
        return ImageUrl;
    }

    private void setImageUrl(Uri imageUrl) {
        ImageUrl = imageUrl;
    }





    public City(String amaan) {
        this.amaan = amaan;
    }

    public String getAmaan() {
        return amaan;
    }

    private void setAmaan(String amaan) {
        this.amaan = amaan;
    }



}
