package com.heziz.liyang.adaper;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.HomeListBean;
import com.heziz.liyang.utils.NumberUtils;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class HomeListAdapter1 extends BaseQuickAdapter<HomeListBean,BaseViewHolder> {
    private Context context;
    private boolean flag;
    private int type;
    public HomeListAdapter1(Context context, List<HomeListBean> list, int type) {
        super(R.layout.home_list_item1,list);
        this.context=context;
        this.flag=flag;
        this.type=type;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeListBean item) {
        helper.setText(R.id.tvName, item.getPopedomName());
        helper.setText(R.id.tvNumber, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvtotal,item.getListSize()+"");
        switch (type){
            case 0:
                helper.setText(R.id.tvzh,item.getBuild()+"");
                break;
            case 1:
                helper.setText(R.id.tvzh, NumberUtils.getTwoDecimal(item.getCost()/10000.0)+"");
                break;
            case 2:
                helper.setText(R.id.tvzh,item.getBuild()+"");
                break;
            case 3:
                helper.setText(R.id.tvzh,item.getDiff()+"");
                break;
            case 4:
                helper.setText(R.id.tvzh,item.getSzg()+"");
                break;
            case 5:
                helper.setText(R.id.tvzh,item.getQzg()+"");
                break;
            case 6:
                helper.setText(R.id.tvzh,item.getFj()+"");
                break;
            case 7:
                helper.setText(R.id.tvzh,item.getSz()+"");
                break;
            case 8:
                helper.setText(R.id.tvzh,item.getYl()+"");
                break;
            case 9:
                helper.setText(R.id.tvzh,item.getJt()+"");
                break;
            case 10:
                helper.setText(R.id.tvzh,item.getSl()+"");
                break;
            case 11:
                helper.setText(R.id.tvzh,item.getDt()+"");
                break;
            case 12:
                helper.setText(R.id.tvzh,item.getQt()+"");
                break;
        }

//        if (flag){
//            helper.getView(R.id.tvcb).setVisibility(View.GONE);
//        }
//        helper.setText(R.id.tvcb,item.getDiff()+"");
////                .setText(R.id.tweetText, item.getText())
////                .setText(R.id.tweetDate, item.getCreatedAt())
////                .setVisible(R.id.tweetRT, item.isRetweet())
////                .linkify(R.id.tweetText);
//        if (item.get)
//        Glide.with(mContext).load(R.mipmap.zhihui_fangjian_icon).crossFade().into((ImageView) helper.getView(R.id.ivType));
    }
}
