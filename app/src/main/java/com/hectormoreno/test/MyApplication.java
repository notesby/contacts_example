package com.hectormoreno.test;

import android.app.Application;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.hectormoreno.test.base.network.cache.OfflineInterceptor;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by hectormoreno on 5/13/17.
 */

public class MyApplication extends Application {


    private static Retrofit retrofit;
    private static ClearableCookieJar cookieJar;

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.BUILD_TYPE.equals("debug"))
        {
            Timber.plant(new Timber.DebugTree());
        }

        File cacheFile = new File(this.getCacheDir(),"http_cache");
        Cache cache = new Cache(cacheFile,5 * 1024 * 1024);

        cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(this.getBaseContext()));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new OfflineInterceptor(this))
                .addInterceptor(new HttpLoggingInterceptor(message -> {
                    Timber.d(message);}))
                .cache(cache)
                .cookieJar(cookieJar)
                .build();


        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static ClearableCookieJar getCookieJar() {
        return cookieJar;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
