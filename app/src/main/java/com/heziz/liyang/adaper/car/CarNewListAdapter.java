package com.heziz.liyang.adaper.car;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.car.CarDetailsBean1;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class CarNewListAdapter extends BaseQuickAdapter<CarDetailsBean1,BaseViewHolder> {
    private Context context;
    public CarNewListAdapter(Context context, List<CarDetailsBean1> list) {
        super(R.layout.new_clcx_list_item,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CarDetailsBean1 item) {
        helper.setText(R.id.tvCp, item.getLicensePlate());
        String s=item.getLicensePlateColor();
        if("蓝".equals(s)){
            helper.getView(R.id.tvpzColor).setBackgroundColor(Color.BLUE);
            TextView t=(TextView)(helper.getView(R.id.tvpzColor));
            t.setTextColor(Color.WHITE);
        }else{
            helper.getView(R.id.tvpzColor).setBackgroundColor(Color.YELLOW);
            TextView t=(TextView)(helper.getView(R.id.tvpzColor));
            t.setTextColor(Color.BLACK);
        }
        helper.setText(R.id.tvpzColor, item.getLicensePlateColor()+"牌");
        //helper.setText(R.id.tvType,item.getDateTimeString());
        helper.setText(R.id.tvTime,item.getDateTimeString());
        Glide.with(mContext).load(item.getDistantview()).crossFade().into((ImageView) helper.getView(R.id.iv));

        //helper.getView(R.id.iv).setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        List<Rect> rects = new ArrayList<>();
        //        rects.add(JMatrixUtil.getDrawableBoundsInView(helper.getView(R.id.iv)));
        //        JBrowseImgActivity.start(mContext, new ArrayList<>(Arrays.asList(item.getLicensePlateImg())), 0, rects);
        //    }
        //});
    }
}
