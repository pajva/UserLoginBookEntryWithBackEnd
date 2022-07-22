package com.example.testapp.entity.response;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private  static  final String API_BASE_URL="http://192.168.0.7:9191/";
    private static OkHttpClient.Builder httpClient=new OkHttpClient.Builder();

    private static Retrofit.Builder builder=
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()));

    private  ServiceGenerator(){

    }
    public  static <S> S createService(Class<S> serviceClass){
        httpClient.authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                return response.request().newBuilder().build();
            }
        });
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(httpLoggingInterceptor);
        Retrofit retrofit;
        OkHttpClient client=httpClient.build();
        httpClient.connectTimeout(5, TimeUnit.MINUTES);
        httpClient.readTimeout(5,TimeUnit.MINUTES);
        httpClient.writeTimeout(5,TimeUnit.MINUTES);
        retrofit=builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}