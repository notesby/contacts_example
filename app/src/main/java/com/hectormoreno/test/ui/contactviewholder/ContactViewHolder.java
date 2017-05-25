package com.hectormoreno.test.ui.contactviewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hectormoreno.test.databinding.GridContactListBinding;

/**
 * Created by hectormoreno on 5/24/17.
 */

public class ContactViewHolder extends RecyclerView.ViewHolder {

    private GridContactListBinding binding;

    public ContactViewHolder(GridContactListBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public GridContactListBinding getBinding(){
        return binding;
    }
}
