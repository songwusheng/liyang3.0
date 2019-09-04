package com.heziz.liyang.adaper.smz;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.smz.SmzHMCBean;
import com.heziz.liyang.bigimage.JBrowseImgActivity;
import com.heziz.liyang.bigimage.util.JMatrixUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class SmzHmcListAdapter extends BaseQuickAdapter<SmzHMCBean, BaseViewHolder> {
    private Context context;

    public SmzHmcListAdapter(Context context, List<SmzHMCBean> list) {
        super(R.layout.include_smz_item2, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SmzHMCBean item) {
        //helper.setText(R.id.tv, item.getTime());
        helper.setText(R.id.tvId, helper.getLayoutPosition() + 1 + "");
        helper.setText(R.id.tvName, item.getName());
        helper.setText(R.id.tvBz, item.getTeam() + "");
        helper.setText(R.id.tvCard, item.getCardnum());

        Glide.with(mContext).load(item.getCardnumphotobase64()).crossFade().into((ImageView) helper.getView(R.id.ivPic));
        helper.getView(R.id.ivPic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Rect> rects = new ArrayList<>();
                ArrayList<String> arr = new ArrayList<>();
                arr.add(item.getCardnumphotobase64());
                rects.add(JMatrixUtil.getDrawableBoundsInView(helper.getView(R.id.ivPic)));
                JBrowseImgActivity.start(mContext, arr, 0, rects);
            }
        });
    }
}
