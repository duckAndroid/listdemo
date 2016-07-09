package com.pythoncat.listdemo.service;

import com.apkfuns.logutils.LogUtils;
import com.pythoncat.listdemo.Bean;
import com.pythoncat.listdemo.Room;
import com.pythoncat.listdemo.TypeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Observable;

/**
 * Created by pythonCat on 2016/7/6.
 * 获取服务器数据！！！
 */
public class LoadService {

    /**
     * 初次获取数据
     *
     * @return data
     */
    public static List<Bean> load() {
        int len = new Random().nextInt(14) + 6;
        List<Bean> all = all();
        int start = new Random().nextInt(all.size() / 2 + 1);
        LogUtils.e("start = " + start + ", len=" + len);
        List<Bean> data = new ArrayList<>();
        for (int x = 0; x < all.size(); x++) {
            if (x >= start && x < start + len) {
                data.add(all.get(x));
            }
        }
        LogUtils.e("load...");
        LogUtils.e(data.size());
        return data;
    }

    /**
     * 下拉刷新的数据
     *
     * @return data
     */
    public static List<Bean> refresh() {
        LogUtils.e("refresh...");
        return load();
    }

    /**
     * 加载更多的数据 （也许会包含之前的数据）
     *
     * @return data
     */
    public static List<Bean> loadMore() {
        int len = new Random().nextInt(10) + 10;
        List<Bean> all = all();
        int start = new Random().nextInt(all.size() / 2);
        List<Bean> data = new ArrayList<>();
        for (int x = 0; x < all.size(); x++) {
            if (x >= start && x < start + len) {
                data.add(all.get(x));
            }
        }
        LogUtils.e("loadMore...");
        LogUtils.e(data.size());
        return data;
    }

    /**
     * 全部的数据
     *
     * @return data
     */
    private static List<Bean> all() {

//        SystemClock.sleep(4000);
        ArrayList<Bean> list = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            Bean obj = new Bean();
            obj.roomId = i + "";
            if (i % 2 == 0) {
                obj.roomName = "-菊花 " + i;
                obj.roomType = TypeUtils.getType(Room.male);
            } else {
                obj.roomName = "Flower " + i;
                obj.roomType = TypeUtils.getType(Room.female);
            }
            if (i % 100 == 0) {
                obj.roomName = "Pig Head Three " + i + " @$@$";
                obj.roomType = TypeUtils.getType(Room.child);
            }
            list.add(obj);
        }
        LogUtils.w("all...");
//        LogUtils.w(list);
        return list;
    }

    //#####################  分类暂时不实现分页 ###################################
    private static List<Bean> allMale() {
        return Observable.from(all())
                .filter(obj -> obj.roomType.equals(TypeUtils.getType(Room.male)))
                .toList()
                .toBlocking()
                .single();
    }


    private static List<Bean> allFemale() {
        return Observable.from(all())
                .filter(obj -> obj.roomType.equals(TypeUtils.getType(Room.female)))
                .toList()
                .toBlocking()
                .single();
    }

    private static List<Bean> allChild() {
        return Observable.from(all())
                .filter(obj -> obj.roomType.equals(TypeUtils.getType(Room.child)))
                .toList()
                .toBlocking()
                .single();
    }


}
