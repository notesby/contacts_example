package com.hectormoreno.test.base.network.cache;

import android.content.Context;

import com.hectormoreno.test.base.network.util.NetworkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hectormoreno on 3/21/17.
 */

public class OfflineInterceptor implements Interceptor {


    Context context;

    public OfflineInterceptor(Context context)
    {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isNetworkAvailable(context)) {
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxStale(1, TimeUnit.DAYS)
                    .build();

            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();
        }

        return chain.proceed(request);
    }
}
