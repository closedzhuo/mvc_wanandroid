package com.bonnidee.wanandroiddemo.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
//网络管理控件
public class HttpManager {


    private long CONNNECTTIME = 60;
    private long WRITHETIME = 60;
    private long READTIME = 60;
    private String BaseUrl = "http://www.wanandroid.com/";
    public static HttpManager httpManager = new HttpManager();

    public static HttpManager httpManager() {
        return httpManager;

    }

    public OkHttpClient okHttpClient() {
        RetrofitConfig.httpLogingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(RetrofitConfig.LoginngInterceptor)
                .addInterceptor(RetrofitConfig.httpLogingInterceptor)
                .connectTimeout(CONNNECTTIME, TimeUnit.SECONDS)
                .writeTimeout(WRITHETIME, TimeUnit.SECONDS)
                .readTimeout(READTIME, TimeUnit.SECONDS).build();


    }

    // retrofit 请求
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient())
                .baseUrl(BaseUrl)
                .build();

    }


}
