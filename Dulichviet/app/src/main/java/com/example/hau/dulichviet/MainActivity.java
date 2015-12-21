package com.example.hau.dulichviet;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hau.dulichviet.Broadcast.ListenNetWork;
import com.example.hau.dulichviet.Fragments.fragment_tour;
import com.example.hau.dulichviet.Interfaces.EventNetWork;
import com.example.hau.dulichviet.Interfaces.Resourceble;
import com.example.hau.dulichviet.Models.DataCategory;
import com.example.hau.dulichviet.Models.DataPlace;
import com.example.hau.dulichviet.Models.SlideMenuItem;
import com.example.hau.dulichviet.Network.Networking;
import com.example.hau.dulichviet.Utils.PlaceApi;
import com.example.hau.dulichviet.Utils.ViewAnimator;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ViewAnimator viewAnimator;
    private LinearLayout linearLayout;
    Toolbar toolbar;
    public static ArrayList<DataPlace.Place> arrayPlace = new ArrayList<DataPlace.Place>();
    public static ArrayList<DataCategory.Category> arrayCategory = new ArrayList<DataCategory.Category>();
    MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPlaceFromService();
    }

    private void getPlaceFromService() {

          //  if(Networking.isCheckConnect(this)) {
                dialog = new MaterialDialog.Builder(this)
                        .title(null)
                        .content("Đang tải dữ liệu ...")
                        .progress(true, 0)
                        .show();
                PlaceApi placeApi = new PlaceApi();
                placeApi.category().getCategory(new Callback<DataCategory>() {
                    @Override
                    public void success(DataCategory dataCategory, Response response) {
                        arrayCategory = dataCategory.getData();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
                placeApi.service().getPlace(new Callback<DataPlace>() {
                    @Override
                    public void success(DataPlace dataPlace, Response response) {
                        arrayPlace = dataPlace.getData();
                        dialog.dismiss();
                        setActionBar();
                        comitFragment(1);
                        createMenuList();

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }

                });
//            }else{
//                new MaterialDialog.Builder(this)
//                        .content("Không có internet.Vui lòng kiểm tra lại!")
//                        .positiveText("OK")
//                        .contentColor(Color.BLACK)
//                        .positiveColor(Color.BLUE)
//                        .backgroundColor(Color.WHITE)
//                        .show();
//            }
    }

    private void createMenuList() {
        SlideMenuItem menuItem8 = new SlideMenuItem(0, R.mipmap.icn_close);
        list.add(menuItem8);
        SlideMenuItem menuItem0 = new SlideMenuItem(1, R.mipmap.icon1);
        list.add(menuItem0);
        SlideMenuItem menuItem = new SlideMenuItem(6, R.mipmap.icon2);
        list.add(menuItem);
        SlideMenuItem menuItem2 = new SlideMenuItem(2, R.mipmap.icon3);
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem(15, R.mipmap.icon11);
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem(7, R.mipmap.icon4);
        list.add(menuItem4);
        SlideMenuItem menuItem5 = new SlideMenuItem(5, R.mipmap.icon5);
        list.add(menuItem5);
        SlideMenuItem menuItem6 = new SlideMenuItem(13, R.mipmap.icon6);
        list.add(menuItem6);
        SlideMenuItem menuItem7 = new SlideMenuItem(16, R.mipmap.icon7);
        list.add(menuItem7);
        SlideMenuItem menuItem9 = new SlideMenuItem(14, R.mipmap.icon8);
        list.add(menuItem9);

        viewAnimator = new ViewAnimator<>(getApplicationContext(), list, drawerLayout, this);
    }

    private void setActionBar() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);

        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    private void replaceFragment(int position, int topPosition) {
        if (position != 0) {

            View view = findViewById(R.id.content_frame);
            int finalRadius = Math.max(view.getWidth(), view.getHeight());
            SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
            animator.setInterpolator(new AccelerateInterpolator());
            animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

            findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources()));
            animator.start();
            comitFragment(position);
        }

    }

    private void comitFragment(int category_id) {
        for (int i = 0; i < arrayCategory.size(); i++) {
            if (Integer.parseInt(arrayCategory.get(i).getId()) == category_id) {
                toolbar.setTitle(arrayCategory.get(i).getName().toUpperCase());
            }
        }
        Fragment fr = new fragment_tour();
        Bundle bundle = new Bundle();
        bundle.putInt("category_id", category_id);
        fr.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fr).commit();
    }

    @Override
    public void onSwitch(Resourceble slideMenuItem, int topPosition) {
        replaceFragment(slideMenuItem.getPosition(), topPosition);
    }


    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }


}
