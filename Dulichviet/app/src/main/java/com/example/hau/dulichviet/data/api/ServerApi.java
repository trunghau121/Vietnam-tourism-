package com.example.hau.dulichviet.data.api;

import com.example.hau.dulichviet.models.DataCategory;
import com.example.hau.dulichviet.models.DataPlace;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by TRUNGHAU on 4/24/2016.
 */
public interface ServerApi {
    String PATCH_CATEGORY = "/service/category";
    String PATCH_PLACE = "/service/place";

    @GET(PATCH_CATEGORY)
    Observable<DataCategory> getCategory();

    @GET(PATCH_PLACE)
    Observable<DataPlace> getService();

}
