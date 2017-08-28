package com.massiveinfinity.delivery;

import com.massiveinfinity.delivery.data.Delivery;
import com.massiveinfinity.delivery.mainscreen.MainScreenPresenter;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by SurvivoR on 25-08-2017.
 * {@link DeliveryApiTest}
 */

public class DeliveryApiTest extends BaseApiTest {

    @Test
    public void testDeliveryApi() throws InterruptedException {
        runApiService();
    }

    @Override
    public void runApiService() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        mRetrofit.create(MainScreenPresenter.DeliveryService.class).getDeliveries().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Delivery>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Assert.fail(e.getMessage());
                        latch.countDown();
                    }

                    @Override
                    public void onNext(List<Delivery> contacts) {
                        latch.countDown();
                    }
                });
        latch.await();
    }
}
