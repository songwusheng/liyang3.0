package com.heziz.liyang.adaper.yc;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.yc.YczlxNumBean;
import com.heziz.liyang.utils.NumberUtils;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class YcListAdapter extends BaseQuickAdapter<YczlxNumBean,BaseViewHolder> {
    private Context context;
    public YcListAdapter(Context context, List<YczlxNumBean> list) {
        super(R.layout.zh_list_bt_include1,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, YczlxNumBean item) {
        if(item.getStreet()!=null){
            helper.setText(R.id.tvJd, item.getStreet());
        }
        helper.setText(R.id.tvNum, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvTotal,item.getTotalCount()+"");
        helper.setText(R.id.tvOnLine,item.getOnlineCount()+"");
        helper.setText(R.id.tvOffLine,item.getOfflineCount()+"");
        helper.setText(R.id.num, NumberUtils.getTwoDecimal(Double.valueOf(item.getAvgValue()))+"");
////                .setText(R.id.tweetText, item.getText())
////                .setText(R.id.tweetDate, item.getCreatedAt())
////                .setVisible(R.id.tweetRT, item.isRetweet())
////                .linkify(R.id.tweetText);
//        if (item.get)
//        Glide.with(mContext).load(R.mipmap.zhihui_fangjian_icon).crossFade().into((ImageView) helper.getView(R.id.ivType));
    }
}
