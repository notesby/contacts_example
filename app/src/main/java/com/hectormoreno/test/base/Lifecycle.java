package com.hectormoreno.test.base;

import android.support.annotation.NonNull;

/**
 * Created by hectormoreno on 3/22/17.
 */

public interface Lifecycle {
    interface View
    {

    }

    interface ViewModel
    {
        void onViewAttached(@NonNull Lifecycle.View view);
        void onViewDetached();
        void onViewResumed();
        void onViewPaused();
    }
}
