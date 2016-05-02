package com.example.hau.dulichviet.ui.detail_tour;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hau.dulichviet.Constants;
import com.example.hau.dulichviet.models.DataPlace;
import com.example.hau.dulichviet.models.ParseHtml;
import com.example.hau.dulichviet.R;
import com.example.hau.dulichviet.ui.base.BaseActivity;
import com.example.hau.dulichviet.ui.search_tour.SearchTourPresenter;
import com.example.hau.dulichviet.utils.Share;
import com.facebook.CallbackManager;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


import butterknife.Bind;

/**
 * Created by HAU on 11/28/2015.
 */
public class MainDetail extends BaseActivity implements MainDetailPresenter.View {
    @Bind(R.id.tvAddressDetail)
    TextView tvAddressDetail;
    @Bind(R.id.tvNameDetail)
    TextView tvNameDetail;
    @Bind(R.id.tvContentDetail)
    TextView tvContentDetail;
    @Bind(R.id.tvGoto)
    TextView tvGoto;
    @Bind(R.id.tb_main)
    Toolbar tbDetail;
    @Bind(R.id.ll_goto)
    LinearLayout llGoto;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.nsvDetail)
    NestedScrollView nsvDetail;
    @Bind(R.id.fab)
    FloatingActionMenu fab;
    @Bind(R.id.fabShare)
    FloatingActionButton fabShare;
    @Bind(R.id.fabMap)
    FloatingActionButton fabMap;
    DataPlace.Place place;
    @Bind(R.id.imgTour)
    ImageView imgTour;
    private MainDetailPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_tour);
        presenter = new MainDetailPresenter();
        presenter.bindView(this);
        presenter.getIntent(getIntent());
    }


    public void Init() {
        tbDetail.setTitle(" ");
        setSupportActionBar(tbDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        toolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.btn_context_menu_normal));
        nsvDetail.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY > 0) {
                fab.hideMenuButton(true);
                toolbarLayout.setTitle(place.name.toUpperCase());
                tvNameDetail.setVisibility(View.INVISIBLE);
            } else {
                fab.showMenuButton(true);
                toolbarLayout.setTitle("");
                tvNameDetail.setVisibility(View.VISIBLE);
            }
        });
        fabShare.setOnClickListener(this);
        fabMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fabMap) {

        } else if (v.getId() == R.id.fabShare) {
                Share.shareURL(place.share_link, this);

        }

    }

    @Override
    public void showDataPlace(DataPlace.Place place) {
        this.place = place;
        Init();
        Glide.with(this).load(Constants.PICASSO + place.image_id + Constants.ORIGIN1).centerCrop().into(imgTour);
        tvNameDetail.setText(place.name.toUpperCase());
        tvAddressDetail.setText(place.address);
        tvContentDetail.setText(Html.fromHtml(place.description));
        if (place.howtogo.length() > 0) {
            tvGoto.setText(Html.fromHtml(place.howtogo));
        } else {
            llGoto.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public Context getContext() {
        return null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }


}
