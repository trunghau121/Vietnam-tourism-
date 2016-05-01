package com.example.hau.dulichviet.utils;

import com.activeandroid.ActiveAndroid;
import com.example.hau.dulichviet.models.DataCategory;
import com.example.hau.dulichviet.models.DataPlace;
import com.example.hau.dulichviet.models.database.Category;
import com.example.hau.dulichviet.models.database.Place;

import java.util.List;

/**
 * Created by TRUNGHAU on 5/1/2016.
 */
public class DataMapper {
    public static void parseCategory(List<DataCategory.Category> categories) {
        ActiveAndroid.beginTransaction();
        Category.deleteAllCategory();
        for (DataCategory.Category category : categories){
            Category data = new Category();
            data.id = category.id;
            data.name = category.name;
            data.image_id = category.image_id;
            data.save();
        }
        ActiveAndroid.setTransactionSuccessful();
        ActiveAndroid.endTransaction();

    }

    public static void parsePlace(List<DataPlace.Place> places) {
        ActiveAndroid.beginTransaction();
        Place.deleteCategoryPlace();
        for (DataPlace.Place place : places){
            Place data = new Place();
            data.id = place.id;
            data.share_link = place.share_link;
            data.name = place.name;
            data.address = place.address;
            data.latitude = place.latitude;
            data.longitude = place.longitude;
            data.short_description = place.short_description;
            data.description = place.description;
            data.howtogo = place.howtogo;
            data.category_id = place.category_id;
            data.image_id = place.image_id;
            data.hexcode = place.hexcode;
            data.rating = place.rating;
            data.rate_real = place.rate_real;
            data.review_number = place.review_number;
            data.checkin_number= place.checkin_number;
            data.checkin_number= place.checkin_number;
            data.checkin_number= place.checkin_number;
            data.recommend_number= place.recommend_number;
            data.report_number= place.report_number;
            data.approve= place.approve;
            data.save();
        }
        ActiveAndroid.setTransactionSuccessful();
        ActiveAndroid.endTransaction();

    }
}
