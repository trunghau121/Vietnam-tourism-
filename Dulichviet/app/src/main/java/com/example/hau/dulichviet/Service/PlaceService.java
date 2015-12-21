package com.example.hau.dulichviet.Service;



import com.example.hau.dulichviet.Models.DataPlace;

import retrofit.Callback;
import retrofit.http.GET;

public interface PlaceService {

    @GET("/service/place")
    public void getPlace(Callback<DataPlace> callback);

}
