package com.massiveinfinity.delivery.mainscreen.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.massiveinfinity.delivery.DeliveryApp;
import com.massiveinfinity.delivery.R;
import com.massiveinfinity.delivery.data.Delivery;
import com.massiveinfinity.delivery.database.DBHelper;
import com.massiveinfinity.delivery.mainscreen.DeliveryAdapter;
import com.massiveinfinity.delivery.mainscreen.DeliverySelectListener;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by SurvivoR on 25-08-2017.
 * {@link DeliveryListFragment}
 */

public class DeliveryListFragment extends Fragment implements View.OnClickListener {

    private DeliveryAdapter mDeliveryAdapter;
    protected RecyclerView mRecyclerView;

    @Inject
    DBHelper mDBHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerDeliveryListComponent.builder()
                .netComponent(((DeliveryApp) getActivity().getApplication()).getNetComponent())
                .build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.fragment_delivery_list, container, false);
        return inflate.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((DeliverySelectListener)getActivity()).setTitle("Things to Deliver");
        mRecyclerView = (RecyclerView) view.findViewById(R.id.activity_contact_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        showAdapter(mDBHelper.getAllDelivery());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void showAdapter(List<Delivery> allDelivery) {
        if (mDeliveryAdapter == null) {
            mDeliveryAdapter = new DeliveryAdapter(allDelivery, this);
        } else {
            mDeliveryAdapter.setData(allDelivery);
        }
        mRecyclerView.setAdapter(mDeliveryAdapter);
    }

    @Override
    public void onClick(View v) {
        if (mRecyclerView == null || mDeliveryAdapter == null) {
            return;
        }
        int position = mRecyclerView.getChildAdapterPosition(v);
        Delivery delivery = mDeliveryAdapter.getItemAt(position);
        if (getActivity() == null || delivery == null) {
            return;
        }
        ((DeliverySelectListener) getActivity()).onDeliverySelect(delivery);
    }
}
