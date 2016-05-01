package com.example.hau.dulichviet.models;

import com.google.gson.annotations.SerializedName;

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



    public static class Place implements Serializable{
        @SerializedName("id")
        private String id;
        @SerializedName("share_link")
        private String share_link;
        @SerializedName("name")
        private String name;
        @SerializedName("address")
        private String address;
        @SerializedName("lat")
        private String latitude;
        @SerializedName("long")
        private String longitude;
        @SerializedName("short_description")
        private String short_description;
        @SerializedName("description")
        private String description;
        @SerializedName("howtogo")
        private String howtogo;
        @SerializedName("category_id")
        private String category_id;
        @SerializedName("image_id")
        private String image_id;
        @SerializedName("hexcode")
        private String hexcode;
        @SerializedName("rating")
        private String rating;
        @SerializedName("rate_real")
        private String rate_real;
        @SerializedName("review_number")
        private String review_number;
        @SerializedName("checkin_number")
        private String checkin_number;
        @SerializedName("recommend_number")
        private String recommend_number;
        @SerializedName("report_number")
        private String report_number;
        @SerializedName("approve")
        private double approve;


        public Place() {
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShare_link() {
            return share_link;
        }

        public void setShare_link(String share_link) {
            this.share_link = share_link;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getShort_description() {
            return short_description;
        }

        public void setShort_description(String short_description) {
            this.short_description = short_description;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getHowtogo() {
            return howtogo;
        }

        public void setHowtogo(String howtogo) {
            this.howtogo = howtogo;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getImage_id() {
            return image_id;
        }

        public void setImage_id(String image_id) {
            this.image_id = image_id;
        }

        public String getHexcode() {
            return hexcode;
        }

        public void setHexcode(String hexcode) {
            this.hexcode = hexcode;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getRate_real() {
            return rate_real;
        }

        public void setRate_real(String rate_real) {
            this.rate_real = rate_real;
        }

        public String getReview_number() {
            return review_number;
        }

        public void setReview_number(String review_number) {
            this.review_number = review_number;
        }

        public String getCheckin_number() {
            return checkin_number;
        }

        public void setCheckin_number(String checkin_number) {
            this.checkin_number = checkin_number;
        }

        public String getRecommend_number() {
            return recommend_number;
        }

        public void setRecommend_number(String recommend_number) {
            this.recommend_number = recommend_number;
        }

        public String getReport_number() {
            return report_number;
        }

        public void setReport_number(String report_number) {
            this.report_number = report_number;
        }

        public double getApprove() {
            return approve;
        }

        public void setApprove(double approve) {
            this.approve = approve;
        }
    }

}
