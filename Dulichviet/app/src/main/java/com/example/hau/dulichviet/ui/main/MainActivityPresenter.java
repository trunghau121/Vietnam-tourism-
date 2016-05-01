package com.example.hau.dulichviet.ui.main;

import android.support.annotation.NonNull;

import com.example.hau.dulichviet.Dependencies;
import com.example.hau.dulichviet.models.DataCategory;
import com.example.hau.dulichviet.models.DataPlace;
import com.example.hau.dulichviet.ui.base.MvpView;
import com.example.hau.dulichviet.ui.base.Presenter;
import com.example.hau.dulichviet.data.api.ServerApi;


import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by TRUNGHAU on 5/1/2016.
 */
public class MainActivityPresenter extends Presenter<MainActivityPresenter.View> {
    public interface View extends MvpView{
        public void showLoading();

        public void hideLoading();

        public void showDataPlace(@NonNull DataPlace places);

        public void showDataCategory(@NonNull  DataCategory categorys);
    }

    private ServerApi serverAPI;

    public MainActivityPresenter() {
        this.serverAPI = Dependencies.getServerApi();
    }

    public void getDataPlace(){
        final View view = view();
        if (view != null){
            view.showLoading();
        }

        serverAPI.getService()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataPlace -> {
                            if (view != null){
                                view.showDataPlace(dataPlace);
                            }
                        }
                        , throwable -> {
                            Timber.d(throwable.getMessage());
                            if (view != null){
                                view.hideLoading();
                            }
                        }
                );
    }
    public void getDataCategory(){
        final View view = view();
        serverAPI.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataCategory -> {
                    if (view != null){
                        view.showDataCategory(dataCategory);
                    }
                        }, throwable -> {
                            Timber.d(throwable.getMessage());
                        }
                );
    }


}
