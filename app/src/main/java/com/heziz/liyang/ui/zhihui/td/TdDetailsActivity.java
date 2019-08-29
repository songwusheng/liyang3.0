package com.heziz.liyang.ui.zhihui.td;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.td.TDdetailsListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.td.TDAlarmNumBean;
import com.heziz.liyang.bean.td.TDRealTimeBean;
import com.heziz.liyang.bean.td.TdAlarmBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.RequestBean;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.ui.project.ProjectGKActivity;
import com.heziz.liyang.utils.LogUtils;
import com.heziz.liyang.utils.TimeUtils;
import com.heziz.liyang.view.DashboardView;
import com.heziz.liyang.view.SpinnerPopuwindow;

import java.util.ArrayList;
import java.util.Date;
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

public class TdDetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.cb)
    TextView cb;
    //@BindView(R.id.llgcgk)
    //LinearLayout llgcgk;
    //@BindView(R.id.meterView)
    //MeterView meterView;
    @BindView(R.id.dash)
    DashboardView dashboardView4;

    @BindView(R.id.tvKd)
    TextView tvKd;
    @BindView(R.id.tvDz)
    TextView tvDz;
    @BindView(R.id.tvGd)
    TextView tvGd;
    @BindView(R.id.tvFd)
    TextView tvFd;
    @BindView(R.id.tvZj)
    TextView tvZj;
    @BindView(R.id.tvQxd)
    TextView tvQxd;
    @BindView(R.id.tvFs)
    TextView tvFs;

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.rg1)
    RadioGroup rg1;
    @BindView(R.id.rg2)
    RadioGroup rg2;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rb4)
    RadioButton rb4;
    @BindView(R.id.rb5)
    RadioButton rb5;
    @BindView(R.id.rb6)
    RadioButton rb6;

    @BindView(R.id.llT)
    LinearLayout llT;
    @BindView(R.id.tvType1)
    TextView tvType1;
    /** 类型筛选数据*/
    private List<String> lxData;
    private String type_lx;
    private SpinnerPopuwindow lxSpinnerPopuwindow;
    /*定位参数*/
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private BDLocation location1;

    private Long id;
    private String deviceid;
    private int page=1;
    private int state;
    private String startTime;
    private String endTime;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private TDdetailsListAdapter adapter;
    private List<TdAlarmBean> list=new ArrayList<>();
    String currentType="0";

    private WebSocket mWebSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_td_details);
        ButterKnife.bind(this);
        initLocation();
        initViews();
        initDatas();
        initListeners();
    }



    private void initViews() {

        id=getIntent().getLongExtra("id",0);
        deviceid=getIntent().getStringExtra("deviceid");
        getWebsocket(deviceid);
        //tvName.setText(name);
        tvTitle.setText("塔吊预警");
        dashboardView4.setRealTimeValue(0);
        tvKd.setText("回旋0°");
        //meterView.setData(0);

        adapter=new TDdetailsListAdapter(this,list);
        LinearLayoutManager manager=new LinearLayoutManager(this.getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(recycleView);
        adapter.setEmptyView(R.layout.empty_view);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent=new Intent(getActivity(), ProjectDetailsActivity.class);
//                intent.putExtra("id",projectBeanList.get(position).getId());
//                startActivity(intent);
            }
        });
    }

    private void initDatas() {
        LXData();
        //获取项目概况
        //getProjectDetails();
        //获取报警数量
        getNumbers();
        //获取报警列表
        getList(state,page,currentType);
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

    private void initListeners() {
        tvType1.setOnClickListener(this);
        rlBack.setOnClickListener(this);
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()){
                    case R.id.rb1:
                        rb6.setChecked(true);
                        request(0);
                        break;
                    case R.id.rb2:
                        rb6.setChecked(true);
                        request(1);
                        break;
                }
            }
        });
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()){
                    case R.id.rb4:
                        rb3.setChecked(true);
                        request(2);
                        break;
                    case R.id.rb5:
                        rb3.setChecked(true);
                        break;
                }

            }
        });
        rb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(TdDetailsActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        date.setHours(23);
                        date.setMinutes(59);
                        date.setSeconds(59);
                        endTime= TimeUtils.setSTime(date);
                        date.setHours(0);
                        date.setMinutes(0);
                        date.setSeconds(0);
                        startTime= TimeUtils.setSTime(date);
                        request(3);
                    }
                })
                        .build();
                pvTime.setTitleText("请选择结束时间");
                pvTime.show();
            }
        });
cb.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(TdDetailsActivity.this, ProjectGKActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
});
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getList(state,page,currentType);
            }
        });
    }

    private void request(int i){
        page=1;
        state=i;
        list.clear();
        currentType="0";
        showProgressDialog();
        getList(i,page,currentType);
    }

    private void getNumbers() {
        /*车辆未冲洗违章次数*/
        String url1 = API.TD_BJ_URL+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        Map<String,String> params1=new HashMap<>();
        params1.put("deviceNo",deviceid+"");
        JsonCallBack1<SRequstBean<TDAlarmNumBean>> jsonCallBack1 = new JsonCallBack1<SRequstBean<TDAlarmNumBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<TDAlarmNumBean>> response) {

                TDAlarmNumBean s=response.body().getData();
                if(s!=null){
                    rb1.setText("今日\n"+s.getToday());
                    rb2.setText("本周\n"+s.getThisWeek());
                    rb4.setText("本月\n"+s.getThisMonth());
                    rb5.setText("自定义查询\n");
                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<TDAlarmNumBean>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url1, params1, jsonCallBack1);
    }

    private void getList(int flag,int page,String type){
        String endTime1= TimeUtils.getCurrentTime();
        String startTime1="";
        switch (flag){
            case 0:
                startTime1= TimeUtils.getYesterdayTime();
                break;
            case 1:
                startTime1= TimeUtils.getWeekTime();
                break;
            case 2:
                startTime1= TimeUtils.getMonthTime();
                break;
            case 3:
                startTime1=startTime;
                endTime1=endTime;
                break;
        }
         /*车辆未冲洗*/
        String url1 = API.TD_BJ_LIST+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        Map<String,String> params1=new HashMap<>();
        Map<String,String> params2=new HashMap<>();
        if (flag==0){
            params1.put("alarmTime","1");
        }else{
            params1.put("alarmTime","");
        }
        params1.put("alarmType",type);
        params1.put("endTime", endTime1);
        params1.put("startTime",startTime1);
        params1.put("deviceNum",deviceid);

        params2.put("pageNow",page+"");
        params2.put("pageSize","10");
        JsonCallBack1<SRequstBean<RequestBean<List<TdAlarmBean>>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<RequestBean<List<TdAlarmBean>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<TdAlarmBean>>>> response) {

//                Log.w("main",response.body().getData().getList().toString());
                if (response.body().getData()!=null){
//                            carDDetailsBeanList.addAll(response.body().getData().getList());
                    if(response.body().getData().getList().size()!=0){
                        list.addAll(response.body().getData().getList());
                        if(page==response.body().getData().getPage().getTotalPageCount()){
                            adapter.loadMoreEnd();
                        }else{
                            adapter.loadMoreComplete();
                        }
                    }else{
                        adapter.loadMoreEnd();
                    }
                }else{
                    adapter.loadMoreFail();
                }

                adapter.notifyDataSetChanged();
                dissmissProgressDialog();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<TdAlarmBean>>>> response) {
                super.onError(response);
//                adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData2(url1, params1,params2,jsonCallBack1);
    }

    private void getWebsocket(String ycDeviceid) {
        new Thread(){
            @Override
            public void run() {
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
                        TDRealTimeBean bean=gson.fromJson(s, TDRealTimeBean.class);
                        Message message = Message.obtain();
                        message.what = 0;
                        message.obj = bean;
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
        }.start();

    }

    @SuppressLint("HandlerLeak")
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
//                    tvTime.setText("最近更新时间："+TimeUtils.getCurrentTime());
                    TDRealTimeBean bean=(TDRealTimeBean) msg.obj;
                    String type=bean.getMessage().getDataType();
                    if(type.equals("7")){
                        tvDz.setText(""+bean.getMessage().getWeight()+"t");
                        tvGd.setText(""+bean.getMessage().getHeight()+"m");
                        tvFd.setText(""+bean.getMessage().getRange()+"m");
                        tvZj.setText(""+bean.getMessage().getRotation()+"°");
                        tvQxd.setText(""+bean.getMessage().getObliquity()+"°");
                        tvFs.setText(""+bean.getMessage().getWindSpeed()+"m/s");
                        tvKd.setText("回旋"+bean.getMessage().getRotation()+"°");
                        float kd=Float.valueOf(bean.getMessage().getRotation());
                        if(kd>540){
                            kd=540;
                        }else if(kd<-540){
                            kd=-540;
                        }
                        dashboardView4.setRealTimeValue(kd,true,10);
                    }

                    //meterView.setData(Float.valueOf(bean.getMessage().getRotation()));
                    break;
            }
        }
    };



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
            case R.id.tvType1:
                type_lx = tvType1.getText().toString();
                lxSpinnerPopuwindow = new SpinnerPopuwindow(TdDetailsActivity.this,type_lx,lxData,lxitemsOnClick);
                lxSpinnerPopuwindow.showPopupWindow(llT);
                lxSpinnerPopuwindow.setTitleText("类型");//给下拉列表设置标题
                break;
        }
    }


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            location1=location;
        }
    }
    private AdapterView.OnItemClickListener lxitemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = lxData.get(lxSpinnerPopuwindow.getText());
            tvType1.setText(value);
            lxSpinnerPopuwindow.dismissPopupWindow();
            //if(position==0){
            //    params1.put("pType","");
            //}else{
            //    params1.put("pType",position+"");
            //}
            currentType=position+"";
            list.clear();
            getList(state,1,currentType);
        }
    };
    /**
     * 类型数据 1-风速，2-角度，3-力矩，4-幅度，5-吊重，6-高度
     */
    private void LXData() {
        lxData = new ArrayList<>();
        lxData.add("全部");
        lxData.add("风速");
        lxData.add("角度");
        lxData.add("力矩");
        lxData.add("幅度");
        lxData.add("吊重");
        lxData.add("高度");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mWebSocket!=null){
            mWebSocket.cancel();
        }
    }
}
