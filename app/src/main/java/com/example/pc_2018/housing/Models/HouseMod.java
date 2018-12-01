package com.example.pc_2018.housing.Models;

import android.graphics.Bitmap;

public class HouseMod {

        // Store the id of the  movie poster
        private int id;

        private String ownername;


        private String place;

        private String hpuseNumber;


        private String isPayed;

        private Bitmap image;


        private  String price;

    public HouseMod(int id, String ownername, String place, String hpuseNumber, Bitmap image,String price,String isPayed ) {
        this.id = id;
        this.price = price;
        this.ownername = ownername;
        this.place = place;
        this.hpuseNumber = hpuseNumber;
        this.image = image;
        this.isPayed = isPayed;
    }



    public int getId() {
        return id;
    }

    public String getIsPayed() {
        return isPayed;
    }

    public String getOwnername() {
        return ownername;
    }

    public String getPrice() {
        return price;
    }

    public String getPlace() {
        return place;
    }

    public String getHpuseNumber() {
        return hpuseNumber;
    }

    public Bitmap getImage() {
        return image;
    }
}
