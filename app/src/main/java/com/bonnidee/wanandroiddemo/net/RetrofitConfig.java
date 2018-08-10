package com.bonnidee.wanandroiddemo.net;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;

public class RetrofitConfig {


    private static String TAG = "Http";
    public static final Interceptor LoginngInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Buffer requestBuffer = new Buffer();
            if (request.body()!=null) {
                request.body().writeTo(requestBuffer);

            }else{

                Log.d(TAG, "request body ==null");

            }
            Log.w(TAG,"请求=="+request.url()+"=="+_parseParams(request.body(), requestBuffer));


            return chain.proceed(request);
        }

    };
    @NonNull
    private static String _parseParams(RequestBody body, Buffer requestBuffer) throws UnsupportedEncodingException {
        if (body == null) {
            return "null";
        }
        if (body.contentType() != null && !body.contentType().toString().contains("multipart")) {
            return URLDecoder.decode(requestBuffer.readUtf8(), "UTF-8");
        }
        return "null";
    }

    public static final HttpLoggingInterceptor httpLogingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            Log.d(TAG, message);
        }
    });


}
