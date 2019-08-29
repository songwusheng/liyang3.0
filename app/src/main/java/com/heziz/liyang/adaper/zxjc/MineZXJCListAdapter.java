package com.heziz.liyang.adaper.zxjc;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.mine.zxjc.MineZXJCBean;
import com.heziz.liyang.utils.StringUtil;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:五达标一公示--已查项目列表适配器
 */

public class MineZXJCListAdapter extends BaseQuickAdapter<MineZXJCBean,BaseViewHolder> {
    private Context context;
    public MineZXJCListAdapter(Context context, List<MineZXJCBean> list) {
        super(R.layout.wdb_list_include,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MineZXJCBean item) {
        helper.setText(R.id.tvName, item.getProjectName());
        helper.setText(R.id.tvId, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvFzr,item.getCustomEventName());
        helper.setText(R.id.tvXq, StringUtil.isNullOrEmpty(item.getStartTime()));
       String s="";
        switch (item.getEndStatus()){
            case 1:
                s="未检查";
                break;
            case 2:
                s="合格";
                break;
            case 3:
                s="不合格";
                break;
            case 4:
                s="通知整改";
                break;
            case 5:
                s="整改完毕";
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
