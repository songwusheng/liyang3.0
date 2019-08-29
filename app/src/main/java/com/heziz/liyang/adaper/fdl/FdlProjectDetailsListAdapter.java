package com.heziz.liyang.adaper.fdl;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.fdl.FdlProjectDetailsBean;
import com.heziz.liyang.bigimage.JBrowseImgActivity;
import com.heziz.liyang.bigimage.util.JMatrixUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class FdlProjectDetailsListAdapter extends BaseQuickAdapter<FdlProjectDetailsBean,BaseViewHolder> {
    private Context context;
    public FdlProjectDetailsListAdapter(Context context, List<FdlProjectDetailsBean> list) {
        super(R.layout.fdl_details_list_item,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, FdlProjectDetailsBean item) {
        helper.setText(R.id.tvId, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvGyl,item.getAmount()+"");
        helper.setText(R.id.tvDj,item.getGrade()+"");
        helper.setText(R.id.tvTime,item.getByTime()+"");
//        helper.setText(R.id.tvFp,item.getInvoicePath()+"");

////                .setText(R.id.tweetText, item.getText())
////                .setText(R.id.tweetDate, item.getCreatedAt())
////                .setVisible(R.id.tweetRT, item.isRetweet())
////                .linkify(R.id.tweetText);
//        if (item.get)
        Glide.with(mContext).load(item.getInvoicePath()).crossFade().into((ImageView) helper.getView(R.id.tvFp));
        helper.getView(R.id.tvFp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Rect> rects = new ArrayList<>();
                rects.add(JMatrixUtil.getDrawableBoundsInView(helper.getView(R.id.iv)));
                JBrowseImgActivity.start(mContext, new ArrayList<>(Arrays.asList(item.getInvoicePath())), 0, rects);
            }
        });
    }
}
