package com.heziz.liyang.adaper.yc;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.yc.YcProjectDBean;
import com.heziz.liyang.bean.yc.YcProjectDeviceBean;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class YcDeviceListAdapter extends BaseQuickAdapter<YcProjectDBean<YcProjectDeviceBean>,BaseViewHolder> {
    private Context context;
    public YcDeviceListAdapter(Context context, List<YcProjectDBean<YcProjectDeviceBean>> list) {
        super(R.layout.yc_device_item,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, YcProjectDBean<YcProjectDeviceBean> item) {
        helper.setText(R.id.tvId, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvDeviceId,item.getWeacherDeviceId());

//        if(item.getMWeatherProjectDevice().)
////                .setText(R.id.tweetText, item.getText())
////                .setText(R.id.tweetDate, item.getCreatedAt())
////                .setVisible(R.id.tweetRT, item.isRetweet())
////                .linkify(R.id.tweetText);
//        if (item.get)
//        Glide.with(mContext).load(R.mipmap.zhihui_fangjian_icon).crossFade().into((ImageView) helper.getView(R.id.ivType));
    }
}
