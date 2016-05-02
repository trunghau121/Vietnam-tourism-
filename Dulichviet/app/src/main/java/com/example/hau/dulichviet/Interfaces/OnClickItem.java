package com.example.hau.dulichviet.interfaces;

import android.view.View;

import com.example.hau.dulichviet.models.database.Place;

/**
 * Created by HAU on 11/28/2015.
 */
public interface OnClickItem {
    public void shareClick(View v, Place place);
    public void readMoreClick(View v, Place place);
    public void mapClick(View v, Place place);
}
