package com.heziz.liyang.adaper.fdl;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.fdl.FdlStreetListBean;
import com.heziz.liyang.utils.NumberUtils;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class FdlStreetListAdapter extends BaseQuickAdapter<FdlStreetListBean,BaseViewHolder> {
    private Context context;
    public FdlStreetListAdapter(Context context, List<FdlStreetListBean> list) {
        super(R.layout.zh_list_fdl_item,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, FdlStreetListBean item) {
        helper.setText(R.id.tvJd, item.getName());
        helper.setText(R.id.tvId, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvPc,item.getTotalRecord()+"");
        helper.setText(R.id.tvYl,item.getTotalAmount()+"");
        if(item.getTotalRecord()!=0){
            helper.setText(R.id.tvDbl, NumberUtils.getTwoDecimal(item.getQualifiedNumber()*100.0/item.getTotalRecord())+"%");
        }else{
            helper.setText(R.id.tvDbl,"0");
        }

////                .setText(R.id.tweetText, item.getText())
////                .setText(R.id.tweetDate, item.getCreatedAt())
////                .setVisible(R.id.tweetRT, item.isRetweet())
////                .linkify(R.id.tweetText);
//        if (item.get)
//        Glide.with(mContext).load(R.mipmap.zhihui_fangjian_icon).crossFade().into((ImageView) helper.getView(R.id.ivType));
    }
}
