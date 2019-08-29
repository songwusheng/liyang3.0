package com.heziz.liyang.adaper.car;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.mine.clcx.MineCLCXBean;
import com.heziz.liyang.bean.mine.clcx.MineCLCXListBean;
import com.heziz.liyang.utils.StringUtil;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class MineCLCXProjectDetailsListAdapter extends BaseQuickAdapter<MineCLCXListBean,BaseViewHolder> {
    private Context context;
    private MineCLCXBean mineYcListBean;
    public MineCLCXProjectDetailsListAdapter(Context context, List<MineCLCXListBean> list, MineCLCXBean mineYcListBean) {
        super(R.layout.mine_clcx_detals_item,list);
        this.context=context;
        this.mineYcListBean=mineYcListBean;
    }

    @Override
    protected void convert(BaseViewHolder helper, MineCLCXListBean item) {
        helper.setText(R.id.tv, "车牌号：");
        helper.setText(R.id.tvValue, item.getLicensePlate()+"");
        helper.setText(R.id.tvTime, item.getDateTime().replace("T"," ")+"");
        helper.setText(R.id.tvFzr, StringUtil.isNullOrEmpty(mineYcListBean.getProjectLeader()));
        helper.setText(R.id.tvDh,StringUtil.isNullOrEmpty(mineYcListBean.getProjectPhone()));
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
