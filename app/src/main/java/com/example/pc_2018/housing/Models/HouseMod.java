package com.example.pc_2018.housing.Models;

public class HouseMod {

        // Store the id of the  movie poster
        private int mImageDrawable;
        // Store the name of the movie
        private String mName;
        // Store the release date of the movie
        private String mRelease;

        // Constructor that is used to create an instance of the Movie object
        public HouseMod(int mImageDrawable, String mName, String mRelease) {
            this.mImageDrawable = mImageDrawable;
            this.mName = mName;
            this.mRelease = mRelease;
        }

        public int getmImageDrawable() {
            return mImageDrawable;
        }

        public void setmImageDrawable(int mImageDrawable) {
            this.mImageDrawable = mImageDrawable;
        }

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }

        public String getmRelease() {
            return mRelease;
        }

        public void setmRelease(String mRelease) {
            this.mRelease = mRelease;
        }

}
