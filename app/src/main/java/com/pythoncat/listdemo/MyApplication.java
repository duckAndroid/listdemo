package com.pythoncat.listdemo;

import android.app.Application;

/**
 * Created by pythonCat on 2016/7/6.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectDiskReads() // 捕捉读取磁盘
//                .detectDiskWrites() // 捕捉写入磁盘
//                .detectNetwork()   // 捕捉网络访问 或使用detectAll() 火力全开
//                .penaltyLog() // 捕捉LogCat日志
//                .build());
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                .detectLeakedSqlLiteObjects()
//                .detectLeakedClosableObjects()
//                .penaltyLog()
//                .penaltyDeath()
//                .build());
    }
}
