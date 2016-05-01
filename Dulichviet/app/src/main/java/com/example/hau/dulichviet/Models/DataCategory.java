package com.example.hau.dulichviet.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;


public class DataCategory {
    @SerializedName("data")
    private ArrayList<Category> data;

    public ArrayList<Category> getData() {
        return data;
    }

    public void setData(ArrayList<Category> data) {
        this.data = data;
    }
    @Parcel
    public static class Category{

        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("image_id")
        public String image_id;

        public Category() {
        }
    }
}
