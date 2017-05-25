package com.hectormoreno.test.ui.contactlist;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hectormoreno.test.BuildConfig;
import com.hectormoreno.test.MyApplication;
import com.hectormoreno.test.adapter.ContactsAdapter;
import com.hectormoreno.test.base.Lifecycle;
import com.hectormoreno.test.base.NetworkViewModel;
import com.hectormoreno.test.model.Contact;
import com.hectormoreno.test.model.ResponseGeneric;
import com.hectormoreno.test.networking.APIContactService;

import java.util.ArrayList;

import io.reactivex.ObservableEmitter;
import io.reactivex.disposables.Disposable;
import io.reactivex.processors.AsyncProcessor;
import io.reactivex.subscribers.DisposableSubscriber;
import timber.log.Timber;

/**
 * Created by hectormoreno on 5/13/17.
 */

public class MainActivityViewModel extends NetworkViewModel implements ContactListContract.ViewModel {
    private ContactListContract.View view;

    private APIContactService service;
    private AsyncProcessor<Object> processor;
    private Disposable disposable;

    private ArrayList<Contact> contacts;
    private ContactsAdapter adapter;


    MainActivityViewModel()
    {
        this.contacts = new ArrayList<>();
        adapter = new ContactsAdapter(this.contacts);
        service = new APIContactService(MyApplication.getRetrofit());
    }

    @Override
    public void onAddContactClick() {
        if (view != null)
        {
            view.startAddContactActivity();
        }
    }

    @Override
    public void getAllContacts() {
        processor = AsyncProcessor.create();
        disposable = processor.subscribeWith(new GetAllObserver());

        service.getAll().subscribe(processor);
    }

    @Override
    public ContactsAdapter getAdapter() {
        return adapter;
    }

    @Override
    public boolean isRequestingInformation() {
        return false;
    }

    public boolean isNetworkRequestMade(){
        return disposable != null;
    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View view) {
        this.view = (ContactListContract.View) view;
    }

    @Override
    public void onViewDetached() {
        view = null;

        if (isNetworkRequestMade()){
            disposable.dispose();
        }
    }

    @Override
    public void onViewResumed() {
        if (isNetworkRequestMade()){
            processor.subscribe(new GetAllObserver());
        }
    }

    @Override
    public void onViewPaused() {

    }

    private void onGetAllSuccess(ResponseGeneric response)
    {
        if (view != null)
        {
            view.hideProgressDialog();
            if (!response.getError())
            {
                Gson gson = new Gson();
                String jsonData = gson.toJson(response.getData());
                ArrayList<Contact> contacts = gson.fromJson(jsonData,new TypeToken<ArrayList<Contact>>(){}.getType());

                this.contacts.clear();
                this.contacts.addAll(contacts);
                adapter.notifyDataSetChanged();
            }
            else
            {
                view.showMessage("Error","unable to retrieve data");
            }
        }
    }

    private void onGetAllError(Throwable e)
    {
        if (view != null)
        {
            view.hideProgressDialog();
        }

        if (BuildConfig.DEBUG){
            e.printStackTrace();
        }
    }


    class GetAllObserver extends DisposableSubscriber<Object> {


        @Override
        public void onNext(Object o) {
            onGetAllSuccess((ResponseGeneric)o);
        }

        @Override
        public void onError(Throwable t) {
            onGetAllError(t);
        }

        @Override
        public void onComplete() {

        }
    }
}
