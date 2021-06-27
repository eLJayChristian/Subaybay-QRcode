package com.example.subaybay.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Client {

    public static Retrofit getClient(String url){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return  retrofit;
    }
    // for plain string response
    public static Retrofit getClientButPlainString(String url){

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(url)
                .build();

        return retrofit;
    }
}
