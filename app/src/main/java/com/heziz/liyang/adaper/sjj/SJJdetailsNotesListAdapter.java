package com.heziz.liyang.adaper.sjj;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.sjj.MessageBean;
import com.heziz.liyang.bean.sjj.SjjNotesBean;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class SJJdetailsNotesListAdapter extends BaseQuickAdapter<SjjNotesBean,BaseViewHolder> {
    private Context context;
    public SJJdetailsNotesListAdapter(Context context, List<SjjNotesBean> list) {
        super(R.layout.sjj_details_notes_list,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SjjNotesBean item) {
        helper.setText(R.id.tvxh, helper.getLayoutPosition() + 1 + "");
        helper.setText(R.id.tvxm, item.getName());
        helper.setText(R.id.tvsfzh, item.getIdCard() + "");
        helper.setText(R.id.tvsj, item.getDataTime() + "");
    }
}
