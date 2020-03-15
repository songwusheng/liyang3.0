package com.heziz.liyang.adaper.sjj;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heziz.liyang.R;
import com.heziz.liyang.ui.zhihui.sjj.SjjDetailsActivity;
import com.heziz.liyang.bean.sjj.SjjProjectBean;
import com.heziz.liyang.ui.zhihui.sjj.SjjDetailsActivity1;

import java.util.List;

/**
 * Created by sws on 2019-05-15.
 * from:
 * describe:
 */

public class MySjjExtendableListViewAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<SjjProjectBean> list;
    public MySjjExtendableListViewAdapter(Context mContext, List<SjjProjectBean> list) {
        this.mContext=mContext;
        this.list=list;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getTowers().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getTowers().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.zh_sp_list_include1, null);
        } else {

        }
        TextView tvNum = (TextView) convertView.findViewById(R.id.tvNum);
        TextView tvJd = (TextView) convertView.findViewById(R.id.tvJd);
        TextView tvTotal = (TextView) convertView.findViewById(R.id.tvTotal);
        TextView tvOnLine = (TextView) convertView.findViewById(R.id.tvOnLine);
        TextView tvOffLine = (TextView) convertView.findViewById(R.id.tvOffLine);
//        TextView num = (TextView) convertView.findViewById(R.id.num);
        tvNum.setText(groupPosition+1+"");
        tvJd.setText(list.get(groupPosition).getName());
        tvTotal.setText(list.get(groupPosition).getCount()+"");
        tvOnLine.setText(list.get(groupPosition).getOnline()+"");
        tvOffLine.setText(list.get(groupPosition).getOffline()+"");
//        num.setText(list.get(groupPosition).getAvgValue());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.yc_device_item, null);
            LinearLayout llHeader=(LinearLayout) convertView.findViewById(R.id.llHeader);
            TextView tvId=(TextView) convertView.findViewById(R.id.tvId);
            TextView tvDeviceId=(TextView) convertView.findViewById(R.id.tvDeviceId);
            Button btn=(Button) convertView.findViewById(R.id.btn);
            if(childPosition==0){
                llHeader.setVisibility(View.VISIBLE);
            }
            tvId.setText(childPosition+1+"");
            tvDeviceId.setText(list.get(groupPosition).getTowers().get(childPosition).getDeviceNum());
            int status=list.get(groupPosition).getTowers().get(childPosition).getState();

            if(status==1){
                btn.setText("在线");
                btn.setEnabled(true);
                btn.setBackground(mContext.getResources().getDrawable(R.drawable.btn_yj_bg));
            }else if (status==0){
                btn.setText("离线");
                btn.setEnabled(false);
                btn.setBackground(mContext.getResources().getDrawable(R.drawable.btn_yj_bg1));
            }
            //else{
            //    btn.setText("未知");
            //    btn.setEnabled(true);
            //    btn.setBackground(mContext.getResources().getDrawable(R.drawable.btn_yj_bg));
            //}

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1=new Intent(mContext,SjjDetailsActivity1.class);
                    intent1.putExtra("id",Long.valueOf(list.get(groupPosition).getId()));
                    intent1.putExtra("name",list.get(groupPosition).getName());
                    intent1.putExtra("deviceid",list.get(groupPosition).getTowers().get(childPosition).getDeviceNum());
                    mContext.startActivity(intent1);
                }
            });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
