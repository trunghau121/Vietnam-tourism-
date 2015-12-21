package com.example.hau.dulichviet;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
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
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.example.hau.dulichviet.Adapter.AdapterSliderImage;
import com.example.hau.dulichviet.Models.Constant;
import com.example.hau.dulichviet.Models.DataPlace;
import com.example.hau.dulichviet.Models.ParseHtml;
import com.example.hau.dulichviet.Network.Networking;
import com.example.hau.dulichviet.Utils.PlaceApi;
import com.example.hau.dulichviet.Utils.Share;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HAU on 11/28/2015.
 */
public class MainDetail extends AppCompatActivity implements View.OnClickListener {
    @InjectView(R.id.tvAddressDetail)
    TextView tvAddressDetail;
    @InjectView(R.id.tvNameDetail)
    TextView tvNameDetail;
    @InjectView(R.id.tvContentDetail)
    TextView tvContentDetail;
    @InjectView(R.id.tvGoto)
    TextView tvGoto;
    @InjectView(R.id.tb_main)
    Toolbar tbDetail;
    @InjectView(R.id.ll_goto)
    LinearLayout llGoto;
    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout toolbarLayout;
    @InjectView(R.id.nsvDetail)
    NestedScrollView nsvDetail;
    @InjectView(R.id.fab)
    FloatingActionMenu fab;
    @InjectView(R.id.fabShare)
    FloatingActionButton fabShare;
    @InjectView(R.id.fabMap)
    FloatingActionButton fabMap;
    @InjectView(R.id.indicator)
    CirclePageIndicator indicator;
    @InjectView(R.id.pager)
    ViewPager mPager;
    DataPlace.Place place;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    CallbackManager callbackManager;
    Share share;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_tour);
        ButterKnife.inject(this);
        if (getIntent() != null) {
            if (getIntent().getBundleExtra("bundle") != null) {
                place = (DataPlace.Place) getIntent().getBundleExtra("bundle").getSerializable("data");
                new GetDataHtml().execute(place.getShare_link() + "#tab-photo");
            }
        }

    }


    public void Init() {
        tbDetail.setTitle(" ");
        setSupportActionBar(tbDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        toolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.btn_context_menu_normal));
        nsvDetail.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > 0) {
                    fab.hideMenuButton(true);
                    toolbarLayout.setTitle(place.getName().toUpperCase());
                    tvNameDetail.setVisibility(View.INVISIBLE);
                } else {
                    fab.showMenuButton(true);
                    toolbarLayout.setTitle("");
                    tvNameDetail.setVisibility(View.VISIBLE);
                }
            }
        });
        fabShare.setOnClickListener(this);
        fabMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fabMap) {
            Intent it =new Intent(MainDetail.this,MapTour.class);
            Bundle bundle =new Bundle();
            bundle.putSerializable("data",place);
            it.putExtra("bundle",bundle);
            startActivity(it);

        } else if (v.getId() == R.id.fabShare) {
        //    if (Networking.isCheckConnect(this))
                Share.shareURL(place.getShare_link(), this);
//            else
//            {
//                new MaterialDialog.Builder(this)
//                        .title(null)
//                        .content("Điện thoại của bạn hiện giờ không có internet.")
//                        .positiveText("OK")
//                        .negativeColor(Color.BLUE)
//                        .backgroundColor(Color.WHITE)
//                        .show();
//            }

        }

    }

    class GetDataHtml extends AsyncTask<String, ArrayList<String>, Void> {

        @Override
        protected Void doInBackground(String... params) {

            ArrayList<String> urls = new ArrayList<>();
         //   if (Networking.isCheckConnect(getApplication()))
                urls = ParseHtml.getImageHtml(params[0]);
            publishProgress(urls);
            return null;
        }

        @Override
        protected void onProgressUpdate(ArrayList<String>... values) {
            createSliderImage(values[0]);
            Init();
            setData();
            super.onProgressUpdate(values);
        }
    }

    public void createSliderImage(ArrayList<String> images) {
        mPager.setAdapter(new AdapterSliderImage(getApplicationContext(), images));
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);
        NUM_PAGES = images.size();
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2000);

        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }

    public void setData() {
        tvNameDetail.setText(place.getName().toUpperCase());
        tvAddressDetail.setText(place.getAddress());
        tvContentDetail.setText(Html.fromHtml(place.getDescription()));
        if (place.getHowtogo().length() > 0) {
            tvGoto.setText(Html.fromHtml(place.getHowtogo()));
        } else {
            llGoto.setVisibility(View.INVISIBLE);
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
