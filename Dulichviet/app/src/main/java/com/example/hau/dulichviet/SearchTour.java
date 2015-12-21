package com.example.hau.dulichviet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hau.dulichviet.Models.Constant;
import com.example.hau.dulichviet.Models.DataPlace;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HAU on 12/2/2015.
 */
public class SearchTour extends AppCompatActivity implements View.OnClickListener {
    @InjectView(R.id.tvName)
    TextView txtName;
    @InjectView(R.id.tvContent)
    TextView txtContent;
    @InjectView(R.id.tvAddress)
    TextView txtAddress;
    @InjectView(R.id.ivTour)
    ImageView ivTour;
    @InjectView(R.id.btnMap)
    ImageButton btnMap;
    @InjectView(R.id.btnShare)
    ImageButton btnShare;
    @InjectView(R.id.btnReadMore)
    Button btnReadMore;
    DataPlace.Place place;
    private float scale;
    private int width;
    private int height;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_list_tour);
        ButterKnife.inject(this);
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
        Glide.with(this).load(Constant.PICASSO + place.getImage_id() + Constant.ORIGIN1).override(width, height).centerCrop().into(ivTour);
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
