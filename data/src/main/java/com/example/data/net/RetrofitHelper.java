package com.example.data.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 11/6/17.
 */

public class RetrofitHelper {
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        if (retrofit!=null){
            return retrofit;
        }
        else {
            retrofit=new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit;
        }
    }
}
