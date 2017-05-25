package com.hectormoreno.test.ui.contactlist;

import com.hectormoreno.test.adapter.ContactsAdapter;
import com.hectormoreno.test.base.Lifecycle;

/**
 * Created by hectormoreno on 5/13/17.
 */

public interface ContactListContract {
    interface View extends Lifecycle.View{
        void startAddContactActivity();
        void showProgressDialog();
        void hideProgressDialog();

        void showMessage(String title, String message);

    }

    interface ViewModel extends  Lifecycle.ViewModel{
        void onAddContactClick();
        void getAllContacts();
        ContactsAdapter getAdapter();
    }
}
