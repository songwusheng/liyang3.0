package com.heziz.liyang.adaper.car;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.mine.clcx.MineCLCXBean;
import com.heziz.liyang.utils.StringUtil;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class MineCLCXGJListAdapter extends BaseQuickAdapter<MineCLCXBean,BaseViewHolder> {
    private Context context;
    public MineCLCXGJListAdapter(Context context, List<MineCLCXBean> list) {
        super(R.layout.mine_yc_list_include1,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MineCLCXBean item) {
        helper.setText(R.id.tvProjectName, item.getProjectName());
        helper.setText(R.id.tvId, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvJd,item.getProjectArea());
        String s="";
        String type=item.getPType()+"";
        if(StringUtil.isNull(type)){
            s="-";
        }else if(type.equals("1")) {
            s = "房建";
        }else if (type.equals("2")) {
            s = "市政";
        }else if (type.equals("3")) {
            s = "交通";
        }else if (type.equals("4")) {
            s = "轨道";
        }else if (type.equals("5")) {
            s = "水务";
        }else if (type.equals("6")) {
            s = "园林";
        }else{
            s="其他";
        }
        helper.setText(R.id.tvLx,s);
        String diff="";
        if(item.getDiff().equals("1")){
            diff="信息化工地";
        }else if (item.getDiff().equals("2")){
            diff="智慧工地";
        }else if (item.getDiff().equals("0")){
            diff="未申报智慧工地";
        }else {
            diff="智慧信息";
        }
        helper.setText(R.id.tvSx,diff);
        helper.setText(R.id.tvNum,item.getAlarmNum()+"");
////                .setText(R.id.tweetText, item.getText())
////                .setText(R.id.tweetDate, item.getCreatedAt())
////                .setVisible(R.id.tweetRT, item.isRetweet())
////                .linkify(R.id.tweetText);
//        if (item.get)
//        Glide.with(mContext).load(R.mipmap.zhihui_fangjian_icon).crossFade().into((ImageView) helper.getView(R.id.ivType));
    }
}
