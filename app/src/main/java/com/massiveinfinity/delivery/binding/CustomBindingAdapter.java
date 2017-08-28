package com.massiveinfinity.delivery.binding;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.massiveinfinity.delivery.R;

public class CustomBindingAdapter {

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(final ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).asBitmap().centerCrop()
                .placeholder(R.drawable.ic_profile_placeholder).
                into(imageView);
    }

    @BindingAdapter("bind:visibilityOnIndex")
    public static void toggleVisibility(View view, int index) {
        view.setVisibility(index > 0 ? View.VISIBLE : View.GONE);
    }
}