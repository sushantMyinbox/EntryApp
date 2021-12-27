package com.mim.entryapp.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static ApiService apiService = null;

    private static String BASE_URL="http://103.205.64.220:17250/societyAPI/";
//    http://103.205.64.220:17250/societyAPI/api/societyApp/SaveRegistrationDetail

    public static ApiService getApiService(){
        if (apiService == null){
            getRetrofitClient();
        }
        return apiService;
    }

    public  static OkHttpClient getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build();
        return  client;
    }


    private static void getRetrofitClient() {
        apiService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);
    }
}
