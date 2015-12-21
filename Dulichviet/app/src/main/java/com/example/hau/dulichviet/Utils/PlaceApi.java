package com.example.hau.dulichviet.Utils;


import com.example.hau.dulichviet.Service.CategoryService;
import com.example.hau.dulichviet.Service.PlaceService;

public class PlaceApi extends BaseApi {
    private PlaceService placeService;
    private CategoryService categoryService;

    public PlaceService service(){
        if (placeService ==null){
            placeService = restAdapter.create(PlaceService.class);
        }
        return placeService;
    }
    public CategoryService category(){
        if (categoryService==null){
            categoryService =restAdapter.create(CategoryService.class);
        }
        return  categoryService;
    }
}
