package com.heziz.liyang.adaper.td;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.td.TdAlarmBean;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class TDdetailsListAdapter extends BaseQuickAdapter<TdAlarmBean,BaseViewHolder> {
    private Context context;
    public TDdetailsListAdapter(Context context, List<TdAlarmBean> list) {
        super(R.layout.td_details_list,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TdAlarmBean item) {
        helper.setText(R.id.tvZ, item.getAlarm()+"");
        helper.setText(R.id.tvNum, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvTime,item.getAlarmTime());
        int type=item.getAlarmType();
        String s="";
        switch (type){
            case 1:
                s="风速";
                break;
            case 2:
                s="角度";
                break;
            case 3:
                s="力矩";
                break;
            case 4:
                s="幅度";
                break;
            case 5:
                s="吊重";
                break;
            case 6:
                s="高度";
                break;
        }
        helper.setText(R.id.tvType,s);
//        helper.setText(R.id.tvzh,item.getBuild()+"");
//        helper.setText(R.id.tvcb,item.getDiff()+"");
////                .setText(R.id.tweetText, item.getText())
////                .setText(R.id.tweetDate, item.getCreatedAt())
////                .setVisible(R.id.tweetRT, item.isRetweet())
////                .linkify(R.id.tweetText);
//        if (item.get)
//        Glide.with(mContext).load(R.mipmap.zhihui_fangjian_icon).crossFade().into((ImageView) helper.getView(R.id.ivType));
    }
}
