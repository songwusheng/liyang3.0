package com.heziz.liyang.ui.zhihui.fdl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.fdl.FdlStreetListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.fdl.FdlStreetListBean;
import com.heziz.liyang.bean.zh.FDLBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.utils.NumberUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FdlStreetDeviceListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ivIcon)
    ImageView ivIcon;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.tvOnLine)
    TextView tvOnLine;
    @BindView(R.id.tvOffline)
    TextView tvOffline;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    String type;
    FdlStreetListAdapter adapter;
    private List<FdlStreetListBean> fdlStreetListBeanList=new ArrayList<>();

    private UserInfor userInfor;
    Map<String,String> params1=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdl_street_device_list);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
    }

    private void initViews() {
        userInfor= MyApplication.getInstance().getUserInfor();
        tvTitle.setText("非道路机械管理");
        adapter=new FdlStreetListAdapter(this,fdlStreetListBeanList);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(recycleView);
        adapter.setEmptyView(R.layout.empty_view);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(FdlStreetDeviceListActivity.this, FDLProjectListActivity.class);
                intent.putExtra("managerId",String.valueOf(fdlStreetListBeanList.get(position).getId()));
                intent.putExtra("jd",fdlStreetListBeanList.get(position).getName());
                startActivity(intent);
            }
        });
    }

    private void initDatas() {

        getDLData();

        if(userInfor.getPosition().equals("1")){
            params1.put("siteId",userInfor.getStation()+"");
        }else if(userInfor.getPosition().equals("2")){
            params1.put("areaId",userInfor.getManagerId()+"");
        }
        initProjectData();
    }

    private void getDLData() {
        Map<String,String> params2=new HashMap<>();
        if(userInfor.getPosition().equals("1")){
            params2.put("siteId",userInfor.getStation()+"");
        }else if(userInfor.getPosition().equals("2")){
            params2.put("managerId",userInfor.getManagerId()+"");
        }
        String url1 = API.ZH_DATA2+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        JsonCallBack1<SRequstBean<FDLBean>> jsonCallBack1 = new JsonCallBack1<SRequstBean<FDLBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<FDLBean>> response) {
                dissmissProgressDialog();
                if(response.body().getData()!=null){
                    FDLBean fdlBean=response.body().getData();
                    if(fdlBean.getTotalRecord()==0){
                        tvTotal.setText("0%");
                    }else{
                        tvTotal.setText(NumberUtils.getTwoDecimal(fdlBean.getQualifiedNumber()*100.0/fdlBean.getTotalRecord())+"%");
                    }

                    tvOnLine.setText(fdlBean.getTotalRecord()+"");
                    tvOffline.setText(fdlBean.getTotalAmount()+"");
                }else{
                    tvTotal.setText("0");
                    tvOnLine.setText("0");
                    tvOffline.setText("0");
                }
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<FDLBean>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url1, params2, jsonCallBack1);
    }
    private void initProjectData() {
        showProgressDialog();
        String url1 = API.FDL_STREET_LIST+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();

        JsonCallBack1<SRequstBean<List<FdlStreetListBean>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<List<FdlStreetListBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<FdlStreetListBean>>> response) {
                fdlStreetListBeanList.clear();
                fdlStreetListBeanList.addAll(response.body().getData());
                adapter.notifyDataSetChanged();
//                showMark(projectBeanList);
                dissmissProgressDialog();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<FdlStreetListBean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData0(url1, params1, jsonCallBack1);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
        }
    }
}
