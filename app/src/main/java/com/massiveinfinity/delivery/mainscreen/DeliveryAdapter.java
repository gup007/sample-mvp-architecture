package com.massiveinfinity.delivery.mainscreen;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.massiveinfinity.delivery.BR;
import com.massiveinfinity.delivery.R;
import com.massiveinfinity.delivery.data.Delivery;

import java.util.List;

/**
 * Created by SurvivoR on 7/31/2017.
 * {@link DeliveryAdapter}
 */

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.BindingHolder> {

    private List<Delivery> deliveries;
    private View.OnClickListener mListener;

    public DeliveryAdapter(List<Delivery> deliveries, View.OnClickListener listener) {
        this.deliveries = deliveries;
        this.mListener = listener;
    }

    public void setData(List<Delivery> deliveries) {
        this.deliveries = deliveries;
        notifyDataSetChanged();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delivery_list, parent, false);
        v.setOnClickListener(mListener);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final Delivery delivery = deliveries.get(position);
        holder.getBinding().setVariable(BR.delivery, delivery);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return deliveries.size();
    }

    @Nullable
    public Delivery getItemAt(int position){
        if(position < 0 || position >= deliveries.size()){
            return null;
        }
        return deliveries.get(position);
    }

    static class BindingHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        ViewDataBinding getBinding() {
            return binding;
        }
    }
}