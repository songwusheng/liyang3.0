package com.heziz.liyang.ui.zhihui.clwcx;

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
import com.google.gson.Gson;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.car.CardetailsListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.car.CarDetailsBean;
import com.heziz.liyang.bean.car.CarWZBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.RequestBean;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.utils.LogUtils;
import com.heziz.liyang.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarDetailsActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private int page=1;
    private int state;
    private String startTime;
    private String endTime;
    private CardetailsListAdapter adapter;
    private List<CarDetailsBean> carDetailsBeanList=new ArrayList<>();
    private Long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        ButterKnife.bind(this);
        id=getIntent().getLongExtra("id",0);
        initViews();
        initDatas();
        initListeners();
    }

    private void initListeners() {
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
                TimePickerView pvTime = new TimePickerBuilder(CarDetailsActivity.this, new OnTimeSelectListener() {
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

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getList(state,page);
            }
        });
//        adapter.disableLoadMoreIfNotFullPage();


    }

    private void request(int i){
        page=1;
        state=i;
        carDetailsBeanList.clear();
        getList(i,page);
    }
    private void initDatas() {

        getNumbers();
        getList(state,page);
//        getList(1,TimeUtils.getCurrentTime(),TimeUtils.getWeekTime());
//        getList(2,TimeUtils.getCurrentTime(),TimeUtils.getMonthTime());
    }

    private void getNumbers() {
        showProgressDialog();
        /*车辆未冲洗违章次数*/
        String url1 = API.CAR_WZ_NUM+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        Map<String,String> params1=new HashMap<>();
        params1.put("projectId",id+"");
        params1.put("licensePlateColor","黄");
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
    private void getList(int flag,int page){
        String endTime1=TimeUtils.getCurrentTime();
        String startTime1="";
        switch (flag){
            case 0:
                startTime1=TimeUtils.getYesterdayTime();
                break;
            case 1:
                startTime1=TimeUtils.getWeekTime();
                break;
            case 2:
                startTime1=TimeUtils.getMonthTime();
                break;
            case 3:
                startTime1=startTime;
                endTime1=endTime;
                break;
        }
         /*车辆未冲洗*/
        String url1 = API.CAR_PROJECT_LIST+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        Map<String,String> params1=new HashMap<>();
        Map<String,String> params2=new HashMap<>();
        params1.put("projectId",id+"");
        params1.put("endTime", endTime1);
        params1.put("startTime",startTime1);
        params1.put("licensePlateColor","黄");
        params2.put("pageNow",page+"");
        params2.put("pageSize","10");
        JsonCallBack1<SRequstBean<RequestBean<List<CarDetailsBean>>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<RequestBean<List<CarDetailsBean>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<CarDetailsBean>>>> response) {
                dissmissProgressDialog();
                if (response.body().getData()!=null){
//                            carDDetailsBeanList.addAll(response.body().getData().getList());
                    if(response.body().getData().getList().size()!=0){
                        carDetailsBeanList.addAll(response.body().getData().getList());
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
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<CarDetailsBean>>>> response) {
                super.onError(response);
                adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData2(url1, params1,params2,jsonCallBack1);
    }

    private void initViews() {

        tvTitle.setText("车辆未冲洗");

        adapter=new CardetailsListAdapter(this,carDetailsBeanList);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
