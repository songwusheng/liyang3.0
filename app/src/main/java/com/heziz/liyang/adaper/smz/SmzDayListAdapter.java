package com.heziz.liyang.adaper.smz;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.smz.SmzDayBean;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class SmzDayListAdapter extends BaseQuickAdapter<SmzDayBean,BaseViewHolder> {
    private Context context;
    public SmzDayListAdapter(Context context, List<SmzDayBean> list) {
        super(R.layout.include_smz_item1,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SmzDayBean item) {
        helper.setText(R.id.tvTime, item.getTime());
        helper.setText(R.id.tvNum, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvName,item.getName());
        helper.setText(R.id.tvNo,item.getNo());
        helper.setText(R.id.tvBz,item.getBz());

    }
}
