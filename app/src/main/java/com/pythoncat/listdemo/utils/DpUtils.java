package com.pythoncat.listdemo.utils;

import android.content.Context;

/**
 * Created by pythonCat on 2016/7/6.
 */
public class DpUtils {

    public static int dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
