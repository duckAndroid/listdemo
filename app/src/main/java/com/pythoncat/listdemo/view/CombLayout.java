package com.pythoncat.listdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pythoncat.listdemo.R;
import com.pythoncat.listdemo.utils.DpUtils;

/**
 * Created by pythonCat on 2016/7/6.
 */
public class CombLayout extends LinearLayout {

    private View head01;
    private CheckBox cbHead01;
    private View head02;
    private CheckBox cbHead02;
    private View head03;
    private CheckBox cbHead03;
    private View slight01;
    private View slight02;
    private View slight03;

    private boolean check01, check02, check03;

    private static final int addedNone = 0;
    private static final int added01 = 1;
    private static final int added02 = 2;
    private static final int added03 = 3;
    private int currentAdded = 0;

    public CombLayout(Context context) {
        super(context);
        init(context);
    }


    public CombLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CombLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater from = LayoutInflater.from(context);
        head01 = from.inflate(R.layout.item_head, null);
        cbHead01 =
                (CheckBox) head01.findViewById(R.id.head_cb);
        TextView tvHead01 = (TextView) head01.findViewById(R.id.head_tv);
        tvHead01.setText("服务器介绍");
        head02 = from.inflate(R.layout.item_head, null);
        cbHead02 = (CheckBox) head02.findViewById(R.id.head_cb);
        TextView tvHead02 = (TextView) head02.findViewById(R.id.head_tv);
        tvHead02.setText("注册/登录教程");
        head03 = from.inflate(R.layout.item_head, null);
        cbHead03 = (CheckBox) head03.findViewById(R.id.head_cb);
        TextView tvHead03 = (TextView) head03.findViewById(R.id.head_tv);
        tvHead03.setText("更多服务");
        slight01 = from.inflate(R.layout.item_slight1, this, false);
        slight02 = from.inflate(R.layout.item_slight2, this, false);
        slight03 = from.inflate(R.layout.item_slight3, this, false);
        LinearLayout.LayoutParams headP = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                DpUtils.dp2Px(context, 60));
        headP.gravity = Gravity.CENTER;
        LinearLayout.LayoutParams slightP = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1);
        slightP.gravity = Gravity.CENTER;
        head01.setOnClickListener(v -> {
            boolean check01 = handlerSelf01(cbHead01);
            /**
             * -----------------------------------------------
             *   01 当前是选中，那么现在就是取消。--》 那么，就关闭自己，结束
             *   02 当前是非选中，那么现在就是选中 --》那么，移除其他人，添加自己
             */

            if (check01) {
                // todo 02
                showSlight01(headP, slightP);
            } else {
                // todo  01
                showSlightNone(headP);

            }
        });
        head02.setOnClickListener(v -> {
            boolean check = handlerSelf02(cbHead02);
            if (check) {
                showSlight02(headP, slightP);
            } else {
                showSlightNone(headP);

            }
        });
        head03.setOnClickListener(v -> {
            boolean check = handlerSelf03(cbHead03);
            if (check) {
                showSlight03(headP, slightP);
            } else {
                showSlightNone(headP);
            }
        });
        initData();
        showSlight01(headP, slightP);
    }


    private void initData() {
        check01 = true;
    }

    private boolean handlerSelf01(CheckBox cb) {
        check01 = !check01;
        check02 = false;
        check03 = false;
        cb.setChecked(check01);
        return check01;
    }

    private boolean handlerSelf02(CheckBox cb) {
        check02 = !check02;
        check01 = false;
        check03 = false;
        cb.setChecked(check02);
        return check02;
    }

    private boolean handlerSelf03(CheckBox cb) {
        check03 = !check03;
        check01 = false;
        check02 = false;
        cb.setChecked(check03);
        return check03;
    }

    private void showSlight01(LayoutParams headP, LayoutParams slightP) {
        this.removeAllViews();
        this.addView(head01, headP);
        this.addView(slight01, slightP);
        this.addView(head02, headP);
        this.addView(head03, headP);
        cbHead01.setChecked(check01);
        cbHead02.setChecked(check02);
        cbHead03.setChecked(check03);
        currentAdded = added01;
    }

    private void showSlight02(LayoutParams headP, LayoutParams slightP) {
        this.removeAllViews();
        this.addView(head01, headP);
        this.addView(head02, headP);
        this.addView(slight02, slightP);
        this.addView(head03, headP);
        cbHead01.setChecked(check01);
        cbHead02.setChecked(check02);
        cbHead03.setChecked(check03);
        currentAdded = added02;
    }

    private void showSlight03(LayoutParams headP, LayoutParams slightP) {
        this.removeAllViews();
        this.addView(head01, headP);
        this.addView(head02, headP);
        this.addView(head03, headP);
        this.addView(slight03, slightP);
        cbHead01.setChecked(check01);
        cbHead02.setChecked(check02);
        cbHead03.setChecked(check03);
        currentAdded = added03;
    }

    private void showSlightNone(LayoutParams headP) {
        this.removeAllViews();
        this.addView(head01, headP);
        this.addView(head02, headP);
        this.addView(head03, headP);
        currentAdded = addedNone;
    }
}
