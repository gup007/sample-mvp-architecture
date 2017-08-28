package com.massiveinfinity.delivery.mainscreen;

import com.massiveinfinity.delivery.data.Delivery;

/**
 * Created by SurvivoR on 25-08-2017.
 * {@link DeliverySelectListener}
 */

public interface DeliverySelectListener {

    void onDeliverySelect(Delivery delivery);

    void setTitle(String title);

}
