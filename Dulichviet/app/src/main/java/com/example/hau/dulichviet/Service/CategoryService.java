package com.example.hau.dulichviet.Service;



import com.example.hau.dulichviet.Models.DataCategory;

import retrofit.Callback;
import retrofit.http.GET;

public interface CategoryService {
    @GET("/service/category")
    public void getCategory(Callback<DataCategory> callback);
}
