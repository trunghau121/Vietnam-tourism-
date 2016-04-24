package com.example.hau.dulichviet;

import android.support.annotation.NonNull;

import com.example.hau.dulichviet.data.api.ServerApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import timber.log.Timber;

/**
 * Created by TRUNGHAU on 4/24/2016.
 */
public class Dependencies {
    private final static int TIME_OUT = 30 ;
    public static ServerApi serverApi;

    public static void init(){
        HttpLoggingInterceptor interceptor =provideHttpLoggingInterceptor();
        OkHttpClient client = provideOkHttpClient(interceptor);
        serverApi = provideServerApi(client);
    }

    public static ServerApi getServerApi() {
        return serverApi;
    }

    public static HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor((message) -> Timber.d(message));
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return interceptor;

    }

    public static OkHttpClient provideOkHttpClient(@NonNull HttpLoggingInterceptor interceptor){
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(interceptor);
        client.setConnectTimeout(TIME_OUT, TimeUnit.SECONDS);
        client.setWriteTimeout(TIME_OUT, TimeUnit.SECONDS);
        client.setReadTimeout(TIME_OUT, TimeUnit.SECONDS);

        return client;
    }

    public static ServerApi provideServerApi(@NonNull OkHttpClient client){
        Gson gson = new  GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL , Modifier.TRANSIENT , Modifier.STATIC).create();
        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.URL_BELIAT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        return builder.build().create(ServerApi.class);
    }


}
