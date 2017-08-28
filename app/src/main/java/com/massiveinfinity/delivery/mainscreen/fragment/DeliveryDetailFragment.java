package com.massiveinfinity.delivery.mainscreen.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.massiveinfinity.delivery.BR;
import com.massiveinfinity.delivery.R;
import com.massiveinfinity.delivery.data.Delivery;
import com.massiveinfinity.delivery.mainscreen.DeliverySelectListener;

import static com.massiveinfinity.delivery.R.id.map;

/**
 * Created by SurvivoR on 25-08-2017.
 * {@link DeliveryDetailFragment}
 */

public class DeliveryDetailFragment extends Fragment implements OnMapReadyCallback {

    private static final String KEY_DELIVERY_DETAIL = "delivery_detail";
    private Delivery mDelivery;
    private MapView mapView;
    private GoogleMap mMap;

    public static DeliveryDetailFragment newInstance(Delivery delivery) {
        DeliveryDetailFragment fragment = new DeliveryDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DELIVERY_DETAIL, delivery);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setDelivery(Delivery delivery) {
        this.mDelivery = delivery;
        bindData();
        bindMapData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_delivery_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((DeliverySelectListener)getActivity()).setTitle("Delivery Details");
        mDelivery = getArguments().getParcelable(KEY_DELIVERY_DETAIL);
        bindData();
        mapView = (MapView) view.findViewById(map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    private void bindData() {
        if (getView() == null) {
            return;
        }
        ViewDataBinding dataBinding = DataBindingUtil.bind(getView().findViewById(R.id.map_detail));
        dataBinding.setVariable(BR.delivery, mDelivery);
        dataBinding.executePendingBindings();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        bindMapData();
    }

    private void bindMapData() {
        LatLng address = new LatLng(mDelivery.getLocation().getLatitude(), mDelivery.getLocation().getLongitude());
        mMap.addMarker(new MarkerOptions().position(address).title(mDelivery.getLocation().getAddress()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(address, 15));
    }
}
