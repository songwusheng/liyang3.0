package com.heziz.liyang.ui.zhihui.sjj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.gson.Gson;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.sjj.SJJdetailsListAdapter;
import com.heziz.liyang.adaper.td.TDdetailsListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.sjj.MessageBean;
import com.heziz.liyang.bean.sjj.SJJRealTime1Bean;
import com.heziz.liyang.bean.sjj.SJJRealTimeBean;
import com.heziz.liyang.bean.sjj.SjjBJNumBean;
import com.heziz.liyang.bean.sjj.SjjDeviceNumBean;
import com.heziz.liyang.bean.sjj.SjjEDBean;
import com.heziz.liyang.bean.sjj.SjjProjectBean;
import com.heziz.liyang.bean.td.TDAlarmNumBean;
import com.heziz.liyang.bean.td.TdAlarmBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.HezhiResponse;
import com.heziz.liyang.network.JsonCallBack;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.RequestBean;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.ui.project.ProjectGKActivity;
import com.heziz.liyang.ui.zhihui.td.TdDetailsActivity;
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

public class SjjDetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.cb)
    TextView cb;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvjxsqgd)
    TextView tvjxsqgd;
    @BindView(R.id.tvdqzz)
    TextView tvdqzz;
    @BindView(R.id.tvqxd1)
    TextView tvqxd1;
    @BindView(R.id.tvqxd2)
    TextView tvqxd2;
    @BindView(R.id.tvyxsd)
    TextView tvyxsd;
    @BindView(R.id.tvfhl)
    TextView tvfhl;
    @BindView(R.id.tvqsxg)
    TextView tvqsxg;
    @BindView(R.id.tvedzz)
    TextView tvedzz;
    @BindView(R.id.tvyxqxd)
    TextView tvyxqxd;
    @BindView(R.id.tvqmzt)
    TextView tvqmzt;
    @BindView(R.id.tvhmzt)
    TextView tvhmzt;
    @BindView(R.id.tvsbh)
    TextView tvsbh;
    @BindView(R.id.tvsfsb)
    TextView tvsfsb;
    @BindView(R.id.tvsfsbTime)
    TextView tvsfsbTime;
    @BindView(R.id.tvwzyx)
    TextView tvwzyx;
    @BindView(R.id.tvljyx)
    TextView tvljyx;
    @BindView(R.id.lineChart)
    LineChart lineChart;

    private List<SJJRealTimeBean> sjjRealTimeBeanList=new ArrayList<>();
    private Long id;
    private String deviceid;
    private WebSocket mWebSocket;

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
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private SJJdetailsListAdapter adapter;
    private List<MessageBean> list=new ArrayList<>();

    private int page=1;
    private int state;
    private String startTime;
    private String endTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjj_details);
        ButterKnife.bind(this);
        initViews();
        initCharts(lineChart);
        initDatas();
        initListeners();
    }

    private void initViews() {
        id=getIntent().getLongExtra("id",0);
        deviceid=getIntent().getStringExtra("deviceid");
        tvsbh.setText(deviceid);
        getWebsocket(deviceid);
        //tvName.setText(name);
        tvTitle.setText("升降机");

        adapter=new SJJdetailsListAdapter(this,list);
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
        showProgressDialog();
        //额定起升重量，额定高度，允许倾斜度初始化
        Map<String,String> params1=new HashMap<>();
        params1.put("devNum",deviceid);
        String url1 = API.SJJ_DETAILS_ED;

        JsonCallBack1<SRequstBean<List<SjjEDBean>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<List<SjjEDBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<SjjEDBean>>> response) {

                SjjEDBean sjjEDBean=response.body().getData().get(0);
                tvqsxg.setText(sjjEDBean.getLimitHeight()+"m");
                tvedzz.setText(sjjEDBean.getLimitWeight()+"t");
                tvyxqxd.setText(sjjEDBean.getLimitBatter()+"°");
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<SjjEDBean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(url1, params1, jsonCallBack1);
        ////身份识别初始化
        String urlnum = API.SJJ_DETAILS_SFSB;
        Map<String, String> paramsnum = new HashMap<>();
        paramsnum.put("devNum",deviceid);
        JsonCallBack1<SRequstBean<String>> jsonCallBacknum = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {

                String res=response.body().getData();

                if(res.split(";").length!=0){
                    tvsfsbTime.setText(res.split(";")[0]);
                    if(res.split(";").length==2){
                        tvsfsb.setText(res.split(";")[1]);
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

        getNumbers();
        getList(0);
    }


    private void initListeners() {
        cb.setOnClickListener(this);
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
                TimePickerView pvTime = new TimePickerBuilder(SjjDetailsActivity.this, new OnTimeSelectListener() {
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
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getList(state);
            }
        });
    }
    private void request(int i){
        page=1;
        state=i;
        list.clear();
        showProgressDialog();
        getList(i);
    }

    private void getNumbers() {
        /*车辆未冲洗违章次数*/
        String url1 = API.SJJ_BJ_NUM;
        Map<String,String> params1=new HashMap<>();
        params1.put("devNum",deviceid+"");
        JsonCallBack1<SRequstBean<SjjBJNumBean>> jsonCallBack1 = new JsonCallBack1<SRequstBean<SjjBJNumBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<SjjBJNumBean>> response) {
                dissmissProgressDialog();
                SjjBJNumBean s=response.body().getData();
                if(s!=null){
                    rb1.setText("今日\n"+s.getDayCount());
                    rb2.setText("本周\n"+s.getWeekCount());
                    rb4.setText("本月\n"+s.getMonthCount());
                    rb5.setText("自定义查询\n");
                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<SjjBJNumBean>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url1, params1, jsonCallBack1);
    }

    private void getList(int flag){
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
        String url1 = API.SJJ_BJ_LIST;
        Map<String,String> params1=new HashMap<>();
        Map<String,String> params2=new HashMap<>();
        params1.put("alarmType","0");
        params1.put("type","2");
        params1.put("endTime", endTime1);
        params1.put("startTime",startTime1);
        params1.put("devNum",deviceid);

        params2.put("pageNow",page+"");
        params2.put("pageSize","10");
        JsonCallBack1<SRequstBean<RequestBean<List<MessageBean>>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<RequestBean<List<MessageBean>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<MessageBean>>>> response) {

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
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<MessageBean>>>> response) {
                super.onError(response);
//                adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData2(url1, params1,params2,jsonCallBack1);
    }

    //初始化图表
    private void initCharts(LineChart chart) {
        // enable description text
        chart.getDescription().setEnabled(false);
        chart.setNoDataText("暂无数据");
        chart.setTouchEnabled(true);
        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(false);
        // if disabled, scaling can be done on x- and y-axis separately

        chart.setPinchZoom(true);
        // set an alternative background color
//        chart.setBackgroundColor(Color.LTGRAY);
        //设置一页最大显示个数为6，超出部分就滑动
        //float ratio = (float) sjjRealTimeBeanList.size()/(float) 6;
        ////显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
        //chart.zoom(ratio,1f,0,0);
        //至此已经已经完成了左右滑动的效果。

        //这个时候数据还是从第一条开始显示，左右滑动进行查看。如果希望从最后一个数据查看，使用
        //chart.moveViewToX(sjjRealTimeBeanList.size() - 1);

        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);
        // add empty data
        chart.setData(data);
        // 左下角图例设置
        Legend l = chart.getLegend();
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(getResources().getColor(R.color.maincolor1));
        l.setEnabled(false);

        XAxis xl = chart.getXAxis();
        xl.setTextColor(getResources().getColor(R.color.maincolor1));
        xl.setDrawGridLines(false);
        xl.setTextSize(10);
        xl.setDrawLabels(true);
        xl.setEnabled(true);

        //很重要，设置x轴上的标签数和点数一样，不会出现多余的标签
        xl.setGranularity(1);
        //xl.setLabelCount(6,false);
        //xl.setLabelRotationAngle(45);
        //设置显示X轴
        xl.setDrawAxisLine(true);
        //设置X轴显示的位置
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setAvoidFirstLastClipping(true);



        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTextColor(getResources().getColor(R.color.maincolor1));
        leftAxis.setDrawGridLines(true);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setAxisMinimum(0f);
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
    }

    //PM2.5折线图添加数据
    private void addEntry25(SJJRealTimeBean bean) {
        LineData data = lineChart.getData();
        if (data != null) {
            ILineDataSet set = data.getDataSetByIndex(0);
            if (set == null) {
                set = createSet25();
                data.addDataSet(set);
            }


            lineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    String time="";
                    int value1=(int)value;
                    //if(type==0){
                    if(value1<sjjRealTimeBeanList.size()&&value1>=0){
                        time=sjjRealTimeBeanList.get(value1).getMessage().getDataTime().substring(11,19);
                    }

                    return time;
                }
            });
            data.addEntry(new Entry(set.getEntryCount(), Float.valueOf(bean.getMessage().getWeight()),bean), 0);

            data.notifyDataChanged();
            lineChart.notifyDataSetChanged();
            //lineChart.setVisibleXRangeMaximum(120);
            lineChart.invalidate();
            lineChart.setVisibleXRangeMaximum(4);
            //int count= set.getEntryCount() > 5 ? 4 : set.getEntryCount() > 2 ? set.getEntryCount() - 1 : 1;
            //lineChart.getXAxis().setLabelCount(count);
            //lineChart.getXAxis().setAvoidFirstLastClipping(true);
            //lineChart.getXAxis().setAxisM
            lineChart.moveViewToX(data.getEntryCount());
        }
    }

    //PM2.5折线图设置
    private LineDataSet createSet25() {
        LineDataSet set = new LineDataSet(null, "当前载重");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(getResources().getColor(R.color.maincolor1));
        set.setCircleColor(getResources().getColor(R.color.maincolor));
        set.setLineWidth(1f);
        set.setCircleRadius(2f);
        set.setDrawCircleHole(false);
        set.setValueTextColor(getResources().getColor(R.color.maincolor1));
        set.setValueTextSize(9f);
        set.setDrawValues(false);

        return set;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cb:
                Intent intent=new Intent(SjjDetailsActivity.this, ProjectGKActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
            case R.id.rlBack:
                finish();
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
                    //String type=bean.getMessage().getDataType();
                    //if(type.equals("10")){
                    sjjRealTimeBeanList.add(bean);
                    addEntry25(bean);
                    tvjxsqgd.setText(bean.getMessage().getHeight()+"m");
                    tvdqzz.setText(bean.getMessage().getWeight()+"t");
                    tvqxd1.setText(bean.getMessage().getAngularity2());
                    tvqxd2.setText(bean.getMessage().getAngularity1());
                    tvyxsd.setText(bean.getMessage().getSpeed()+"m/s");
                    tvfhl.setText(bean.getMessage().getWeightPer()+"%");
                    String lockF=bean.getMessage().getLockFrontState();
                    if(lockF.equals("1")){
                        tvqmzt.setText("异常");
                    }else{
                        tvqmzt.setText("正常");
                    }
                    String lockB=bean.getMessage().getLockBackState();
                    if(lockB.equals("1")){
                        tvhmzt.setText("异常");
                    }else{
                        tvhmzt.setText("正常");
                    }
                    break;
                case 1:
                    SJJRealTime1Bean bean1=(SJJRealTime1Bean) msg.obj;
                    tvsfsb.setText(bean1.getName()+"("+bean1.getIdCard()+")");
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebSocket.cancel();
    }
}
