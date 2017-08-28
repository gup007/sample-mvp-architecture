package com.massiveinfinity.delivery.mainscreen;

import com.massiveinfinity.delivery.data.Delivery;
import com.massiveinfinity.delivery.database.DBHelper;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by SurvivoR on 7/31/2017.
 * {@link MainScreenPresenter}
 */

public class MainScreenPresenter implements MainScreenContract.Presenter {

    public Retrofit mRetrofit;
    MainScreenContract.View mView;
    private DBHelper mDBHelper;

    @Inject
    public MainScreenPresenter(Retrofit retrofit, MainScreenContract.View mView, DBHelper dbHelper) {
        this.mRetrofit = retrofit;
        this.mView = mView;
        this.mDBHelper = dbHelper;
    }

    @Override
    public void loadPost() {
        if (mDBHelper.numberOfRows() > 0) {
            mView.showPosts();
            mView.showComplete();
            return;
        }
        mRetrofit.create(DeliveryService.class).getDeliveries().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Delivery>>() {
                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Delivery> contacts) {
                        storeInDB(contacts);
                    }
                });
    }

    private void storeInDB(List<Delivery> deliveries) {
        for (Delivery delivery : deliveries) {
            mDBHelper.insertDelivery(delivery);
        }
        mView.showPosts();
    }

    public interface DeliveryService {
        @GET("/deliveries")
        Observable<List<Delivery>> getDeliveries();
    }
}
