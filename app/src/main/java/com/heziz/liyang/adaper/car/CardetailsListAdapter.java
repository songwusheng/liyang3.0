package com.heziz.liyang.adaper.car;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.car.CarDetailsBean;
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

public class CardetailsListAdapter extends BaseQuickAdapter<CarDetailsBean,BaseViewHolder> {
    private Context context;
    public CardetailsListAdapter(Context context, List<CarDetailsBean> list) {
        super(R.layout.car_details_list,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CarDetailsBean item) {
        helper.setText(R.id.tvCp, item.getLicensePlate());
        helper.setText(R.id.tvNumber, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvTime,item.getDateTimeString());
        Glide.with(mContext).load(item.getLicensePlateImg()).crossFade().into((ImageView) helper.getView(R.id.iv));

        helper.getView(R.id.iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Rect> rects = new ArrayList<>();
                rects.add(JMatrixUtil.getDrawableBoundsInView(helper.getView(R.id.iv)));
                JBrowseImgActivity.start(mContext, new ArrayList<>(Arrays.asList(item.getLicensePlateImg())), 0, rects);
            }
        });
    }
}
