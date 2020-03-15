package com.heziz.liyang.adaper.xmjc.rcjc;

import android.app.Activity;
import android.view.View;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.rcjc.NewCheckBean;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class NewCheckAdapter extends BaseItemDraggableAdapter<NewCheckBean,BaseViewHolder> {
    public NewCheckAdapter(Activity context, List<NewCheckBean> list) {
        super(R.layout.new_check_item,list);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewCheckBean item) {

        helper.setText(R.id.tvName, item.getName());
        if(item.getStatus().equals("1")){
            helper.getView(R.id.llunCheck).setVisibility(View.VISIBLE);
            helper.getView(R.id.llCheck).setVisibility(View.GONE);
        }else{
            helper.getView(R.id.llunCheck).setVisibility(View.GONE);
            helper.getView(R.id.llCheck).setVisibility(View.VISIBLE);
            helper.setText(R.id.tvXJJG,item.getXjjg());
            helper.setText(R.id.tvPicnum,"已上传"+item.getPicNum()+"张照片");
        }
        helper.addOnClickListener(R.id.llunCheck,R.id.llCheck);

    }

}


