package com.magicrecipe;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by HP on 04/03/18.
 */

public interface RequestInterface {

    @GET("api/")
    Observable<Recepies> register(@Query("i") String i);
}
