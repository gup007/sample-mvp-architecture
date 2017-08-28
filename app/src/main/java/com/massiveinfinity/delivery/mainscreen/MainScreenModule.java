package com.massiveinfinity.delivery.mainscreen;

import com.massiveinfinity.delivery.util.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SurvivoR on 7/31/2017.
 * {@link MainScreenModule}
 */
@Module
public class MainScreenModule {

    private final MainScreenContract.View mView;

    public MainScreenModule(MainScreenContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @CustomScope
    MainScreenContract.View providesMainScreenContractView() {
        return mView;
    }

}
