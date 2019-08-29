package com.heziz.liyang.adaper.home;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.ProjectBean;
import com.heziz.liyang.utils.StringUtil;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class HomeTotalListAdapter extends BaseQuickAdapter<ProjectBean,BaseViewHolder> {
    private Context context;
    private String name;
    public HomeTotalListAdapter(Context context, List<ProjectBean> list,String name) {
        super(R.layout.project_lsit_item1,list);
        this.context=context;
        this.name=name;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectBean item) {
        helper.setText(R.id.tvName, item.getName());
        helper.setText(R.id.tvNum, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvStreet,name);

        String cost=item.getCost();
        if(cost!=null){
            cost=cost.trim();
        }
        if(StringUtil.isNull(cost)||StringUtil.isEmpty(cost)){
            helper.setText(R.id.tvtype,"0");
        }else{
            helper.setText(R.id.tvtype,Double.valueOf(cost)/10000+"");
        }

    }
}
