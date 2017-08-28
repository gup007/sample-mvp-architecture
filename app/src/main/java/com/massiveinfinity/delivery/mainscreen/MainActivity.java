package com.massiveinfinity.delivery.mainscreen;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.massiveinfinity.delivery.BaseActivity;
import com.massiveinfinity.delivery.DeliveryApp;
import com.massiveinfinity.delivery.R;
import com.massiveinfinity.delivery.data.Delivery;
import com.massiveinfinity.delivery.mainscreen.fragment.DeliveryDetailFragment;
import com.massiveinfinity.delivery.mainscreen.fragment.DeliveryListFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainScreenContract.View, DeliverySelectListener {

    protected Toolbar toolbar;

    @Inject
    MainScreenPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Things to Deliver");
        setSupportActionBar(toolbar);

        DaggerMainScreenComponent.builder()
                .netComponent(((DeliveryApp) getApplicationContext()).getNetComponent())
                .mainScreenModule(new MainScreenModule(this))
                .build().inject(this);

        showLoadingDialog();
        mainPresenter.loadPost();
    }

    @Override
    public void showPosts() {
        hideLoadingDialog();
        if (findViewById(R.id.fragment_container) != null) {

            // Create an Instance of Fragment
            DeliveryListFragment fragment = new DeliveryListFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    @Override
    public void showError(String message) {
        hideLoadingDialog();
        showSnackbar(TextUtils.isEmpty(message) ? "Not able to connect to Server" : message, toolbar
                , "Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showLoadingDialog();
                        mainPresenter.loadPost();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void onDeliverySelect(Delivery delivery) {
        DeliveryDetailFragment deliveryFragment = (DeliveryDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_delivery_detail);

        if (deliveryFragment != null) {
            deliveryFragment.setDelivery(delivery);
        } else {
            DeliveryDetailFragment fragment = DeliveryDetailFragment.newInstance(delivery);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            fragmentTransaction.commit();
        }
    }

    @Override
    public void setTitle(String title) {
        if(TextUtils.isEmpty(title)){
            return;
        }
        toolbar.setTitle(title);
    }
}
