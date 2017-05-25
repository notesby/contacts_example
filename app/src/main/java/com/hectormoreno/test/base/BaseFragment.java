package com.hectormoreno.test.base;


import android.support.v4.app.Fragment;

/**
 * Created by hectormoreno on 3/22/17.
 */

public abstract class BaseFragment extends Fragment implements Lifecycle.View{

    protected abstract Lifecycle.ViewModel getViewModel();

    @Override
    public void onStart() {
        super.onStart();

        getViewModel().onViewAttached(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        getViewModel().onViewDetached();
    }

    @Override
    public void onResume() {
        super.onResume();

        getViewModel().onViewResumed();
    }

    @Override
    public void onPause() {
        super.onPause();

        getViewModel().onViewPaused();
    }
}
