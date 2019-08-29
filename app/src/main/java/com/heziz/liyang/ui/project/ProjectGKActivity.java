package com.heziz.liyang.ui.project;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.heziz.liyang.R;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.ProjectBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.utils.ProjectUtils;
import com.heziz.liyang.utils.TimeUtils;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectGKActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;


    @BindView(R.id.tvName)
    TextView tvName1;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvFzr)
    TextView tvFzr;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvStreet)
    TextView tvStreet;
    @BindView(R.id.tvJdFzr)
    TextView tvJdFzr;
    @BindView(R.id.tvJdPhone)
    TextView tvJdPhone;
    @BindView(R.id.tvGxjb)
    TextView tvGxjb;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvSx)
    TextView tvSx;
    @BindView(R.id.tvXk)
    TextView tvXk;
    @BindView(R.id.tvkg)
    TextView tvkg;
    @BindView(R.id.tvjg)
    TextView tvjg;
    @BindView(R.id.ivDh)
    ImageView ivDh;
    @BindView(R.id.tvXmxj)
    TextView tvXmxj;
    @BindView(R.id.tvWlxj)
    TextView tvWlxj;
    @BindView(R.id.ivfzrcall)
    ImageView ivfzrcall;
    @BindView(R.id.ivfzrxx)
    ImageView ivfzrxx;
    @BindView(R.id.ivjdcall)
    ImageView ivjdcall;
    @BindView(R.id.ivjdxx)
    ImageView ivjdxx;
    ProjectBean bean;
    private Long id;

    /*定位参数*/
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private BDLocation location1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_gk);
        ButterKnife.bind(this);

        initViews();

        initLocation();
        initListeners();
    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
        ivfzrcall.setOnClickListener(this);
        ivfzrxx.setOnClickListener(this);
        ivjdcall.setOnClickListener(this);
        ivjdxx.setOnClickListener(this);
        ivDh.setOnClickListener(this);
    }

    private void initViews() {
        tvTitle.setText("项目概况");
        id=getIntent().getLongExtra("id",0);
        getProjectDetails();

    }
    private void initLocation(){
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }
    private void getProjectDetails(){
         /*工程详情*/
         showProgressDialog();
        String url = API.PROJECT_DETAILS;
        Map<String,String> params=new HashMap<>();
        params.put("access_token", MyApplication.getInstance().getUserInfor().getUuid());
        params.put("id",id+"");
        JsonCallBack1<SRequstBean<ProjectBean>> jsonCallBack = new JsonCallBack1<SRequstBean<ProjectBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<ProjectBean>> response) {

                bean=response.body().getData();
                tvName1.setText(bean.getName());
                tvAddress.setText(bean.getAddress());
                tvFzr.setText(ProjectUtils.getValue(bean.getXmLeader()));
                tvPhone.setText(ProjectUtils.getValue(bean.getCreatePhone()));
                tvStreet.setText(ProjectUtils.getValue(bean.getJdName()));
                tvJdFzr.setText(ProjectUtils.getValue(bean.getWg()));
                tvJdPhone.setText(ProjectUtils.getValue(bean.getJdPhone()+""));
                tvGxjb.setText(ProjectUtils.getGXJB(bean.getGxjb()));
                tvType.setText(ProjectUtils.getType(bean.getPType()));
                tvXk.setText(ProjectUtils.getValue(bean.getNightConsNum()));
                tvXmxj.setText(ProjectUtils.getValue(bean.getZjResult()));
                tvWlxj.setText(ProjectUtils.getValue(bean.getWgResult()));
                if(bean.getContractStartDate()!=null){
                    tvkg.setText(TimeUtils.getTime(bean.getContractStartDate()));
                }else{
                    tvkg.setText("-");
                }
                if(bean.getContractEndDate()!=null){
                    tvjg.setText(ProjectUtils.getValue(TimeUtils.getTime(bean.getContractEndDate())));
                }else{
                    tvjg.setText("-");
                }

                tvSx.setText(ProjectUtils.getDiff(bean.getDiff()));
dissmissProgressDialog();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<ProjectBean>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url, params, jsonCallBack);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
            case R.id.ivfzrcall:
                callPhone(tvPhone.getText().toString());
                break;
            case R.id.ivjdcall:
                callPhone(tvJdPhone.getText().toString());
                break;
            case R.id.ivfzrxx:
                sendSmsWithBody(tvPhone.getText().toString());
                break;
            case R.id.ivjdxx:
                sendSmsWithBody(tvJdPhone.getText().toString());
                break;
            case R.id.ivDh:
                if (isAvilible(this, "com.autonavi.minimap")||isAvilible(this, "com.baidu.BaiduMap")){
                    if(isAvilible(this, "com.baidu.BaiduMap")){
                        setUpBaiduAPPByLoca();
                    }else{
                        startNaviGao();
                    }
                }else{
                    toast("请先安装百度或高德地图客户端");
                }

                break;
        }
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            location1=location;
        }
    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
    public void sendSmsWithBody(String number) {
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("smsto:" + number));
//        sendIntent.putExtra("sms_body", body);
        startActivity(sendIntent);
    }

    public void setUpBaiduAPPByLoca(){
        try {
            Intent intent = Intent.getIntent("intent://map/direction?origin=latlng:"+location1.getLatitude()+","+location1.getLongitude()+"|name:我的位置&destination=latlng:"+bean.getLatitude()+","+bean.getLongitude()+"|name:"+bean.getAddress()+"&mode=driving&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            startActivity(intent);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void startNaviGao() {
        try {
            //sourceApplication
            Intent intent = Intent.getIntent("androidamap://route?sourceApplication=softname&slat="+location1.getLatitude()+"&slon="+location1.getLongitude()+"&sname="+"我的位置"+"&dlat="+bean.getLatitude()+"&dlon="+bean.getLongitude()+"&dname="+bean.getAddress()+"&dev=0&m=0&t=1");
            startActivity(intent);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    //验证各种导航地图是否安装
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }
}
