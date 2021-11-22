package com.example.unorpproject.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestRetrofit {

    private final SignUpService singUpService;

    public RequestRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.100:8080")
                .addConverterFactory(GsonConverterFactory.create()).build();
        singUpService = retrofit.create(SignUpService.class);
    }

    public SignUpService getSignUpService() {
        return singUpService;
    }
}
