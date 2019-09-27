package com.heziz.liyang.adaper.aqjc;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.rcjc.AqjcListBean;
import com.heziz.liyang.bean.wdbygs.WDBYGSBean;
import com.heziz.liyang.utils.StringUtil;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:五达标一公示--已查项目列表适配器
 */

public class AQJCListAdapter extends BaseQuickAdapter<AqjcListBean,BaseViewHolder> {
    private Context context;
    public AQJCListAdapter(Context context, List<AqjcListBean> list) {
        super(R.layout.wdb_list_include1,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, AqjcListBean item) {
        helper.setText(R.id.tvName, item.getName());
        helper.setText(R.id.tvId, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvFzr, StringUtil.isNullOrEmpty(item.getLeader()));
        helper.setText(R.id.tvXq,item.getManagerName());
//        helper.setText(R.id.tvZt,item.getXjzt()+"");
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
