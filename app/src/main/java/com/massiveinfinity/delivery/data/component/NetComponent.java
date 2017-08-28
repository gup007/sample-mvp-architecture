package com.massiveinfinity.delivery.data.component;

import com.massiveinfinity.delivery.data.module.AppModule;
import com.massiveinfinity.delivery.data.module.NetModule;
import com.massiveinfinity.delivery.database.DBHelper;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by SurvivoR on 8/25/2017.
 * {@link NetComponent}
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    Retrofit retrofit();

    DBHelper dbHelper();
}
