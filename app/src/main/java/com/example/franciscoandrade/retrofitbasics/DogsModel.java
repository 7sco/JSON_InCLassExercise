package com.example.franciscoandrade.retrofitbasics;

/**
 * Created by franciscoandrade on 12/17/17.
 */

public class DogsModel {
    private String imageUrl;


    public DogsModel(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
