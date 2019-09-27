package com.heziz.liyang.adaper.sjj;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

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

        String systemWeight=item.getSystemWeight();
        if("2".equals(systemWeight)){
            TextView tvzl=helper.getView(R.id.tvzl1);
            tvzl.setTextColor(Color.RED);
        }else{
            TextView tvzl=helper.getView(R.id.tvzl1);
            tvzl.setTextColor(context.getResources().getColor(R.color.bbb));
        }
        String systemHeightLimit=item.getSystemHeightLimit();
        if("2".equals(systemHeightLimit)){
            TextView tvzl=helper.getView(R.id.tvgd);
            tvzl.setTextColor(Color.RED);
        }else{
            TextView tvzl=helper.getView(R.id.tvgd);
            tvzl.setTextColor(context.getResources().getColor(R.color.bbb));
        }
        String systemSpeedLimit=item.getSystemSpeedLimit();
        if("2".equals(systemSpeedLimit)){
            TextView tvzl=helper.getView(R.id.tvsd);
            tvzl.setTextColor(Color.RED);
        }else{
            TextView tvzl=helper.getView(R.id.tvsd);
            tvzl.setTextColor(context.getResources().getColor(R.color.bbb));
        }
        String systemPeopleNum=item.getSystemPeopleNum();
        if("2".equals(systemPeopleNum)){
            TextView tvzl=helper.getView(R.id.tvrs);
            tvzl.setTextColor(Color.RED);
        }else{
            TextView tvzl=helper.getView(R.id.tvrs);
            tvzl.setTextColor(context.getResources().getColor(R.color.bbb));
        }
        String systemAngularity=item.getSystemAngularity();
        if("2".equals(systemAngularity)){
            TextView tvzl=helper.getView(R.id.tvqx);
            tvzl.setTextColor(Color.RED);
        }else{
            TextView tvzl=helper.getView(R.id.tvqx);
            tvzl.setTextColor(context.getResources().getColor(R.color.bbb));
        }


    }
}
