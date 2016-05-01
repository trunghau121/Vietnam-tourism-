package com.example.hau.dulichviet.ui.search_tour;

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
import com.example.hau.dulichviet.ui.base.BaseActivity;
import com.example.hau.dulichviet.ui.detail_tour.MainDetail;

import butterknife.Bind;

/**
 * Created by HAU on 12/2/2015.
 */
public class SearchTour extends BaseActivity implements View.OnClickListener {
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
    DataPlace.Place place;
    private float scale;
    private int width;
    private int height;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_list_tour);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        scale = this.getResources().getDisplayMetrics().density;
        width = this.getResources().getDisplayMetrics().widthPixels - (int) (14 * scale + 0.5f);
        height = (width / 16) * 9;
        if (getIntent() != null) {
            if (getIntent().getBundleExtra("bundle") != null) {
                place = (DataPlace.Place) getIntent().getBundleExtra("bundle").getSerializable("data");
                setData();
            }
        }
    }

    private void setData() {
        Glide.with(this).load(Constants.PICASSO + place.getImage_id() + Constants.ORIGIN1).override(width, height).centerCrop().into(ivTour);
        txtName.setText(place.getName().toUpperCase());
        String address = place.getAddress();
        txtAddress.setText(Html.fromHtml(address));
        String content = place.getDescription();
        txtContent.setText(Html.fromHtml(content));
        btnMap.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnReadMore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnMap) {


        } else if (v.getId() == R.id.btnShare) {

        } else if (v.getId() == R.id.btnReadMore) {
            Intent it = new Intent(this, MainDetail.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",place);
            it.putExtra("bundle", bundle);
            startActivity(it);

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
}
