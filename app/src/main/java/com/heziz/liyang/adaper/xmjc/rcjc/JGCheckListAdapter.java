package com.heziz.liyang.adaper.xmjc.rcjc;

import android.app.Activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.rcjc.xmf.XMFCheckListBean;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class JGCheckListAdapter extends BaseQuickAdapter<XMFCheckListBean,BaseViewHolder> {
    public JGCheckListAdapter(Activity context, List<XMFCheckListBean> list) {
        super(R.layout.check_list_item,list);
    }

    @Override
    protected void convert(BaseViewHolder helper, XMFCheckListBean item) {

        helper.setText(R.id.tvName, item.getProjectName());
        int endStatus=item.getEndStatus();
        switch (endStatus){
            case 1:
                helper.setText(R.id.tvHg,"未检查");
                break;
            case 2:
                helper.setText(R.id.tvHg,"合格");
                break;
            case 3:
                helper.setText(R.id.tvHg,"不合格");
                break;
            case 4:
                helper.setText(R.id.tvHg,"待整改");
                break;
            case 5:
                helper.setText(R.id.tvHg,"整改待确认");
                break;
            case 6:
                helper.setText(R.id.tvHg,"整改合格");
                break;
            case 7:
                helper.setText(R.id.tvHg,"整改不合格");
                break;
        }

        helper.setText(R.id.tvCheckName,item.getPopedomName());
        helper.setText(R.id.tvTime,item.getStartTime());
        helper.addOnClickListener(R.id.tv_delete);
    }

}


