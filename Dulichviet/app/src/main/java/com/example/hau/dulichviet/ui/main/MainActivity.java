package com.example.hau.dulichviet.ui.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hau.dulichviet.Dependencies;
import com.example.hau.dulichviet.interfaces.Resourceble;
import com.example.hau.dulichviet.models.DataCategory;
import com.example.hau.dulichviet.models.DataPlace;
import com.example.hau.dulichviet.models.SlideMenuItem;
import com.example.hau.dulichviet.models.database.Category;
import com.example.hau.dulichviet.models.database.Place;
import com.example.hau.dulichviet.network.Networking;
import com.example.hau.dulichviet.R;
import com.example.hau.dulichviet.ui.dialog.DialogFactory;
import com.example.hau.dulichviet.utils.ViewAnimator;
import com.example.hau.dulichviet.data.api.ServerApi;
import com.example.hau.dulichviet.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;


public class MainActivity extends BaseActivity implements MainActivityPresenter.View {
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.left_drawer)
    LinearLayout leftDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ViewAnimator viewAnimator;
    private  List<Place> places = new ArrayList<>();
    private  List<Category> categories = new ArrayList<>();
    private ProgressDialog dialog;
    private ServerApi serverApi;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dialog = DialogFactory.createLoadingDialog(this, "Đang tải dữ liệu ...");
        presenter = new MainActivityPresenter();
        presenter.bindView(this);
        serverApi = Dependencies.getServerApi();
        createMenuList();
        setActionBar();
        getPlaceFromService();
    }

    private void getPlaceFromService() {

        if (Networking.isCheckConnect(this)) {
            presenter.getDataCategory();
            presenter.getDataPlace();
        } else {
            DialogFactory.showTopPopup(getContext(),"No Internet Connection.","Please check your internet connection and try again");
        }
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
        drawerLayout.setScrimColor(Color.TRANSPARENT);

        leftDrawer.setOnClickListener(v -> drawerLayout.closeDrawers());
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
                leftDrawer.removeAllViews();
                leftDrawer.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && leftDrawer.getChildCount() == 0)
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

            ButterKnife.findById(this, R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources()));
            animator.start();
            comitFragment(position);
        }

    }

    private void comitFragment(int category_id) {
        Fragment fr = new FragmentTour();
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
        leftDrawer.addView(view);
    }


    @Override
    public void showLoading() {
        dialog.show();
    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }

    @Override
    public void showError(int n) {
        if (n == 0) {
            Toast.makeText(this, "Không thể tải dữ liệu danh mục.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Không thể tải dữ liệu du lịch.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showDataPlace(@NonNull List<Place> places) {
        this.places = places;
        comitFragment(1);
    }

    @Override
    public void showDataCategory(@NonNull List<Category> category) {
        categories = category;
        for (int i = 0; i < categories.size(); i++) {
            if (Integer.parseInt(categories.get(i).id) == 1) {
                toolbar.setTitle(categories.get(i).name.toUpperCase());
            }
        }
    }

    @Override
    public Context getContext() {
        return this;
    }
}
