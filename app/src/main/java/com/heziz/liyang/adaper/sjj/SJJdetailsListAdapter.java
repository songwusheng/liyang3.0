package com.heziz.liyang.adaper.sjj;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.sjj.MessageBean;
import com.heziz.liyang.bean.sjj.SJJRealTimeBean;
import com.heziz.liyang.bean.td.TdAlarmBean;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class SJJdetailsListAdapter extends BaseQuickAdapter<MessageBean,BaseViewHolder> {
    private Context context;
    public SJJdetailsListAdapter(Context context, List<MessageBean> list) {
        super(R.layout.sjj_details_list,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {
        helper.setText(R.id.tvzl1, item.getWeight()+"");
        helper.setText(R.id.tvxh, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvsd,item.getSpeed()+"");
        helper.setText(R.id.tvrs,item.getPeopleNum()+"");
        helper.setText(R.id.tvqx,item.getAngularity1()+"");
        helper.setText(R.id.tvgd,item.getHeight()+"");
        helper.setText(R.id.tvsj,item.getDataTime().substring(5,16)+"");
    }
}
