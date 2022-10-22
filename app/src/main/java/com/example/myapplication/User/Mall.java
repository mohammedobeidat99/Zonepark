package com.example.myapplication.User;

import android.net.Uri;

public class Mall {
    String Namemall;
    Uri ImageUrlmall;

    public String getNamemall() {
        return Namemall;
    }

    public void setNamemall(String namemall) {
        Namemall = namemall;
    }

    public Uri getImageUrlmall() {
        return ImageUrlmall;
    }

    public void setImageUrlmall(Uri imageUrlmall) {
        ImageUrlmall = imageUrlmall;
    }


    public Mall(String namemall, Uri imageUrlmall) {
        Namemall = namemall;
        ImageUrlmall = imageUrlmall;
    }


}
