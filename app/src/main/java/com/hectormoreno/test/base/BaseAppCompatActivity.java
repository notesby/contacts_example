package com.hectormoreno.test.base;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by hectormoreno on 3/22/17.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity implements Lifecycle.View {

    protected abstract Lifecycle.ViewModel getViewModel();

    @Override
    protected void onResume() {
        super.onResume();
        getViewModel().onViewResumed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getViewModel().onViewAttached(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getViewModel().onViewDetached();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getViewModel().onViewPaused();
    }
}
