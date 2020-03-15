package com.heziz.liyang.ui.zhihui.sjj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.sjj.SJJdetailsListAdapter;
import com.heziz.liyang.adaper.sjj.SJJdetailsNotesListAdapter;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.sjj.SJJRealTime1Bean;
import com.heziz.liyang.bean.sjj.SJJRealTimeBean;
import com.heziz.liyang.bean.sjj.SjjEDBean;
import com.heziz.liyang.bean.sjj.SjjNotesBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.RequestBean;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.ui.project.ProjectGKActivity;
import com.heziz.liyang.utils.LogUtils;
import com.heziz.liyang.utils.TimeUtils;

import org.json.JSONException;
import org.json.JSONObject;

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

public class SjjDetailsActivity1 extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;


    @BindView(R.id.tvDevceId)
    TextView tvDevceId;
    @BindView(R.id.tvQMstatus)
    TextView tvQMstatus;
    @BindView(R.id.tvHMStatus)
    TextView tvHMStatus;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvSFZId)
    TextView tvSFZId;
    @BindView(R.id.tvSBTime)
    TextView tvSBTime;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.cb)
    CheckBox cb;
    @BindView(R.id.rb4)
    RadioButton rb4;

    private String deviceid;
    private WebSocket mWebSocket;

    Map<String, String> paramPage = new HashMap<>();
    private int pageNow=1;
    private List<SjjNotesBean> notesBeans=new ArrayList<>();
    SJJdetailsNotesListAdapter adapter;
    private int state=1;
    private String startTime;
    private String endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjj_details1);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initViews() {
        deviceid=getIntent().getStringExtra("deviceid");
        tvDevceId.setText(deviceid);
        //tvName.setText(name);
        tvTitle.setText("施工升降机");

        getWebsocket(deviceid);

        paramPage.put("pageNow",pageNow+"");
        paramPage.put("pageSize",10+"");

        adapter=new SJJdetailsNotesListAdapter(this,notesBeans);
        LinearLayoutManager manager=new LinearLayoutManager(this.getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(recycleView);
        adapter.setEmptyView(R.layout.empty_view);

    }

    private void initDatas() {
        getSBJLList(1);
        showProgressDialog();
        ////身份识别初始化
        String urlnum = API.SJJ_DETAILS_SFSB;
        Map<String, String> paramsnum = new HashMap<>();
        paramsnum.put("devNum",deviceid);
        JsonCallBack1<SRequstBean<String>> jsonCallBacknum = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                dissmissProgressDialog();
                String res=response.body().getData();
                if(res.split(";").length!=0){
                    tvSBTime.setText(res.split(";")[0]);
                    if(res.split(";").length==2){
                        String ss=res.split(";")[1];
                        tvName.setText(ss.split("[(]")[0]);
                        tvSFZId.setText(ss.split("[(]")[1].replace(")",""));
                    }

                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(urlnum, paramsnum, jsonCallBacknum);



    }

    private void getSBJLList(int id){
        String url = API.SJJ_DETAILS_SBJL;
        Map<String, String> param1 = new HashMap<>();
        param1.put("relationId",deviceid);
        param1.put("dataType",id+"");
        if(id==4){
            param1.put("startTime",startTime);
            param1.put("endTime",endTime);
        }else{
            param1.put("startTime","");
            param1.put("endTime","");
        }
        JsonCallBack1<SRequstBean<RequestBean<List<SjjNotesBean>>>> jsonCallBacknum1 = new JsonCallBack1<SRequstBean<RequestBean<List<SjjNotesBean>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<SjjNotesBean>>>> response) {
                if (response.body().getData()!=null){
//                            carDDetailsBeanList.addAll(response.body().getData().getList());
                    if(response.body().getData().getList().size()!=0){
                        notesBeans.addAll(response.body().getData().getList());
                        if(pageNow==response.body().getData().getPage().getTotalPageCount()){
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
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<SjjNotesBean>>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData2(url, param1,paramPage, jsonCallBacknum1);
    }


    private void initListeners() {
        rlBack.setOnClickListener(this);
        rb4.setOnClickListener(this);
        cb.setOnClickListener(this);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNow++;
                getSBJLList(state);
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        state=1;
                        changeStateGETDatas();
                        break;
                    case R.id.rb2:
                        state=2;
                        changeStateGETDatas();
                        break;
                    case R.id.rb3:
                        state=3;
                        changeStateGETDatas();
                        break;
                    case R.id.rb4:
                        state=4;
                        break;
                }
            }
        });

    }

    private void changeStateGETDatas(){
        showProgressDialog();
        pageNow=1;
        notesBeans.clear();
        getSBJLList(state);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //case R.id.cb:
            //    Intent intent=new Intent(SjjDetailsActivity.this, ProjectGKActivity.class);
            //    intent.putExtra("id",id);
            //    startActivity(intent);
            //    break;
            case R.id.rlBack:
                finish();
                break;
            case R.id.rb4:
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
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
                        changeStateGETDatas();
                    }
                })
                        .build();
                pvTime.setTitleText("请选择结束时间");
                pvTime.show();
                break;
            case R.id.cb:
                Intent intent=new Intent(mContext, ProjectGKActivity.class);
                intent.putExtra("id",getIntent().getLongExtra("id",0));
                startActivity(intent);
                break;
        }
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
                        try {
                            JSONObject object=new JSONObject(s);
                            JSONObject object1=object.getJSONObject("message");
                            String dataType=object1.getString("dataType");
                            Gson gson=new Gson();
                            if(dataType.equals("10")){
                                SJJRealTimeBean bean=gson.fromJson(s, SJJRealTimeBean.class);
                                Message message = Message.obtain();
                                message.what = 0;
                                message.obj = bean;
                                mHandler.sendMessage(message);
                            }else if(dataType.equals("12")){
                                SJJRealTime1Bean bean=gson.fromJson(s, SJJRealTime1Bean.class);
                                Message message = Message.obtain();
                                message.what = 1;
                                message.obj = bean;
                                mHandler.sendMessage(message);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


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
                    SJJRealTimeBean bean=(SJJRealTimeBean) msg.obj;

                    String lockF=bean.getMessage().getLockFrontState();
                    if(lockF.equals("1")){
                        tvQMstatus.setText("开启");
                    }else{
                        tvQMstatus.setText("关闭");
                    }
                    String lockB=bean.getMessage().getLockBackState();
                    if(lockB.equals("1")){
                        tvHMStatus.setText("开启");
                    }else{
                        tvHMStatus.setText("关闭");
                    }
                    break;
                //case 1:
                //    SJJRealTime1Bean bean1=(SJJRealTime1Bean) msg.obj;
                //    tvsfsb.setText(bean1.getName()+"("+bean1.getIdCard()+")");
                //    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebSocket.cancel();
    }

}
