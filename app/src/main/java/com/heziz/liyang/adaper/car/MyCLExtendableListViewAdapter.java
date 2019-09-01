package com.heziz.liyang.adaper.car;

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
import com.heziz.liyang.bean.car.CarProjectBean;
import com.heziz.liyang.bean.car.CarZHBean;
import com.heziz.liyang.ui.zhihui.clwcx.CLCXListActivity;
import com.heziz.liyang.utils.StringUtil;

import java.util.List;

/**
 * Created by sws on 2019-05-15.
 * from:
 * describe:
 */

public class MyCLExtendableListViewAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<CarProjectBean> list;
    private CarZHBean projectVideoBean;
//    private List<ProjectVideoBean<VideoProjectBean>> deviceList;
//    private YcDeviceListAdapter adapter;
    public MyCLExtendableListViewAdapter(Context mContext, List<CarProjectBean> list) {
        this.mContext=mContext;
        this.list=list;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getDavstring().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getDavstring().get(childPosition);
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
        tvTotal.setText(list.get(groupPosition).getTotalCount()+"");
        tvOnLine.setText(list.get(groupPosition).getOnlineCount()+"");
        tvOffLine.setText(list.get(groupPosition).getOfflineCount()+"");
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
            String deviceid=list.get(groupPosition).getDavstring().get(childPosition).getDavId();
        if (StringUtil.isNull(deviceid)) {

            tvDeviceId.setText("--");
        }else{
            tvDeviceId.setText(deviceid);
        }

            String status=list.get(groupPosition).getDavstring().get(childPosition).getOon();

            if("在线".equals(status)){
                btn.setText(status);
                btn.setEnabled(true);
                btn.setBackground(mContext.getResources().getDrawable(R.drawable.btn_yj_bg));
            }else if ("离线".equals(status)){
                btn.setText(status);
                btn.setEnabled(false);
                btn.setBackground(mContext.getResources().getDrawable(R.drawable.btn_yj_bg1));
            }else{
                btn.setText("未知");
                btn.setEnabled(false);
                btn.setBackground(mContext.getResources().getDrawable(R.drawable.btn_yj_bg));
            }

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent=new Intent(mContext,ClcxDeviceDetailsActivity.class);
                    Intent intent=new Intent(mContext,CLCXListActivity.class);
                    intent.putExtra("name",list.get(groupPosition).getName());
                    intent.putExtra("id",list.get(groupPosition).getDavstring().get(childPosition).getDavId());
                    mContext.startActivity(intent);
                }
            });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
