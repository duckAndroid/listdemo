package com.pythoncat.listdemo.utils;

import rx.Subscription;

/**
 * Created by pythonCat on 2016/7/6.
 */
public class RxJavaUtil {

    public static void close(Subscription s) {
        if (s == null) return;
        if (!s.isUnsubscribed())
            s.unsubscribe();
    }
}
