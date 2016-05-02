package com.example.hau.dulichviet.ui.search_tour;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hau.dulichviet.Constants;
import com.example.hau.dulichviet.models.DataPlace;
import com.example.hau.dulichviet.R;
import com.example.hau.dulichviet.models.database.Place;
import com.example.hau.dulichviet.ui.base.BaseActivity;
import com.example.hau.dulichviet.ui.detail_tour.MainDetail;

import org.parceler.Parcels;

import butterknife.Bind;

/**
 * Created by HAU on 12/2/2015.
 */
public class SearchTour extends BaseActivity implements SearchTourPresenter.View {
    @Bind(R.id.tvName)
    TextView txtName;
    @Bind(R.id.tvContent)
    TextView txtContent;
    @Bind(R.id.tvAddress)
    TextView txtAddress;
    @Bind(R.id.ivTour)
    ImageView ivTour;
    @Bind(R.id.btnMap)
    ImageButton btnMap;
    @Bind(R.id.btnShare)
    ImageButton btnShare;
    @Bind(R.id.btnReadMore)
    Button btnReadMore;
    private float scale;
    private int width;
    private int height;
    private SearchTourPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_list_tour);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        presenter = new SearchTourPresenter();
        presenter.bindView(this);
        presenter.getIntent(getIntent());
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnMap) {


        } else if (v.getId() == R.id.btnShare) {

        } else if (v.getId() == R.id.btnReadMore) {
            presenter.openDetailTour();

        }
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

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showDataPlace(DataPlace.Place place) {
        Glide.with(this).load(Constants.PICASSO + place.image_id + Constants.ORIGIN1).centerCrop().into(ivTour);
        txtName.setText(place.name.toUpperCase());
        String address = place.address;
        txtAddress.setText(Html.fromHtml(address));
        String content = place.description;
        txtContent.setText(Html.fromHtml(content));
        btnMap.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnReadMore.setOnClickListener(this);
    }
}
