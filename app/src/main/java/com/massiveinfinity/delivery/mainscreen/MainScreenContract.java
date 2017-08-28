package com.massiveinfinity.delivery.mainscreen;

import com.massiveinfinity.delivery.data.Delivery;

import java.util.List;

/**
 * Created by SurvivoR on 7/31/2017.
 * {@link MainScreenContract}
 */

public interface MainScreenContract {

    interface View {
        void showPosts();

        void showError(String message);

        void showComplete();
    }

    interface Presenter {
        void loadPost();
    }
}
