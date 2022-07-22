package com.example.testapp.entity.response;
import com.example.testapp.entity.Book;
import com.example.testapp.entity.LoginResponseUser;
import com.example.testapp.entity.RegisterResponseUser;
import com.example.testapp.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiClient {

        @POST("login")
        Call<Boolean> login_inUser(@Body User user);

        @POST("SaveUser")
        Call<RegisterResponseUser>register_user(@Body User user );

        @POST("saveBook")
        Call<Void> saveBook(@Body Book book);

        @GET("findAllBook")
        Call<List<Book>> findAllBook();

        @POST("updateBook")
        Call<Void> updateBook(@Body Book book);

        @GET("deleteBook/{id}")
        Call<Void> deleteBook(@Path("id")int id);

        @GET("searchBook/{id}")
        Call<Book> searchBook(@Path("id")int id);

        @GET("findBookByPrice/{price}")
        Call<List<Book>> findBookByPrice(@Path("price")int price);

    }

