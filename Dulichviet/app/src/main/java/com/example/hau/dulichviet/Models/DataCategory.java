package com.example.hau.dulichviet.models;

import com.google.gson.annotations.SerializedName;

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
    public static class Category{

        @SerializedName("id")
        private String id;
        @SerializedName("name")
        private String name;
        @SerializedName("image_id")
        private String image_id;


        public Category() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage_id() {
            return image_id;
        }

        public void setImage_id(String image_id) {
            this.image_id = image_id;
        }
    }
}
