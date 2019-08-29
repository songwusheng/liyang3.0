package com.heziz.liyang.ui.zhihui.yc;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.heziz.liyang.R;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.yc.YcRealNumberBean;
import com.heziz.liyang.bean.yc.YcRealTimeBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.utils.LogUtils;
import com.heziz.liyang.utils.NumberUtils;
import com.heziz.liyang.utils.TimeUtils;

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

public class YcDetailsNewActivity extends BaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPM10)
    TextView tvPM10;
    @BindView(R.id.tvPM25)
    TextView tvPM25;
    @BindView(R.id.tvZs)
    TextView tvZs;
    @BindView(R.id.tvWD)
    TextView tvWD;
    @BindView(R.id.tvSD)
    TextView tvSD;
    @BindView(R.id.tvFs)
    TextView tvFs;
    @BindView(R.id.tvFx)
    TextView tvFx;
    @BindView(R.id.chart)
    LineChart chart;
    @BindView(R.id.rg1)
    RadioGroup rg1;
    @BindView(R.id.rg2)
    RadioGroup rg2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.llEmpty)
    LinearLayout llEmpty;

    private String name;
    private String deviceid;
    private WebSocket mWebSocket;

    private String flag="a34002-Rtd";
    private int type;
    private List<YcRealNumberBean> list=new ArrayList<>();
    private String defTime;
    Map<String,String> params=new HashMap<>();
    TimePickerView pvTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yc_details_new);
        ButterKnife.bind(this);

        initViews();
        initDatas();
        initListeners();
    }




    private void initViews() {
        tvTitle.setText("扬尘监测");
        name=getIntent().getStringExtra("name");
        deviceid=getIntent().getStringExtra("deviceid");
        getWebsocket(deviceid);
        tvName.setText(name);

        //时间选择器
        pvTime = new TimePickerBuilder(YcDetailsNewActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
//                                date.setHours(23);
//                                date.setMinutes(59);
//                                date.setSeconds(59);
//                                endTime=TimeUtils.setSTime(date);
                date.setHours(23);
                date.setMinutes(59);
                date.setSeconds(59);
                defTime=TimeUtils.setSTime(date);
                params.put("defTime",defTime);
                type=2;
                getReal();
            }
        })
                .build();
        pvTime.setTitleText("请选择查询时间");
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
                        YcRealTimeBean bean=gson.fromJson(s, YcRealTimeBean.class);
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

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    YcRealTimeBean bean=(YcRealTimeBean) msg.obj;
                    tvPM10.setText("PM10:"+ NumberUtils.getTwoDecimal(bean.getA34002Rtd()/1000.0));
                    tvPM25.setText("PM2.5:"+NumberUtils.getTwoDecimal(bean.getA34004Rtd()/1000.0));
                    tvZs.setText(bean.getLARtd()+"");
                    tvWD.setText(bean.getA01001Rtd()+"/");
                    tvSD.setText(bean.getA01002Rtd()+"");
                    tvFx.setText(getFX(bean.getA01008Rtd())+"");
                    tvFs.setText(bean.getA01007Rtd()+"");
                    break;
            }
        }
    };

    private String getFX(double windD){
        String fx;
        if((windD>=0&&windD<22.5)||(windD<=360&&windD<337.5)){
            fx="北风";
        }else if(windD>=22.5&&windD<67.5){
            fx="东北风";
        }else if(windD>=67.5&&windD<112.5){
            fx="东风";
        }else if(windD>=112.5&&windD<157.5){
            fx="东南风";
        }else if(windD>=157.5&&windD<202.5){
            fx="南风";
        }else if(windD>=202.5&&windD<247.5){
            fx="西南风";
        }else if(windD>=247.5&&windD<292.5){
            fx="西风";
        }else{
            fx="西北风";
        }
        return fx;

    }
    private void initDatas() {
        getReal();
    }
    private void initListeners() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                params.put("defTime",defTime);
                switch (i){
                    case R.id.rb1:
                        type=0;
                        getReal();
                        break;
                    case R.id.rb2:
                        type=1;
                        getReal();
                        break;
                    case R.id.rb3:

                        pvTime.show();

                        break;
                }
            }
        });
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb4:
                        flag="a34002-Rtd";
                        getReal();
                        break;
                    case R.id.rb5:
                        flag="a34004-Rtd";
                        getReal();
                        break;
                }
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!pvTime.isShowing()){
                    pvTime.show();
                }
            }
        });
    }
    private void getReal(){
        list.clear();
        showProgressDialog();
        String url = API.YC_HISTORY_URL1;
        params.put("access_token", MyApplication.getInstance().getUserInfor().getUuid());
        params.put("deviceId",deviceid+"");
        params.put("type",type+"");
        params.put("typeName",flag);
        JsonCallBack1<SRequstBean<List<YcRealNumberBean>>> jsonCallBack = new JsonCallBack1<SRequstBean<List<YcRealNumberBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<YcRealNumberBean>>> response) {
//                if (flag==0){
                dissmissProgressDialog();
                if(response.body().getData()!=null){
                    list.addAll(response.body().getData());
                    //Collections.reverse(list);
                    if(list.size()!=0){
                        initChart1();
                        llEmpty.setVisibility(View.GONE);
                        chart.setVisibility(View.VISIBLE);
                    }else{
                        llEmpty.setVisibility(View.VISIBLE);
                        chart.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<YcRealNumberBean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url, params, jsonCallBack);
    }

    private void initChart1(){
        {   // // Chart Style // //

            // background color
            chart.setBackgroundColor(Color.WHITE);

            // disable description text
            chart.getDescription().setEnabled(false);

            // enable touch gestures
            chart.setTouchEnabled(true);

            // set listeners
//            chart.setOnChartValueSelectedListener(getActivity());
            chart.setDrawGridBackground(false);

//            // create marker to display box when values are selected
//            MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
//
//            // Set the marker to the chart
//            mv.setChartView(chart);
//            chart.setMarker(mv);

            // enable scaling and dragging
            chart.setDragEnabled(true);
            chart.setScaleEnabled(true);
            // chart.setScaleXEnabled(true);
            // chart.setScaleYEnabled(true);

            // force pinch zoom along both axis
            chart.setPinchZoom(true);
        }

        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            xAxis.setTextSize(6);
            xAxis.setLabelRotationAngle(45);
            xAxis.setLabelCount(12);
            // vertical grid lines
//          如果设置为true，则在绘制时会避免“剪掉”在x轴上的图表或屏幕边缘的第一个和最后一个坐标轴标签项。
            xAxis.setAvoidFirstLastClipping(true);
//            xAxis.enableGridDashedLine(10f, 10f, 0f);
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return list.get((int)value).getTimeSlot().split("T")[1].substring(0,5);
                }
            });
        }

        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = chart.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            chart.getAxisRight().setEnabled(false);

            // horizontal grid lines
//            yAxis.enableGridDashedLine(10f, 10f, 0f);
            yAxis.setDrawGridLines(false);

            // axis range
            yAxis.setAxisMaximum(200f);
            yAxis.setAxisMinimum(0f);
        }


        {   // // Create Limit Lines // //
            LimitLine llXAxis = new LimitLine(9f, "Index 10");
            llXAxis.setLineWidth(4f);
            llXAxis.enableDashedLine(10f, 3f, 0f);
            llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            llXAxis.setTextSize(10f);
//            llXAxis.setTypeface(tfRegular);

            LimitLine ll1 = new LimitLine(150f, "PM10");
            ll1.setLineWidth(1f);
            ll1.enableDashedLine(20f, 3f, 0f);
            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            ll1.setTextSize(10f);
            ll1.setLineColor(getResources().getColor(R.color.zxt_yjx_color));
//            ll1.setTypeface(tfRegular);

            LimitLine ll2 = new LimitLine(100f, "PM2.5");
            ll2.setLineWidth(1f);
            ll2.enableDashedLine(20f, 3f, 0f);
            ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            ll2.setTextSize(10f);
            ll2.setLineColor(getResources().getColor(R.color.zxt_yjx_color));
//            ll2.setTypeface(tfRegular);

            // draw limit lines behind data instead of on top
            yAxis.setDrawLimitLinesBehindData(true);
            xAxis.setDrawLimitLinesBehindData(true);
            yAxis.removeAllLimitLines();
            // add limit lines
            if(flag.equals("a34002-Rtd")){
            yAxis.addLimitLine(ll1);
        }else{
            yAxis.addLimitLine(ll2);
        }


//            xAxis.addLimitLine(llXAxis);
        }

        // add data
        setData();

        // draw points over time
        chart.animateX(1000);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // draw legend entries as lines
        l.setForm(Legend.LegendForm.LINE);
    }

    private void setData() {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            float val1 = Float.valueOf(list.get(i).getAvg());
            values.add(new Entry(i, val1, null));
        }


        LineDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            if(flag.equals("a34002-Rtd")){
                set1.setLabel("PM10");
            }else{
                set1.setLabel("PM2.5");
            }
            set1.notifyDataSetChanged();
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            if(flag.equals("a34002-Rtd")){
                set1 = new LineDataSet(values, "PM10");
            }else{
                set1 = new LineDataSet(values, "PM2.5");
            }


            set1.setDrawIcons(false);

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f);

            set1.setDrawCircleHole(true);
            // black lines and points
            set1.setColor(getResources().getColor(R.color.maincolor));
            set1.setCircleColor(getResources().getColor(R.color.maincolor));

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(2f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setDrawValues(false);
            // set the filled area
            set1.setDrawFilled(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            chart.setData(data);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mWebSocket!=null){
            mWebSocket.cancel();
        }
    }
}
