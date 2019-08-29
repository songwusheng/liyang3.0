package com.heziz.liyang.adaper.sp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.business.adapter.DataAdapteeImpl;
import com.android.business.adapter.DataAdapterInterface;
import com.android.business.entity.UserInfo;
import com.android.business.exception.BusinessException;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.VProjectBean;
import com.heziz.liyang.bean.sp.ProjectVideoBean;
import com.heziz.liyang.bean.sp.SpProjectBean;
import com.heziz.liyang.bean.sp.VideoProjectBean;
import com.heziz.liyang.ui.zhihui.sp.VXiangqingActivity;
import com.heziz.liyang.utils.ToastUtil;

import java.util.List;

/**
 * Created by sws on 2019-05-15.
 * from:
 * describe:
 */

public class MySpExtendableListViewAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<SpProjectBean> list;
    private ProjectVideoBean projectVideoBean;
    private VideoProjectBean videoProjectBean;
 //    private List<ProjectVideoBean<VideoProjectBean>> deviceList;
//    private YcDeviceListAdapter adapter;
    public MySpExtendableListViewAdapter(Context mContext, List<SpProjectBean> list) {
        this.mContext=mContext;
        this.list=list;
        dataAdapterInterface = DataAdapteeImpl.getInstance();
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
            tvDeviceId.setText(list.get(groupPosition).getDavstring().get(childPosition).getDevicename());
            String status=list.get(groupPosition).getDavstring().get(childPosition).getIsOnline();

            if("Online".equals(status)){
                btn.setText("在线");
                btn.setEnabled(true);
                btn.setBackground(mContext.getResources().getDrawable(R.drawable.btn_yj_bg));
            }else if ("Offline".equals(status)){
                btn.setText("离线");
                btn.setEnabled(false);
                btn.setBackground(mContext.getResources().getDrawable(R.drawable.btn_yj_bg1));
            }else{
                btn.setText("未知");
                btn.setEnabled(true);
                btn.setBackground(mContext.getResources().getDrawable(R.drawable.btn_yj_bg));
            }

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent1=new Intent(mContext,YcDetailsActivity.class);
//                    intent1.putExtra("id",Long.valueOf(list.get(groupPosition).getProjectId()));
//                    intent1.putExtra("name",list.get(groupPosition).getName());
//                    intent1.putExtra("deviceid",list.get(groupPosition).getDavstring().get(childPosition).getDavId());
//                    mContext.startActivity(intent1);
                    projectVideoBean=list.get(groupPosition).getDavstring().get(childPosition);
                    videoProjectBean=list.get(groupPosition).getDavstring().get(childPosition).getMVideoProjectDevice();
                    if(videoProjectBean.getType().equals("0")){
//                        ToastUtil.showToast("大华");
                        setIPPort(projectVideoBean.getVideoDeviceAddress(), projectVideoBean.getVideoDeviceLoginPort());
                        login(projectVideoBean.getVideoDeviceUserName(), projectVideoBean.getVideoDevicePassword());
                    }else if(videoProjectBean.getType().equals("1")){
                        ToastUtil.showToast("视频格式不支持");
                    }else if(videoProjectBean.getType().equals("2")){
                        ToastUtil.showToast("视频格式不支持");
                    }
                }
            });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private DataAdapterInterface dataAdapterInterface;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    boolean ret = (Boolean) msg.obj;
                    if (ret) {
//                        startMyActivity(VXiangqingActivity.class);
                        Intent intent = new Intent(mContext, VXiangqingActivity.class);
                        VProjectBean vProjectBean=new VProjectBean();
                        vProjectBean.setName(projectVideoBean.getDevicename());
                        vProjectBean.setMedia_port(projectVideoBean.getVideoDeviceLevePort());
                        intent.putExtra("project", vProjectBean);
                        intent.putExtra("id", Long.valueOf(videoProjectBean.getProjectId()));
                        mContext.startActivity(intent);
                    } else {
                        ToastUtil.showToast("登陆失败");
                    }
                    break;
            }
        }
    };

    private void setIPPort(String ip, String port) {
        try {
            dataAdapterInterface.createDataAdapter("com.android.business.adapter.DataAdapteeImpl");
            dataAdapterInterface.initServer(ip, Integer.parseInt(port));
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    private void login(final String username, final String password) {
//        mContext.showProgressDialog();
        handler.post(new Runnable() {
            @Override
            public void run() {
                TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                String clientMac = tm.getDeviceId();
                UserInfo info = null;
                try {
                    info = dataAdapterInterface.login(username, password, "1", clientMac, 2);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.what = 0;
                msg.obj = (info != null);
                handler.sendMessage(msg);
            }
        });
    }
}
