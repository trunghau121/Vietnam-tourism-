package com.example.hau.dulichviet.models.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by TRUNGHAU on 5/1/2016.
 */
@Table(name = "Place")
public class Place extends Model{
    @SerializedName("_id")
    public String id;
    @Column(name = "share_link")
    public String share_link;
    @Column(name = "name")
    public String name;
    @Column(name = "address")
    public String address;
    @Column(name = "lat")
    public String latitude;
    @Column(name = "long")
    public String longitude;
    @Column(name = "short_description")
    public String short_description;
    @Column(name = "description")
    public String description;
    @Column(name = "howtogo")
    public String howtogo;
    @Column(name = "category_id")
    public String category_id;
    @Column(name = "image_id")
    public String image_id;
    @Column(name = "hexcode")
    public String hexcode;
    @Column(name = "rating")
    public String rating;
    @Column(name = "rate_real")
    public String rate_real;
    @Column(name = "review_number")
    public String review_number;
    @Column(name = "checkin_number")
    public String checkin_number;
    @Column(name = "recommend_number")
    public String recommend_number;
    @Column(name = "report_number")
    public String report_number;
    @Column(name = "approve")
    public double approve;

    public static List<Place> all() {
        return new Select().from(Place.class).execute();
    }

    public static List<Place> getPlacesCategory(int id_category) {
        return new Select().from(Place.class).where("category_id = ?",id_category).execute();
    }

    public static Place getPlace(int id) {
        return new Select().from(Place.class).where("id = ?",id).executeSingle();
    }

    public static void deleteCategoryPlace() {
        new Delete().from(Place.class).execute();
    }

}
