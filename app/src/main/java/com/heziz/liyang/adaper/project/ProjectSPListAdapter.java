package com.heziz.liyang.adaper.project;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;

import com.android.business.adapter.DataAdapteeImpl;
import com.android.business.adapter.DataAdapterInterface;
import com.android.business.entity.UserInfo;
import com.android.business.exception.BusinessException;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.VProjectBean;
import com.heziz.liyang.bean.sp.ProjectVideoBean;
import com.heziz.liyang.bean.sp.VideoProjectBean;
import com.heziz.liyang.ui.zhihui.sp.VXiangqingActivity;
import com.heziz.liyang.ui.zhihui.sp.SpDetailsActivity;
import com.heziz.liyang.utils.ToastUtil;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class ProjectSPListAdapter extends BaseQuickAdapter<ProjectVideoBean<VideoProjectBean>,BaseViewHolder> {
    private Context context;
    private ProjectVideoBean projectVideoBean;
    private VideoProjectBean videoProjectBean;
    public ProjectSPListAdapter(Context context, List<ProjectVideoBean<VideoProjectBean>> list) {
        super(R.layout.yc_device_item1,list);
        this.context=context;
        dataAdapterInterface = DataAdapteeImpl.getInstance();
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectVideoBean<VideoProjectBean> item) {
        helper.setText(R.id.tvDeviceId, item.getDevicename());
        helper.setText(R.id.tvId, helper.getLayoutPosition()+1+"");
        String status=item.getIsOnline();

        if("Online".equals(status)){
            ((Button)helper.getView(R.id.btn)).setText("在线");
            ((Button)helper.getView(R.id.btn)).setEnabled(true);
            ((Button)helper.getView(R.id.btn)).setBackground(mContext.getResources().getDrawable(R.drawable.btn_yj_bg));
        }else if ("Offline".equals(status)){
            ((Button)helper.getView(R.id.btn)).setText("离线");
            ((Button)helper.getView(R.id.btn)).setEnabled(false);
            ((Button)helper.getView(R.id.btn)).setBackground(mContext.getResources().getDrawable(R.drawable.btn_yj_bg1));
        }else{
            ((Button)helper.getView(R.id.btn)).setText("未知");
            ((Button)helper.getView(R.id.btn)).setEnabled(true);
            ((Button)helper.getView(R.id.btn)).setBackground(mContext.getResources().getDrawable(R.drawable.btn_yj_bg));
        }

        ((Button)helper.getView(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Intent intent1=new Intent(mContext,YcDetailsActivity.class);
//                    intent1.putExtra("id",Long.valueOf(list.get(groupPosition).getProjectId()));
//                    intent1.putExtra("name",list.get(groupPosition).getName());
//                    intent1.putExtra("deviceid",list.get(groupPosition).getDavstring().get(childPosition).getDavId());
//                    mContext.startActivity(intent1);
                projectVideoBean=item;
                videoProjectBean=item.getMVideoProjectDevice();
                if(videoProjectBean.getType().equals("0")){
//                    ToastUtil.showToast("大华");
                    setIPPort(projectVideoBean.getVideoDeviceAddress(), projectVideoBean.getVideoDeviceLoginPort());
                    login(projectVideoBean.getVideoDeviceUserName(), projectVideoBean.getVideoDevicePassword());
                }else if(videoProjectBean.getType().equals("1")){
                    ToastUtil.showToast("海康-暂不支持播放的视频格式");
                }else if(videoProjectBean.getType().equals("2")){
                    ToastUtil.showToast("拉流-暂不支持播放的视频格式");
                    mContext.startActivity(new Intent(mContext, SpDetailsActivity.class));
                }
            }
        });
    }

    private DataAdapterInterface dataAdapterInterface;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
//                    dissmissProgressDialog();
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
