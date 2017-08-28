package com.massiveinfinity.delivery;

import android.app.Application;

import com.massiveinfinity.delivery.data.component.DaggerNetComponent;
import com.massiveinfinity.delivery.data.component.NetComponent;
import com.massiveinfinity.delivery.data.module.AppModule;
import com.massiveinfinity.delivery.data.module.NetModule;

/**
 * Created by SurvivoR on 25-08-2017.
 * {@link DeliveryApp}
 */

public class DeliveryApp extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(getString(R.string.base_url)))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

}
