package com.heziz.liyang.adaper.wdbygs;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.mine.wdbygs.MineWDBYGSListBean;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:五达标一公示--已查项目列表适配器
 */

public class YCListAdapter extends BaseQuickAdapter<MineWDBYGSListBean,BaseViewHolder> {
    private Context context;
    public YCListAdapter(Context context, List<MineWDBYGSListBean> list) {
        super(R.layout.wdb_list_include,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MineWDBYGSListBean item) {
        helper.setText(R.id.tvName, item.getProjectName());
        helper.setText(R.id.tvId, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvFzr,item.getCreateName());
        helper.setText(R.id.tvXq,item.getJdName());

        String s="";
        switch (item.getEndStatus()){
            case 1:
                s="未传图片";
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
