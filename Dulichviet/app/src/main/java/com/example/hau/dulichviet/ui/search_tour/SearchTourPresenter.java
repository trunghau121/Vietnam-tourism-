package com.example.hau.dulichviet.ui.search_tour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hau.dulichviet.models.DataPlace;
import com.example.hau.dulichviet.models.database.Place;
import com.example.hau.dulichviet.ui.base.MvpView;
import com.example.hau.dulichviet.ui.base.Presenter;
import com.example.hau.dulichviet.utils.DataMapper;
import com.example.hau.dulichviet.utils.Navigator;

import org.parceler.Parcels;

/**
 * Created by TRUNGHAU on 5/2/2016.
 */
public class SearchTourPresenter extends Presenter<SearchTourPresenter.View> {
    public interface View extends MvpView, android.view.View.OnClickListener {
        void showDataPlace(DataPlace.Place place);
    }

    private DataPlace.Place place;

    public void getIntent(Intent intent) {
        final View view = view();
        if (intent != null && view != null) {
            Bundle extra = intent.getExtras();
            if (extra != null && extra.containsKey("data")) {
                place = Parcels.unwrap(extra.getParcelable("data"));
                view.showDataPlace(place);
            }
        }
    }
    public void openDetailTour(){
        final View view = view();
        if (view != null) {
            Navigator.openDetailTour(view.getContext(), place);
        }
    }
}
