package com.example.hau.dulichviet.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.hau.dulichviet.models.DataPlace;
import com.example.hau.dulichviet.models.database.Place;
import com.example.hau.dulichviet.ui.search_tour.SearchTour;

import org.parceler.Parcels;

/**
 * Created by TRUNGHAU on 5/1/2016.
 */
public class Navigator {

    public static void openSearchTour(Context context , Place place){
        Intent it = new Intent(context, SearchTour.class);
        it.putExtra("data", Parcels.wrap(place));
        context.startActivity(it);
    }
}
