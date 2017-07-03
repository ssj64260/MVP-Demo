package com.cxb.mvp_project.service;

import com.cxb.mvp_project.config.API;
import com.cxb.mvp_project.config.ServiceApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求配置
 */

public class ServiceCilent {

    private static ServiceApi mService;

    public static ServiceApi getService() {
        if (mService == null) {
            createService();
        }
        return mService;
    }

    private static void createService() {
        mService = createRetrofit().create(ServiceApi.class);
    }

    private static Retrofit createRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpInterceptor().setLevel(HttpInterceptor.BODY))
                .build();

        return new Retrofit.Builder()
                .baseUrl(API.BASE_HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

}
