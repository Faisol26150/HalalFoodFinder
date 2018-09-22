package com.aburubban.halalfoodfinder.Remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abu Rubban on 22-Sep-18.
 */

public class RetrofitClient {
    private static Retrofit retrofit=null;

    public static  Retrofit getClient(String baseURL)
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
