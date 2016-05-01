package com.example.hau.dulichviet.ui.base;

import android.app.LauncherActivity;
import android.support.v7.app.AppCompatActivity;

import com.example.hau.dulichviet.R;

import butterknife.ButterKnife;

/**
 * Created by TRUNGHAU on 5/1/2016.
 */
public class BaseActivity extends AppCompatActivity{
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
