package com.heziz.liyang.ui.zhihui.yc;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
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
import com.heziz.liyang.adaper.yc.YCdetailsListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.ProjectBean;
import com.heziz.liyang.bean.car.CarWZBean;
import com.heziz.liyang.bean.yc.YcRealNumberBean;
import com.heziz.liyang.bean.yc.YcRealTimeBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.RequestBean;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.utils.LogUtils;
import com.heziz.liyang.utils.NumberUtils;
import com.heziz.liyang.utils.ProjectUtils;
import com.heziz.liyang.utils.TimeUtils;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
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

public class YcDetailsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.cb)
    CheckBox cb;
    @BindView(R.id.llgcgk)
    LinearLayout llgcgk;
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

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvName1)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
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
    private Long id;
    private String name;
    private String deviceid;
    private WebSocket mWebSocket;
    private List<YcRealNumberBean> pm25List=new ArrayList<>();
    private List<YcRealNumberBean> pm10List=new ArrayList<>();
    private int num;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private int page=1;
    private int state;
    private String startTime;
    private String endTime;
    private YCdetailsListAdapter adapter;
    private List<YcRealTimeBean> ycDetailsBeanList=new ArrayList<>();
    /*定位参数*/
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private BDLocation location1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yc_details);
        ButterKnife.bind(this);

        initLocation();
        initViews();
        initDatas();
        initListeners();
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

    private void initViews() {

        id=getIntent().getLongExtra("id",0);
        name=getIntent().getStringExtra("name");
        deviceid=getIntent().getStringExtra("deviceid");
        getWebsocket(deviceid);
        tvName.setText(name);
        tvTitle.setText("扬尘检测");
        llgcgk.setVisibility(View.GONE);

        adapter=new YCdetailsListAdapter(this,ycDetailsBeanList);
        LinearLayoutManager manager=new LinearLayoutManager(this.getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(recycleView);

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
        getReal(0,"a34002-Rtd");
        getReal(1,"a34004-Rtd");

        getNumbers();

        getList(state,page);

        /*工程详情*/
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

    private void getNumbers() {
        /*车辆未冲洗违章次数*/
        String url1 = API.YC_BJ_URL+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        Map<String,String> params1=new HashMap<>();
        params1.put("id",id+"");
        params1.put("pm10flag","true");
        params1.put("pm10state","1");
        JsonCallBack1<SRequstBean<String>> jsonCallBack1 = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {

                String s=response.body().getData().replace("\\","");
                LogUtils.show(s);
                Gson gson=new Gson();
                CarWZBean carWZBean=gson.fromJson(s,CarWZBean.class);
                rb1.setText("今日\n"+carWZBean.getToday());
                rb2.setText("本周\n"+carWZBean.getThisWeek());
                rb4.setText("本月\n"+carWZBean.getThisMonth());
                rb5.setText("自定义查询\n");
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(url1, params1, jsonCallBack1);
    }
    private void initListeners() {
        rlBack.setOnClickListener(this);
        ivfzrcall.setOnClickListener(this);
        ivfzrxx.setOnClickListener(this);
        ivjdcall.setOnClickListener(this);
        ivjdxx.setOnClickListener(this);
        ivDh.setOnClickListener(this);
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
                TimePickerView pvTime = new TimePickerBuilder(YcDetailsActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        date.setHours(23);
                        date.setMinutes(59);
                        date.setSeconds(59);
                        endTime=TimeUtils.setSTime(date);
                        date.setHours(0);
                        date.setMinutes(0);
                        date.setSeconds(0);
                        startTime=TimeUtils.setSTime(date);
                        request(3);
                    }
                })
                .build();
                pvTime.setTitleText("请选择结束时间");
                pvTime.show();
            }
        });

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    llgcgk.setVisibility(View.VISIBLE);
                }else{
                    llgcgk.setVisibility(View.GONE);
                }
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getList(state,page);
            }
        });
    }
    private void request(int i){
        page=1;
        state=i;
        ycDetailsBeanList.clear();
        getList(i,page);
    }
    private void getReal(int flag,String pm){
        String url = API.YC_HISTORY_URL;
        Map<String,String> params=new HashMap<>();
        params.put("access_token", MyApplication.getInstance().getUserInfor().getUuid());
        params.put("deviceId",deviceid+"");
        params.put("typeName",pm);
        JsonCallBack1<SRequstBean<List<YcRealNumberBean>>> jsonCallBack = new JsonCallBack1<SRequstBean<List<YcRealNumberBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<YcRealNumberBean>>> response) {

                if (flag==0){
                    pm10List.addAll(response.body().getData());
                    Collections.reverse(pm10List);
                    num++;
                }else{
                    pm25List.addAll(response.body().getData());
                    Collections.reverse(pm25List);
                    num++;
                }
                if(num==2){
                    initChart1();
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

    private void getList(int flag,int page){
        String endTime1=TimeUtils.getISOCurrentTime();
        String startTime1="";
        switch (flag){
            case 0:
                startTime1=TimeUtils.getISODayTime();
                break;
            case 1:
                startTime1=TimeUtils.getISOWeekTime();
                break;
            case 2:
                startTime1=TimeUtils.getISOMonthTime();
                break;
            case 3:
                startTime1=startTime;
                endTime1=endTime;
                break;
        }
         /*车辆未冲洗*/
        String url1 = API.YC_BJ_LIST+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        Map<String,String> params1=new HashMap<>();
        Map<String,String> params2=new HashMap<>();
        params1.put("projectId",id+"");
        params1.put("enddate", endTime1);
        params1.put("startdate",startTime1);
        params1.put("pm10flag","true");
        params1.put("pm10state","1");
        params2.put("pageNow",page+"");
        params2.put("pageSize","10");
        JsonCallBack1<SRequstBean<RequestBean<List<YcRealTimeBean>>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<RequestBean<List<YcRealTimeBean>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<YcRealTimeBean>>>> response) {

//                Log.w("main",response.body().getData().getList().toString());
                if (response.body().getData()!=null){
//                            carDDetailsBeanList.addAll(response.body().getData().getList());
                    if(response.body().getData().getList().size()!=0){
                        ycDetailsBeanList.addAll(response.body().getData().getList());
                        adapter.loadMoreComplete();
                    }else{
                        adapter.loadMoreEnd();
                    }
                }else{
                    adapter.loadMoreFail();
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<YcRealTimeBean>>>> response) {
                super.onError(response);
//                adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData2(url1, params1,params2,jsonCallBack1);
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
                    return pm25List.get((int)value).getTimeSlot().split("T")[1].substring(0,5);
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
            ll1.setLineColor(getResources().getColor(R.color.lan));
//            ll1.setTypeface(tfRegular);

            LimitLine ll2 = new LimitLine(100f, "PM2.5");
            ll2.setLineWidth(1f);
            ll2.enableDashedLine(20f, 3f, 0f);
            ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            ll2.setTextSize(10f);
            ll2.setLineColor(getResources().getColor(R.color.lan));
//            ll2.setTypeface(tfRegular);

            // draw limit lines behind data instead of on top
            yAxis.setDrawLimitLinesBehindData(true);
            xAxis.setDrawLimitLinesBehindData(true);

            // add limit lines
            yAxis.addLimitLine(ll1);
            yAxis.addLimitLine(ll2);
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

        ArrayList<Entry> valuesPM10 = new ArrayList<>();
        ArrayList<Entry> valuesPM25 = new ArrayList<>();

        for (int i = 0; i < pm25List.size(); i++) {

            float val1 = Float.valueOf(pm25List.get(i).getAvg());
            valuesPM25.add(new Entry(i, val1, null));
        }
        for (int i = 0; i < pm10List.size(); i++) {
            float val = Float.valueOf(pm10List.get(i).getAvg());
            valuesPM10.add(new Entry(i, val, null));
        }

        LineDataSet set1;
        LineDataSet set2;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(valuesPM10);
            set2.setValues(valuesPM25);
            set1.notifyDataSetChanged();
            set2.notifyDataSetChanged();
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(valuesPM10, "PM10");
            set2 = new LineDataSet(valuesPM25, "PM2.5");

            set1.setDrawIcons(false);
            set2.setDrawIcons(false);

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f);
            set2.enableDashedLine(10f, 5f, 0f);

            set1.setDrawCircleHole(true);
            set2.setDrawCircleHole(true);
            // black lines and points
            set1.setColor(getResources().getColor(R.color.lan));
            set2.setColor(getResources().getColor(R.color.lan));
            set1.setCircleColor(getResources().getColor(R.color.lan));
            set2.setCircleColor(getResources().getColor(R.color.lan));

            // line thickness and point size
            set1.setLineWidth(1f);
            set2.setLineWidth(1f);
            set1.setCircleRadius(2f);
            set2.setCircleRadius(2f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);
            set2.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set2.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set2.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);
            set2.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);
            set2.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setDrawValues(false);
            set2.enableDashedHighlightLine(10f, 5f, 0f);
            set2.setDrawValues(false);
            // set the filled area
            set1.setDrawFilled(false);
            set2.setDrawFilled(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });
            set2.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets
            dataSets.add(set2); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            chart.setData(data);
        }
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
            case R.id.ivfzrxx:
                sendSmsWithBody(tvPhone.getText().toString());
                break;
            case R.id.ivjdxx:
                sendSmsWithBody(tvJdPhone.getText().toString());
                break;
        }
    }
    public void sendSmsWithBody(String number) {
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("smsto:" + number));
//        sendIntent.putExtra("sms_body", body);
        startActivity(sendIntent);
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
//                    Log.w("main","websocket接收到的消息："+(String) msg.obj.toString());
                    tvTime.setText("最近更新时间："+TimeUtils.getCurrentTime());
                    YcRealTimeBean bean=(YcRealTimeBean) msg.obj;
                    tvPM10.setText("PM10:"+ NumberUtils.getTwoDecimal(bean.getA34002Rtd()/1000.0));
                    tvPM25.setText("PM2.5:"+NumberUtils.getTwoDecimal(bean.getA34004Rtd()/1000.0));
                    tvZs.setText(bean.getLARtd()+"");
                    tvWD.setText(bean.getA01001Rtd()+"/");
                    tvSD.setText(bean.getA01002Rtd()+"");
                    tvFx.setText(bean.getA01008Rtd()+"");
                    tvFs.setText(bean.getA01007Rtd()+"");
//                    String s=msg.obj.toString();
//                    String[] strings=s.split(",");
//                    for(int i=0;i<strings.length;i++){
//                        if (strings[i].contains("a34002-Rtd")){
//                            String[] s1=strings[i].split(":");
//                            tvPM10.setText("PM10:"+Integer.valueOf(s1[1])/1000);
//                        }
//                        if (strings[i].contains("a34004-Rtd")){
//                            String[] s1=strings[i].split(":");
//                            tvPM25.setText("PM2.5:"+Integer.valueOf(s1[1].replace("}",""))/1000);
//                        }
//                        if (strings[i].contains("LA-Rtd")){
//                            String[] s1=strings[i].split(":");
//                            tvZs.setText(s1[1]+"");
//                        }
//                        if (strings[i].contains("a01001-Rtd")){
//                            String[] s1=strings[i].split(":");
//                            tvWD.setText(s1[1]+"/");
//                        }
//                        if (strings[i].contains("a01002-Rtd")){
//                            String[] s1=strings[i].split(":");
//                            tvSD.setText(s1[1]+"");
//                        }
//                        if (strings[i].contains("a01008-Rtd")){
//                            String[] s1=strings[i].split(":");
//                            tvFx.setText(s1[1]+"");
//                        }
//                        if (strings[i].contains("a01007-Rtd")){
//                            String[] s1=strings[i].split(":");
//                            tvFs.setText(s1[1]+"");
//                        }
//
//                    }
////
                    break;
            }
        }
    };


    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
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
}
