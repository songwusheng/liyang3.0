package com.heziz.liyang.adaper.xmjc.rcjc;

import android.app.Activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.bean.rcjc.WgxmListBean;
import com.heziz.liyang.R;
import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class WGXMWCListAdapter extends BaseQuickAdapter<WgxmListBean,BaseViewHolder> {
    private int type;
    public WGXMWCListAdapter(Activity context, List<WgxmListBean> list,int type) {
        super(R.layout.check_list_item,list);
        this.type=type;
    }

    @Override
    protected void convert(BaseViewHolder helper, WgxmListBean item) {

        helper.setText(R.id.tvName, item.getName());
        helper.setText(R.id.tvHg,"未检查");
        //
        helper.setText(R.id.tvCheckName1,"所属辖区：");
        helper.setText(R.id.tvCheckName,item.getManagerName());
        //helper.setText(R.id.tvTime,item.getStartTime());
        //helper.addOnClickListener(R.id.tv_delete);

    }

}


