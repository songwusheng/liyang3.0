package com.heziz.liyang.ui.project;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.gson.Gson;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.project.ProjectSPListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.PDBean;
import com.heziz.liyang.bean.ProjectBean;
import com.heziz.liyang.bean.sp.ProjectVideoBean;
import com.heziz.liyang.bean.sp.VideoProjectBean;
import com.heziz.liyang.bean.yc.YcProjectDBean;
import com.heziz.liyang.bean.yc.YcProjectDeviceBean;
import com.heziz.liyang.bean.yc.YcRealTimeBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.RequestBean;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.ui.zhihui.yc.YcDetailsNewActivity;
import com.heziz.liyang.ui.zhihui.clwcx.CarDetailsActivity;
import com.heziz.liyang.utils.LogUtils;
import com.heziz.liyang.utils.NumberUtils;
import com.heziz.liyang.utils.ProjectUtils;
import com.heziz.liyang.utils.TimeUtils;
import com.heziz.liyang.utils.ToastUtil;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import static com.dahuatech.utilslib.ActivityUtils.startActivity;

public class ProjectDetailsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    private Long id;
    @BindView(R.id.tvName)
    TextView tvName;
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
    @BindView(R.id.tvCar)
    TextView tvCar;
    @BindView(R.id.tvSp)
    TextView tvSp;
    @BindView(R.id.tvYc)
    TextView tvYc;
    @BindView(R.id.tvYc1)
    TextView tvYc1;
    @BindView(R.id.ivDh)
    ImageView ivDh;
    @BindView(R.id.ivfzrcall)
    ImageView ivfzrcall;
    @BindView(R.id.ivfzrxx)
    ImageView ivfzrxx;
    @BindView(R.id.ivjdcall)
    ImageView ivjdcall;
    @BindView(R.id.ivjdxx)
    ImageView ivjdxx;
    @BindView(R.id.tvXmxj)
    TextView tvXmxj;
    @BindView(R.id.tvWlxj)
    TextView tvWlxj;
    @BindView(R.id.llcar)
    LinearLayout llcar;
    @BindView(R.id.llsp)
    LinearLayout llsp;
    @BindView(R.id.llyc)
    LinearLayout llyc;

    @BindView(R.id.llSpList)
    LinearLayout llSpList;

    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    ProjectSPListAdapter adapter;
    private List<ProjectVideoBean<VideoProjectBean>> splist=new ArrayList<>();
    ProjectBean bean;
    private String ycDeviceid;
    private WebSocket mWebSocket;
    private List<YcProjectDBean<YcProjectDeviceBean>> list;
    /*定位参数*/
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private BDLocation location1;

    private int spNum;
    boolean spFlag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        ButterKnife.bind(this);
        id=getIntent().getLongExtra("id",0);
        initViews();
        initLocation();
        initDatas();
        getSpData();
        initListeners();

    }

    private void initViews() {
        adapter=new ProjectSPListAdapter(this,splist);
        LinearLayoutManager manager=new LinearLayoutManager(this.getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
//        adapter.bindToRecyclerView(recycleView);
    }

    private void initLocation(){
        mLocationClient = new LocationClient(this);
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    private void initDatas() {
        showProgressDialog();
        /*工程详情*/
        String url = API.PROJECT_DETAILS;
        Map<String,String> params=new HashMap<>();
        params.put("access_token", MyApplication.getInstance().getUserInfor().getUuid());
        params.put("id",id+"");
        JsonCallBack1<SRequstBean<ProjectBean>> jsonCallBack = new JsonCallBack1<SRequstBean<ProjectBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<ProjectBean>> response) {
                dissmissProgressDialog();
                bean=response.body().getData();
                tvName.setText(bean.getName());
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

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<ProjectBean>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url, params, jsonCallBack);

        /*车辆未冲洗*/
        String url1 = API.CAR_COUNT+"?access_token="+MyApplication.getInstance().getUserInfor().getUuid();
        Map<String,String> params1=new HashMap<>();
        params1.put("projectId",id+"");
        params1.put("endTime",TimeUtils.getCurrentTime());
        params1.put("startTime",TimeUtils.getYesterdayTime());
        params1.put("licensePlateColor","黄");
        JsonCallBack1<SRequstBean<String>> jsonCallBack1 = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {

                String s=response.body().getData().replace("\\","");
                LogUtils.show(s);
                Gson gson=new Gson();
                PDBean bean=gson.fromJson(s, PDBean.class);
//                try {
//                    String car=object.getString("ftpAlarmCount");
                    tvCar.setText("今日违章"+bean.getFtpAlarmCount()+"起");
                    spNum=Integer.valueOf(bean.getVideoNum());
                    tvSp.setText("已安装"+bean.getVideoNum()+"台");
                    tvYc.setText("告警"+bean.getWeatherAlarmNum()+"次");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(url1, params1, jsonCallBack1);

         /*扬尘设备*/
        String url2 = API.YC_LIST+"?access_token="+MyApplication.getInstance().getUserInfor().getUuid();
        Map<String,String> params2=new HashMap<>();
        params2.put("projectId",id+"");
        JsonCallBack1<SRequstBean<RequestBean<List<YcProjectDBean<YcProjectDeviceBean>>>>> jsonCallBack2 = new JsonCallBack1<SRequstBean<RequestBean<List<YcProjectDBean<YcProjectDeviceBean>>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<YcProjectDBean<YcProjectDeviceBean>>>>> response) {

                list=response.body().getData().getList();
                Log.w("main","扬尘设备："+response.body().getData().getList().size());
                if(list.size()!=0){
                    ycDeviceid=list.get(0).getWeacherDeviceId();
                    getWebsocket(ycDeviceid);
                }else{
                    tvYc.setText("未安装");
                    tvYc1.setText("");
                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<YcProjectDBean<YcProjectDeviceBean>>>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData1(url2, params2,"mWeatherProjectDevice", jsonCallBack2);
    }

    private void getSpData() {

        String url = API.SP_LIST;
        Map<String,String> params=new HashMap<>();
        params.put("projectId",id+"");
        JsonCallBack1<SRequstBean<RequestBean<List<ProjectVideoBean<VideoProjectBean>>>>> jsonCallBack = new JsonCallBack1<SRequstBean<RequestBean<List<ProjectVideoBean<VideoProjectBean>>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<ProjectVideoBean<VideoProjectBean>>>>> response) {

                if(response.body().getData()!=null){
                    splist.addAll(response.body().getData().getList());
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

    private void getWebsocket(String ycDeviceid) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(API.WEBSOCKET_URL)
                .build();
        mWebSocket = okHttpClient.newWebSocket(request, new WebSocketListener() {

            //创建线程池，
            ExecutorService writeExecutor = Executors.newSingleThreadExecutor();
            WebSocket webSocket = null;

            @Override
            public void onOpen(final WebSocket webSocket, final Response response) {
                LogUtils.show("Websocket连接成功");
                this.webSocket = webSocket;
                //建立连接成功后，发送消息给服务器端
                writeExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        //socket 发送信息到服务器
                        webSocket.send("?userCode=123&relationId="+ycDeviceid);
                    }
                });
            }

            @Override
            public void onMessage(final WebSocket webSocket, String text) {

//获取到服务器发送过来的信息，然后通过handler进行UI线程的操作
                String s=text.replace("\\","");
                LogUtils.show(s);
                Gson gson=new Gson();
                YcRealTimeBean bean=gson.fromJson(s, YcRealTimeBean.class);
                String a= String.valueOf(NumberUtils.getTwoDecimal(bean.getA34002Rtd()/1000.0));
                Message message = Message.obtain();
                message.what = 0;
                message.obj = a;
                mHandler.sendMessage(message);
            }

            //webSocket关闭时，关闭线程池
            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
                writeExecutor.shutdown();
            }
        });
    }

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
//                    Log.w("main","websocket接收到的消息："+(String) msg.obj.toString());
                    String s=msg.obj.toString();
//                    String[] strings=s.split(",");
//                    for(int i=0;i<strings.length;i++){
//                        if (strings[i].contains("a34002-Rtd")){
//                            String[] s1=strings[i].split(":");
//                            tvYc1.setText("当前扬尘"+Integer.valueOf(s1[1])/1000+"μg/m³");
                            tvYc1.setText("当前扬尘"+s+"μg/m³");
//                        }
//                    }
//
                    break;
            }
        }
    };

    private void initListeners() {
        rlBack.setOnClickListener(this);
        ivfzrcall.setOnClickListener(this);
        ivfzrxx.setOnClickListener(this);
        ivjdcall.setOnClickListener(this);
        ivjdxx.setOnClickListener(this);
        ivDh.setOnClickListener(this);
        llcar.setOnClickListener(this);
        llsp.setOnClickListener(this);
        llyc.setOnClickListener(this);
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
            case R.id.llcar:
                Intent intent=new Intent(ProjectDetailsActivity.this,CarDetailsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
            case R.id.llyc:
                if(list!=null&&list.size()!=0){
                    Intent intent1=new Intent(ProjectDetailsActivity.this,YcDetailsNewActivity.class);
                    intent1.putExtra("id",id);
                    intent1.putExtra("name",bean.getName());
                    intent1.putExtra("deviceid",list.get(0).getWeacherDeviceId());
                    startActivity(intent1);
                }else{
                    ToastUtil.showToast("未安装扬尘设备");
                }
                break;
            case R.id.llsp:
//                if(spNum!=0){
//                    Intent intent2=new Intent(ProjectDetailsActivity.this,SpDetailsActivity.class);
//                    intent2.putExtra("id",id);
//                    startActivity(intent2);
//                }else{
//                    ToastUtil.showToast("未安装");
//                }
                if(spFlag){
                    spFlag=false;
                    llSpList.setVisibility(View.GONE);
                }else{
                    if(splist.size()!=0){
                        spFlag=true;
                        llSpList.setVisibility(View.VISIBLE);
                    }

                }

                break;

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


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            location1=location;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mWebSocket!=null){
            mWebSocket.cancel();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
