package com.heziz.liyang.adaper.yc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.mine.yc.MineYcListBean;
import com.heziz.liyang.bean.yc.YcRealTimeBean;
import com.heziz.liyang.utils.StringUtil;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class MineYcProjectDetailsListAdapter extends BaseQuickAdapter<YcRealTimeBean,BaseViewHolder> {
    private Context context;
    private MineYcListBean mineYcListBean;
    public MineYcProjectDetailsListAdapter(Context context, List<YcRealTimeBean> list, MineYcListBean mineYcListBean) {
        super(R.layout.mine_yc_detals_item,list);
        this.context=context;
        this.mineYcListBean=mineYcListBean;
    }

    @Override
    protected void convert(BaseViewHolder helper, YcRealTimeBean item) {

        helper.setText(R.id.tvTime, item.getSysdate()+"");
        helper.setText(R.id.tvFzr,StringUtil.isNullOrEmpty(mineYcListBean.getProjectLeader()));
        helper.setText(R.id.tvDh,StringUtil.isNullOrEmpty(mineYcListBean.getProjectPhone()));

        double value1=item.getA34002Rtd();
        TextView tvValue=helper.getView(R.id.tvValue);
        if(value1>=100&&value1<150){
            tvValue.setTextColor(mContext.getResources().getColor(R.color.color1));
        }else if(value1>=150){
            tvValue.setTextColor(mContext.getResources().getColor(R.color.color3));
        }
        helper.setText(R.id.tvValue, item.getA34002Rtd()+"");
        helper.getView(R.id.ivDDH).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone();
            }
        });
        helper.getView(R.id.ivFDX).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSmsWithBody(mineYcListBean.getProjectPhone());
            }
        });

    }

    public void callPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + mineYcListBean.getProjectPhone());
        intent.setData(data);
        mContext.startActivity(intent);
    }
    public void sendSmsWithBody(String number) {
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("smsto:" + number));
//        sendIntent.putExtra("sms_body", body);
        mContext.startActivity(sendIntent);
    }
}
