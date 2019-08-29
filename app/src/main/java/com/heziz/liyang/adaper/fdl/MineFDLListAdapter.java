package com.heziz.liyang.adaper.fdl;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.fdl.MineFDLBean;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:五达标一公示--已查项目列表适配器
 */

public class MineFDLListAdapter extends BaseQuickAdapter<MineFDLBean,BaseViewHolder> {
    private Context context;
    public MineFDLListAdapter(Context context, List<MineFDLBean> list) {
        super(R.layout.mine_fdl_list_include,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MineFDLBean item) {
        helper.setText(R.id.tvName, item.getProjectName());
        helper.setText(R.id.tvId, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvFzr,item.getByTime()+"");
//        helper.setText(R.id.tvXq,item.getEndTime()+"");
       String s="";
        switch (item.getAuditStatus()){
            case 0:
                s="待审核";
                break;
            case 1:
                s="通过";
                break;
            case 2:
                s="不通过";
                break;

        }
        helper.setText(R.id.tvZt,s);
//        helper.setText(R.id.tvzh,item.getBuild()+"");
//        helper.setText(R.id.tvcb,item.getDiff()+"");
////                .setText(R.id.tweetText, item.getText())
////                .setText(R.id.tweetDate, item.getCreatedAt())
////                .setVisible(R.id.tweetRT, item.isRetweet())
////                .linkify(R.id.tweetText);
//        if (item.get)
//        Glide.with(mContext).load(R.mipmap.zhihui_fangjian_icon).crossFade().into((ImageView) helper.getView(R.id.ivType));
    }
}
