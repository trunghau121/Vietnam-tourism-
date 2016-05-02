package com.example.hau.dulichviet.ui.main;

import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.example.hau.dulichviet.interfaces.OnClickItem;
import com.example.hau.dulichviet.models.database.Place;
import com.example.hau.dulichviet.ui.base.MvpView;
import com.example.hau.dulichviet.ui.base.Presenter;
import com.example.hau.dulichviet.utils.DataMapper;
import com.example.hau.dulichviet.utils.Navigator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TRUNGHAU on 5/1/2016.
 */
public class FragmentTourPresenter extends Presenter<FragmentTour>{
    public interface View extends MvpView,OnClickItem{

          void showDataSearch(@NonNull  MatrixCursor c);

          void showDataPlace(@NonNull List<Place> places);
    }
    private List<Place> listSuggestion;
    private List<Place> places;
    MatrixCursor c;
    public FragmentTourPresenter() {
        places = new ArrayList<>();
    }

    public void searchTour(String query) {
        c = new MatrixCursor(new String[]{BaseColumns._ID, "cityName"});
        listSuggestion=new ArrayList<>();
        String name="";
        if (query.trim().length() > 0) {
            for (int i = 0; i < places.size(); i++) {
                name = places.get(i).name;
                int n = name.toUpperCase().indexOf(query.toUpperCase());
                if (n != -1) {
                    listSuggestion.add(places.get(i));
                    c.addRow(new Object[]{i, name});
                }
            }
        }
        final View view = view();
        if (view != null) {
            view.showDataSearch(c);
        }
    }

    public void openSearchTour(int position){
        final View view = view();
        if (view != null) {
            Navigator.openSearchTour(view.getContext(), DataMapper.getPlace(listSuggestion.get(position)));
        }
    }

    public void openDetailTour(Place place){
        final View view = view();
        if (view != null) {
            Navigator.openDetailTour(view.getContext(), DataMapper.getPlace(place));
        }
    }


    public void getIntent(Bundle bundle){
        int category_id = bundle.getInt("category_id");
        getDataPlaces(category_id);
    }

    public void getDataPlaces(int category_id){
        final  View view =view();
        if (view != null){
            places = Place.getPlacesCategory(category_id);
            view.showDataPlace(places);
        }
    }
}
