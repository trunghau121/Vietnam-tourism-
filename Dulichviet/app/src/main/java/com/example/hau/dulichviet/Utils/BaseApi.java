package com.example.hau.dulichviet.Utils;




import com.example.hau.dulichviet.Models.Constant;

import retrofit.RestAdapter;


public class BaseApi {

    public RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(Constant.HOST)
            .build();
}
