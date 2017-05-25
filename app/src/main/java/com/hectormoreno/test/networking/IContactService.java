package com.hectormoreno.test.networking;

import com.hectormoreno.test.model.Contact;
import com.hectormoreno.test.model.ResponseGeneric;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by hectormoreno on 5/13/17.
 */

public interface IContactService {

    @POST("contact/insert")
    Observable<ResponseGeneric> add(@Body Contact contact);

    @POST("contact/update")
    Observable<ResponseGeneric> update(@Body Contact contact);

    @POST("contact/get")
    Observable<ResponseGeneric> get(@Body Contact contact);

    @POST("contact/delete")
    Observable<ResponseGeneric> delete(@Body Contact contact);

    @GET("contact/get/all")
    Observable<ResponseGeneric> getAll();
}
