package com.heziz.liyang.ui.zhihui.clwcx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.car.CarNewListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.car.CarDetailsBean1;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.RequestBean;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.utils.TimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CLCXListActivity extends BaseActivity {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.tvName)
    TextView tvName;
    private String name;
    private String id;

    private int page=1;
    private String startTime;
    private String endTime;
    private int flag;
    @BindView(R.id.rg)
    RadioGroup rg;

    private CarNewListAdapter adapter;
    private List<CarDetailsBean1> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clcxlist);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }
    private void initViews() {
        id=getIntent().getStringExtra("id");
        name=getIntent().getStringExtra("name");
        tvTitle.setText("车辆冲洗");
        tvName.setText(name);

        adapter=new CarNewListAdapter(this,list);
        LinearLayoutManager manager=new LinearLayoutManager(this.getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(recycleView);
        adapter.setEmptyView(R.layout.empty_view);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(CLCXListActivity.this, CLCXDetailsActivity.class);
                intent.putExtra("CarDetailsBean1",list.get(position));
                startActivity(intent);
            }
        });
    }
    private void initListeners() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        flag=0;
                        refresh();
                        break;
                    case R.id.rb2:
                        flag=1;
                        refresh();
                        break;
                    case R.id.rb3:
                        flag=2;
                        refresh();
                        break;
                    case R.id.rb4:
                        refresh();
                        flag=3;
                        break;

                }
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getList();
            }
        });
    }

    private void refresh(){
        showProgressDialog();
        page=1;
        list.clear();
        getList();
    }
    private void initDatas() {
        showProgressDialog();
        flag=0;
        page=1;
        getList();
    }
    private void getList(){
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
                startTime1=TimeUtils.getYearTime();
                break;
        }
         /*车辆未冲洗*/
        String url1 = API.CL_NEW_LIST+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        Map<String,String> params1=new HashMap<>();
        Map<String,String> params2=new HashMap<>();
        params1.put("projectName",id);
        params1.put("endTime", endTime1);
        params1.put("startTime",startTime1);
        params1.put("licensePlateColor","黄");
        params2.put("pageNow",page+"");
        params2.put("pageSize","10");
        JsonCallBack1<SRequstBean<RequestBean<List<CarDetailsBean1>>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<RequestBean<List<CarDetailsBean1>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<CarDetailsBean1>>>> response) {
                dissmissProgressDialog();
                if (response.body().getData()!=null){
//                            carDDetailsBeanList.addAll(response.body().getData().getList());
                    if(response.body().getData().getList().size()!=0){
                        list.addAll(response.body().getData().getList());
                        if(list.size()<10){
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
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<CarDetailsBean1>>>> response) {
                super.onError(response);
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData2(url1, params1,params2,jsonCallBack1);
    }

}
