package com.example.franciscoandrade.retrofitbasics;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by franciscoandrade on 12/17/17.
 */

public interface ServiceAPI {

    @GET("api/breed/hound/images")
    Call<RootObject> getResponseGet();


}
