package com.heziz.liyang.adaper.video;

import android.content.Context;
import android.widget.ImageView;

import com.android.business.entity.ChannelInfo;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class ChannelInfoAdapter extends BaseQuickAdapter<ChannelInfo,BaseViewHolder> {
    private Context context;
    public ChannelInfoAdapter(Context context,List<ChannelInfo> list) {
        super(R.layout.shipin_channel_list,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ChannelInfo item) {
        helper.setText(R.id.tvChannelName, item.getName());
        if(item.getState()==ChannelInfo.ChannelState.Online){
            Glide.with(mContext).load(R.drawable.sp_online).crossFade().into((ImageView) helper.getView(R.id.ivChannel));
        }else{
            Glide.with(mContext).load(R.drawable.sp_offline).crossFade().into((ImageView) helper.getView(R.id.ivChannel));
        }
////        helper.setText(R.id.tvNum, String.valueOf(n));
////                .setText(R.id.tweetText, item.getText())
////                .setText(R.id.tweetDate, item.getCreatedAt())
////                .setVisible(R.id.tweetRT, item.isRetweet())
////                .linkify(R.id.tweetText);
//        if (item.get)
//        Glide.with(mContext).load(R.mipmap.zhihui_fangjian_icon).crossFade().into((ImageView) helper.getView(R.id.ivType));
    }
}
