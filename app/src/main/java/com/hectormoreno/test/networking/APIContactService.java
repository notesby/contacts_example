package com.hectormoreno.test.networking;

import com.hectormoreno.test.MyApplication;
import com.hectormoreno.test.model.Contact;
import com.hectormoreno.test.model.ResponseGeneric;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by hectormoreno on 5/13/17.
 */

public class APIContactService {
    private IContactService service;
    private boolean isRequestingInformation;

    public APIContactService(Retrofit retrofit)
    {
        service = retrofit.create(IContactService.class);
    }


    public Observable<ResponseGeneric> addContact(Contact contact)
    {
        return service.add(contact)
                .doOnSubscribe(disposable -> {isRequestingInformation = true;})
                .doOnTerminate(() -> {isRequestingInformation = false;})
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Maybe<ResponseGeneric> updateContact(Contact contact)
    {
        return service.update(contact)
                .doOnSubscribe(disposable -> {isRequestingInformation = true;})
                .doOnTerminate(() -> {isRequestingInformation = false;})
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .firstElement();
    }

    public Maybe<ResponseGeneric> getContact(Contact contact)
    {
        return service.get(contact)
                .doOnSubscribe(disposable -> {isRequestingInformation = true;})
                .doOnTerminate(() -> {isRequestingInformation = false;})
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .firstElement();
    }

    public Maybe<ResponseGeneric> deleteContact(Contact contact)
    {
        return service.delete(contact)
                .doOnSubscribe(disposable -> {isRequestingInformation = true;})
                .doOnTerminate(() -> {isRequestingInformation = false;})
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .firstElement();
    }

    public Flowable<ResponseGeneric> getAll()
    {
        return service.getAll()
                .doOnSubscribe(disposable -> {isRequestingInformation = true;})
                .doOnTerminate(() -> {isRequestingInformation = false;})
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .toFlowable(BackpressureStrategy.BUFFER);
    }
}
