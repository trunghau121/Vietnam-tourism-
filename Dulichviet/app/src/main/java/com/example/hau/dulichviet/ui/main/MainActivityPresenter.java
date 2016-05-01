package com.example.hau.dulichviet.ui.main;

import android.support.annotation.NonNull;

import com.example.hau.dulichviet.Dependencies;
import com.example.hau.dulichviet.models.database.Category;
import com.example.hau.dulichviet.models.database.Place;
import com.example.hau.dulichviet.ui.base.MvpView;
import com.example.hau.dulichviet.ui.base.Presenter;
import com.example.hau.dulichviet.data.api.ServerApi;
import com.example.hau.dulichviet.utils.DataMapper;
import com.example.hau.dulichviet.utils.ViewAnimator;


import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by TRUNGHAU on 5/1/2016.
 */
public class MainActivityPresenter extends Presenter<MainActivityPresenter.View> {
    public interface View extends MvpView, ViewAnimator.ViewAnimatorListener {
        void showLoading();

        void hideLoading();

        void showError(int n);

        void showDataPlace(@NonNull List<Place> places);

        void showDataCategory(@NonNull List<Category> category);
    }

    private ServerApi serverAPI;

    public MainActivityPresenter() {
        this.serverAPI = Dependencies.getServerApi();
    }

    public void getDataPlace() {
        final View view = view();
        serverAPI.getService()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataPlace -> {
                            DataMapper.parsePlace(dataPlace.getData());
                            if (view != null) {
                                view.hideLoading();
                                view.showDataPlace(Place.all());
                            }
                        }
                        , throwable -> {
                            Timber.d(throwable.getMessage());
                            if (view != null) {
                                view.hideLoading();
                                view.showError(1);
                            }
                        }
                );
    }


    public void getDataCategory() {
        final View view = view();
        if (view != null) {
            view.showLoading();
        }
        serverAPI.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataCategory -> {
                    DataMapper.parseCategory(dataCategory.getData());
                            if (view != null) {
                                view.showDataCategory(Category.all());
                            }
                        }, throwable -> {
                            Timber.d(throwable.getMessage());
                            if (view != null) {
                                view.hideLoading();
                                view.showError(0);
                            }
                        }
                );
    }


}
