package com.example.hau.dulichviet.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by TRUNGHAU on 5/1/2016.
 */
public class BaseHolder<V> extends RecyclerView.ViewHolder {

    public BaseHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(V data) {

    }

    public void bindEvent() {

    }
}
