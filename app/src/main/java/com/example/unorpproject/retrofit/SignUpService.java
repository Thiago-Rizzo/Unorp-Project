package com.example.unorpproject.retrofit;

import com.example.unorpproject.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SignUpService {
    @GET("/signup/{email}/{password}/{name}/{cpf}")
    Call<Result> signup(@Path("email") String email, @Path("password") String password, @Path("name") String name, @Path("cpf") String cpf);

    @GET("/login/{email}/{password}")
    Call<Result> login(@Path("email") String email, @Path("password") String password);
}
