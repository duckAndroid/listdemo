package com.pythoncat.listdemo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pythoncat.listdemo.Bean;
import com.pythoncat.listdemo.R;

import java.util.List;

/**
 * Created by pythonCat on 2016/7/6.
 */
public class RvAdapter extends BaseQuickAdapter<Bean> {
    public RvAdapter(List<Bean> data) {
        super(R.layout.item_layout, data);
        if (data == null) throw new RuntimeException("data is null !");
    }

    @Override
    protected void convert(BaseViewHolder helper, Bean bean) {
        helper.setText(R.id.item_roomId, bean.roomId)
                .setText(R.id.item_roomName, bean.roomName)
                .setText(R.id.item_roomType, bean.roomType);
    }
}
