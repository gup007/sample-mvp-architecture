package com.massiveinfinity.delivery;

import android.app.Application;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.massiveinfinity.delivery.data.component.DaggerNetComponent;
import com.massiveinfinity.delivery.data.component.NetComponent;
import com.massiveinfinity.delivery.data.module.AppModule;
import com.massiveinfinity.delivery.data.module.NetModule;

import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static junit.framework.Assert.assertTrue;

/**
 * Created by SurvivoR on 25-08-2017.
 * {@link BaseApiTest}
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public abstract class BaseApiTest {

    protected Context context;
    protected Retrofit mRetrofit;

    public BaseApiTest() {
        this.context = InstrumentationRegistry.getContext();
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://mi-mobile-dev.ap-southeast-1.elasticbeanstalk.com")
                .client(client.build())
                .build();
    }

    @Test
    public void runPreCondition() {
        assertTrue(context != null);
    }

    public abstract void runApiService() throws InterruptedException;
}
