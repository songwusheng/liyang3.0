package com.heziz.liyang.adaper.xmjc.zxjc;

import android.app.Activity;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.rcjc.jgf.ZXCheckListBean;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class ZXCheckListAdapter extends BaseItemDraggableAdapter<ZXCheckListBean,BaseViewHolder> {
    public ZXCheckListAdapter(Activity context, List<ZXCheckListBean> list) {
        super(R.layout.zx_check_list_item,list);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZXCheckListBean item) {

        helper.setText(R.id.tvName, item.getProjectName());
        helper.setText(R.id.tvType, item.getEventName());
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
                helper.setText(R.id.tvHg,"通知整改");
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
        //1未上传图片，2检查结束，状态正常，3不正常，4不正常，已通知项目方整改，5项目方整改完毕，6整改合格 7整改不合格
        helper.setText(R.id.tvCheckName,item.getCheckName());
        helper.setText(R.id.tvTime,item.getStartTime());
        helper.addOnClickListener(R.id.tv_delete);

    }

}


