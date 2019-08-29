package com.heziz.liyang.adaper.wdbygs;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bigimage.JBrowseImgActivity;
import com.heziz.liyang.bigimage.util.JMatrixUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:五达标一公示--已查项目列表适配器
 */

public class WDBYGSImageListAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private Context context;
    public WDBYGSImageListAdapter(Context context, List<String> list) {
        super(R.layout.wdbygs_details_include_list_image_list,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(mContext).load(item).crossFade().into((ImageView) helper.getView(R.id.iv));

        helper.getView(R.id.iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Rect> rects = new ArrayList<>();
                rects.add(JMatrixUtil.getDrawableBoundsInView(helper.getView(R.id.iv)));
                JBrowseImgActivity.start(mContext, new ArrayList<>(Arrays.asList(item)), 0, rects);
            }
        });
    }
}
