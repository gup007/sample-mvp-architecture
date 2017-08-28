package com.massiveinfinity.delivery.mainscreen;

import com.massiveinfinity.delivery.data.component.NetComponent;
import com.massiveinfinity.delivery.util.CustomScope;

import dagger.Component;

/**
 * Created by SurvivoR on 7/31/2017.
 * {@link MainScreenComponent}
 */
@CustomScope
@Component(dependencies = {NetComponent.class}, modules = {MainScreenModule.class})
public interface MainScreenComponent {
    void inject(MainActivity activity);
}
