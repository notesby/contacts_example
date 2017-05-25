package com.hectormoreno.test.ui.contactlist;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hectormoreno.test.R;
import com.hectormoreno.test.base.BaseAppCompatActivity;
import com.hectormoreno.test.base.Lifecycle;
import com.hectormoreno.test.databinding.ActivityMainBinding;
import com.hectormoreno.test.ui.addcontact.AddContactActivity;

public class MainActivity extends BaseAppCompatActivity implements ContactListContract.View{

    MaterialDialog messageDialog,progressDialog;

    MainActivityViewModel viewModel;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        setSupportActionBar(binding.toolbar);

        viewModel = new MainActivityViewModel();

        binding.content.recyclerViewContacts.setLayoutManager(new GridLayoutManager(this,1));
        binding.content.recyclerViewContacts.setAdapter(viewModel.getAdapter());

        swipeRefreshLayout = binding.content.swipeRefreshLayoutContacts;
        swipeRefreshLayout.setOnRefreshListener(() -> {viewModel.getAllContacts();});

        binding.fab.setOnClickListener(v -> {viewModel.onAddContactClick();});

        setupDialogs(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        viewModel.getAllContacts();
    }

    private void setupDialogs(Context context){
        progressDialog = new MaterialDialog.Builder(context)
                .title("Procesando")
                .content("Espere un momento")
                .progress(true,0)
                .autoDismiss(false)
                .build();

        messageDialog = new MaterialDialog.Builder(context)
                .title("")
                .content("")
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startAddContactActivity() {
        Intent intent = new Intent(this, AddContactActivity.class);
        startActivity(intent);
    }

    @Override
    public void showProgressDialog() {
        if (!progressDialog.isShowing())
        {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(String title, String message) {
        messageDialog.setContent(message);
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return viewModel;
    }
}
