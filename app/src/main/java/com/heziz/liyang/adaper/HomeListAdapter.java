package com.heziz.liyang.adaper;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.HomeListBean;
import com.heziz.liyang.bean.ProjectBean;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class HomeListAdapter extends BaseQuickAdapter<HomeListBean<List<ProjectBean>>,BaseViewHolder> {
    private Context context;
    private boolean flag;
    private int type;
    public HomeListAdapter(Context context, List<HomeListBean<List<ProjectBean>>> list) {
        super(R.layout.home_list_item,list);
        this.context=context;
        this.flag=flag;
        this.type=type;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeListBean<List<ProjectBean>> item) {
        helper.setText(R.id.tvName, item.getPopedomName());
        helper.setText(R.id.tvNumber, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvtotal,item.getListSize()+"");
        helper.setText(R.id.tvzh,item.getBuild()+"");
        helper.setText(R.id.tvcb,item.getDiff()+"");
////                .setText(R.id.tweetText, item.getText())
////                .setText(R.id.tweetDate, item.getCreatedAt())
////                .setVisible(R.id.tweetRT, item.isRetweet())
////                .linkify(R.id.tweetText);
//        if (item.get)
//        Glide.with(mContext).load(R.mipmap.zhihui_fangjian_icon).crossFade().into((ImageView) helper.getView(R.id.ivType));
    }
}
