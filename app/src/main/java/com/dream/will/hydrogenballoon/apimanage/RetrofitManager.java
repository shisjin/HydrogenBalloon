package com.dream.will.hydrogenballoon.apimanage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.util.SparseArray;

import com.dream.will.hydrogenballoon.MyApp;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitManager {
    private static SparseArray<Retrofit> retrofitList = new SparseArray<>();
    private static OkHttpClient client;

    public static Retrofit getInstance(int type) {
        Retrofit retrofit = retrofitList.get(type);
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstant.getHostByType(type))
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpInstance())
                    .build();
            retrofitList.put(type, retrofit);
        }
        return retrofit;
    }

    private static OkHttpClient getOkHttpInstance() {
        if (null == client) {
            synchronized (RetrofitManager.class) {
                if (null == client) {
                    File file = new File(MyApp.getInstance().getExternalCacheDir(), "okHttp");
                    client = new OkHttpClient.Builder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .addNetworkInterceptor(new CacheControlIntercepter())
                            .addInterceptor(new LogIntercepter())
                            .addInterceptor(new RequestIntercepter())
                            .cache(new Cache(file, 10 * 1024 * 1024))
                            .build();
                }
            }
        }
        return client;
    }

    private static class LogIntercepter implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            okhttp3.Request request = chain.request();
            long t1 = System.nanoTime();
            Log.i("google.karlo", String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            okhttp3.Response response = chain.proceed(request);
            long t2 = System.nanoTime();

            Log.i("google.karlo", String.format("Response message for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    }


    private static class CacheControlIntercepter implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Log.i("google.karlo", "CacheControlIntercepter");
            Request request = chain.request();
            Response response = chain.proceed(request);
            ConnectivityManager manager = (ConnectivityManager) MyApp.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                Log.i("google.karlo", "intercept: response ; isAvailable ");
//                int maxAge = 60 * 60;
//                String cacheControl = request.cacheControl().toString();
                response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
//                        .header("Cache-Control", cacheControl)
                        .build();
            } else {
                Log.i("google.karlo", "intercept: response ; not isAvailable ");
//                int maxStale = 60 * 60 * 24;
                response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
//                        .header("Cache-Control", "public, max-age=" + maxStale)
                        .build();
            }
            return response;
        }
    }

    private static class RequestIntercepter implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            ConnectivityManager manager = (ConnectivityManager) MyApp.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable() || !activeNetworkInfo.isConnected()) {
                Log.i("google.karlo", "intercept: request  not isAvailable");
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            return chain.proceed(request);
        }
    }
}
