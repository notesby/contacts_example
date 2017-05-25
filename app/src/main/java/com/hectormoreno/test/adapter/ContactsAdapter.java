package com.hectormoreno.test.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hectormoreno.test.R;
import com.hectormoreno.test.databinding.GridContactListBinding;
import com.hectormoreno.test.model.Contact;
import com.hectormoreno.test.ui.contactviewholder.ContactItemViewModel;
import com.hectormoreno.test.ui.contactviewholder.ContactViewHolder;

import java.util.ArrayList;

/**
 * Created by hectormoreno on 5/24/17.
 */

public class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Contact> contacts;

    public ContactsAdapter(ArrayList<Contact> contacts){
        this.contacts = contacts;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        GridContactListBinding binding = DataBindingUtil.inflate(inflater, R.layout.grid_contact_list,parent,false);

        ContactViewHolder holder = new ContactViewHolder(binding);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ContactViewHolder bindingViewHolder = (ContactViewHolder) holder;
        bindingViewHolder.getBinding().setViewModel(new ContactItemViewModel(contacts.get(position)));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
