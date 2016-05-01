package com.example.hau.dulichviet.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;


public class DataPlace{


    @SerializedName("data")
    private ArrayList<Place> data;

    public ArrayList<Place> getData() {
        return data;
    }

    public void setData(ArrayList<Place> data) {
        this.data = data;
    }


    @Parcel
    public static class Place{
        @SerializedName("id")
        public String id;
        @SerializedName("share_link")
        public String share_link;
        @SerializedName("name")
        public String name;
        @SerializedName("address")
        public String address;
        @SerializedName("lat")
        public String latitude;
        @SerializedName("long")
        public String longitude;
        @SerializedName("short_description")
        public String short_description;
        @SerializedName("description")
        public String description;
        @SerializedName("howtogo")
        public String howtogo;
        @SerializedName("category_id")
        public String category_id;
        @SerializedName("image_id")
        public String image_id;
        @SerializedName("hexcode")
        public String hexcode;
        @SerializedName("rating")
        public String rating;
        @SerializedName("rate_real")
        public String rate_real;
        @SerializedName("review_number")
        public String review_number;
        @SerializedName("checkin_number")
        public String checkin_number;
        @SerializedName("recommend_number")
        public String recommend_number;
        @SerializedName("report_number")
        public String report_number;
        @SerializedName("approve")
        public double approve;


        public Place() {
        }

    }

}
