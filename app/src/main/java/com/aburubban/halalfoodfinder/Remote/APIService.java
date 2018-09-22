package com.aburubban.halalfoodfinder.Remote;

import com.aburubban.halalfoodfinder.Model.MyResponse;
import com.aburubban.halalfoodfinder.Model.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Abu Rubban on 22-Sep-18.
 */

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA2iy652s:APA91bHUkbgDo8vcewYxprFXvTqoKE5rz0SbcczL6H5uRx8budzJbdAZPD89Q5HX-bDtNn6_r7DMsFXf3fss61bBydMCMWYRvEpA1rJsDgFgj5Fj6eLeKKU4Jdz2yZPZ7QjnraM8_PWC"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
