package com.pythoncat.listdemo.service;

import com.pythoncat.listdemo.Bean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pythonCat on 2016/7/9.
 */
public class LoadApi {


    public static Observable<List<Bean>> loadMoreApi() {
        return Observable.timer(3, TimeUnit.SECONDS)
                .flatMap(time -> Observable.just(LoadService.loadMore()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<List<Bean>> refreshApi() {
        return Observable.timer(3, TimeUnit.SECONDS)
                .flatMap(time -> Observable.just(LoadService.refresh()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
