package com.heziz.liyang.ui.zhihui.sp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.business.adapter.DataAdapteeImpl;
import com.android.business.adapter.DataAdapterInterface;
import com.android.business.entity.UserInfo;
import com.android.business.exception.BusinessException;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.sp.DeviceInfoAdapter;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.VProjectBean;
import com.heziz.liyang.bean.sp.ProjectVideoBean;
import com.heziz.liyang.bean.sp.VideoProjectBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.RequestBean;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpDetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private Long id;
    DeviceInfoAdapter adapter;
    ProjectVideoBean<VideoProjectBean> projectVideoBean;
    private List<ProjectVideoBean<VideoProjectBean>> list=new ArrayList<>();

    @BindView(R.id.videoView)
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_details);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();

        MediaController mediaController = new MediaController(this);
        String uri="rtsp://112.85.138.10:9090/dss/monitor/params?cameraid=1000562$0&substream=1";
        videoView.setVideoURI(Uri.parse(uri));
        videoView.setMediaController(mediaController);
        videoView.start();
    }

    private void initDatas() {


        String url = API.SP_LIST;
        Map<String,String> params=new HashMap<>();
        params.put("projectId",id+"");
        JsonCallBack1<SRequstBean<RequestBean<List<ProjectVideoBean<VideoProjectBean>>>>> jsonCallBack = new JsonCallBack1<SRequstBean<RequestBean<List<ProjectVideoBean<VideoProjectBean>>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<ProjectVideoBean<VideoProjectBean>>>>> response) {

                if(response.body().getData()!=null){
                    list.addAll(response.body().getData().getList());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<ProjectVideoBean<VideoProjectBean>>>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData1(url, params, "mVideoProjectDevice", jsonCallBack);
    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
    }

    private void initViews() {
        dataAdapterInterface = DataAdapteeImpl.getInstance();
        id=getIntent().getLongExtra("id",0);
        tvTitle.setText("视频监控");

        adapter=new DeviceInfoAdapter(this,list);
        LinearLayoutManager manager=new LinearLayoutManager(this.getApplicationContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(recycleView);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent=new Intent(getActivity(), ProjectDetailsActivity.class);
//                intent.putExtra("id",projectBeanList.get(position).getId());
//                startActivity(intent);

                projectVideoBean=list.get(position);
                VideoProjectBean videoProjectBean=projectVideoBean.getMVideoProjectDevice();
                if(videoProjectBean.getType().equals("0")){
                    ToastUtil.showToast("大华");
                    setIPPort(projectVideoBean.getVideoDeviceAddress(), projectVideoBean.getVideoDeviceLoginPort());
                    login(projectVideoBean.getVideoDeviceUserName(), projectVideoBean.getVideoDevicePassword());
                }else if(videoProjectBean.getType().equals("1")){
                    ToastUtil.showToast("海康");
                }else if(videoProjectBean.getType().equals("2")){
                    ToastUtil.showToast("拉流");
                }

            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
        }
    }

    private DataAdapterInterface dataAdapterInterface;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    dissmissProgressDialog();
                    boolean ret = (Boolean) msg.obj;
                    if (ret) {
//                        startMyActivity(VXiangqingActivity.class);
                        Intent intent = new Intent(SpDetailsActivity.this, VXiangqingActivity.class);
                        VProjectBean vProjectBean=new VProjectBean();
                        vProjectBean.setName("aaa");
                        vProjectBean.setMedia_port(projectVideoBean.getVideoDeviceLevePort());
                        intent.putExtra("project", vProjectBean);
                        startActivity(intent);
                    } else {
                        toast("登陆失败");
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
        showProgressDialog();
        handler.post(new Runnable() {
            @Override
            public void run() {
                TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
                if (ActivityCompat.checkSelfPermission(SpDetailsActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
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
