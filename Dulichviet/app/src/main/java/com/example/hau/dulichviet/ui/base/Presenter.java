package com.example.hau.dulichviet.ui.base;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by TRUNGHAU on 5/1/2016.
 */
public class Presenter<V> {
    @Nullable
    private volatile V view;

    protected CompositeSubscription subscriptions;

    @CallSuper
    public void bindView(@NonNull V view) {
        final V previousView = this.view;

        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    @Nullable
    protected V view() {
        return view;
    }


    @CallSuper
    public void unbindView(@NonNull V view) {
        final V previousView = this.view;

        if (subscriptions != null) {
            subscriptions.clear();
        }

        if (previousView == view) {
            this.view = null;
        } else {
            throw new IllegalStateException("Unexpected view! previousView = " + previousView + ", view to unbind = " + view);
        }

    }
}