package com.heziz.liyang.adaper.aqjc;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.mine.wdbygs.MineWDBYGSListBean;
import com.heziz.liyang.bean.rcjc.AqjcListBean;
import com.heziz.liyang.utils.StringUtil;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:安全检查--已查项目列表适配器
 */

public class AQYcListAdapter extends BaseQuickAdapter<AqjcListBean,BaseViewHolder> {
    private Context context;
    public AQYcListAdapter(Context context, List<AqjcListBean> list) {
        super(R.layout.wdb_list_include,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, AqjcListBean item) {
        helper.setText(R.id.tvName, item.getName());
        helper.setText(R.id.tvId, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvFzr, StringUtil.isNullOrEmpty(item.getLeader()));
        helper.setText(R.id.tvXq,item.getManagerName());

        String s="";
        switch (item.getState()){
            case 1:
                s="检查合格";
                break;
            case 2:
                s="检查不合格，已通知整改";
                break;
            case 3:
                s="整改完成";
                break;

        }
        helper.setText(R.id.tvZt,s);
    }
}
