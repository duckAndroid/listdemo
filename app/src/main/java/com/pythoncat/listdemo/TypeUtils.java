package com.pythoncat.listdemo;

/**
 * Created by pythonCat on 2016/7/6.
 */
public class TypeUtils {

    private TypeUtils() {
        throw new RuntimeException(" forbidden");
    }

    public static String getType(int type) {
        String t;
        if (type == Room.male) {
            t = "男人";
        } else if (type == Room.female) {
            t = "女人";
        } else if (type == Room.child) {
            t = "小孩";
        } else {
            t = "男人";
        }
        return t;

    }
}
