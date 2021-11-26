package com.example.unorpproject.retrofit;

import com.example.unorpproject.model.Device;
import com.example.unorpproject.model.Home;
import com.example.unorpproject.model.Result;
import com.example.unorpproject.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SignUpService {
    @GET("/signup/{email}/{password}/{name}/{cpf}")
    Call<Result<User>> signup(@Path("email") String email, @Path("password") String password, @Path("name") String name, @Path("cpf") String cpf);

    @GET("/login/{email}/{password}")
    Call<Result<User>> login(@Path("email") String email, @Path("password") String password);

    @GET("/home/{userId}")
    Call<Result<List<Home>>> findHome(@Path("userId") long userId);

    @POST("/home/insert/{userId}")
    Call<Result<Home>> insert(@Path("userId") long userId, @Query("deviceName") String name,
                              @Query("usageTime") long usageTime, @Query("power") long power,
                              @Query("quantity") long quantity);

    @DELETE("/home/delete/{homeId}")
    Call<Result<Home>> delete(@Path("homeId") long homeId);

    @GET("/device/find/{name}")
    Call<Result<Device>> findDevice(@Path("name") String name);
}
