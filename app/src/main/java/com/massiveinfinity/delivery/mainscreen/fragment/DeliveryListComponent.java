package com.massiveinfinity.delivery.mainscreen.fragment;

import com.massiveinfinity.delivery.data.component.NetComponent;
import com.massiveinfinity.delivery.util.CustomScope;

import dagger.Component;

/**
 * Created by SurvivoR on 25-08-2017.
 * {@link DeliveryListComponent}
 */
@CustomScope
@Component(dependencies = {NetComponent.class}, modules = {})
public interface DeliveryListComponent {
    void inject(DeliveryListFragment fragment);
}
