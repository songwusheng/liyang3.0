package com.heziz.liyang.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.project.ProjectListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.ProjectBean;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.RequestBean;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.ui.newjm.SanjiActivity;
import com.heziz.liyang.ui.project.ProjectDetailsActivity;
import com.heziz.liyang.utils.ListDataSave;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeProjectListActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;

//    HomeListAdapter1 adapter;
    private UserInfor userInfo;
    ListDataSave dataSave;
    ProjectListAdapter adapter;
    private List<ProjectBean> projectBeanList=new ArrayList<>();
    private String id;
    private int type;
    private int type1;
    private String name;
    Map<String,String> params1=new HashMap<>();
    Map<String,String> params2=new HashMap<>();
    private int pageNow=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_project_list);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initViews() {
        userInfo=MyApplication.getInstance().getUserInfor();
        id=getIntent().getStringExtra("id");
        type=getIntent().getIntExtra("type",0);
        type1=getIntent().getIntExtra("type1",0);
        name=getIntent().getStringExtra("name");
        tvTitle.setText(name);
        dataSave = new ListDataSave(getApplicationContext(), "project_list");
        adapter=new ProjectListAdapter(this,projectBeanList);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), home_bg_icon4);
//        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
adapter.bindToRecyclerView(recycleView);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNow++;
                getData();
            }
        });
    }

    private void initDatas() {
        params1.put("name","");
        params1.put("vasa","");
        if(userInfo.getPosition().equals("2")){
            params1.put("managerRoleIds",userInfo.getManagerId()+"");
        }
        params1.put("pType","");
        params1.put("diff","");
        params1.put("station",API.STATION);
        params2.put("pageSize","10");
        switch (type){
            case 0:
                params1.put("managerRoleIds",id);
                break;
            case 1:

                break;
            case 2:
                params1.put("diff","2");
                break;
            case 3:
                params1.put("diff","1");
                break;
            case 4:
                params1.put("vasa","1");
                break;
            case 5:
                params1.put("vasa","2");
                break;
            case 6:
                params1.put("pType","1");
                break;
            case 7:
                params1.put("pType","2");
                break;
            case 8:
                params1.put("pType","6");
                break;
            case 9:
                params1.put("pType","3");
                break;
            case 10:
                params1.put("pType","5");
                break;
            case 11:
                params1.put("pType","4");
                break;
            case 12:
                params1.put("pType","7");
                break;
            case 13:
                params1.put("diff","1,2");
                break;
            case 14:
                params1.put("diff","0");
                break;

        }
        showProgressDialog();
       getData();


    }

private void getData(){
    String url1 = API.HOME_TOTAL_PROJECT_LIST+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
    params2.put("pageNow",pageNow+"");
    JsonCallBack1<SRequstBean<RequestBean<List<ProjectBean>>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<RequestBean<List<ProjectBean>>>>() {
        @Override
        public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<ProjectBean>>>> response) {

            if(response.body().getData().getList().size()!=0){
                projectBeanList.addAll(response.body().getData().getList());
                if(pageNow==response.body().getData().getPage().getTotalPageCount()){
                    adapter.loadMoreEnd();
                }else{
                    adapter.loadMoreComplete();
                }
            }else{
                if (pageNow==1){
                    adapter.setEmptyView(R.layout.empty_view);
                }else{
                    adapter.loadMoreEnd();
                }

            }
            adapter.notifyDataSetChanged();
            dissmissProgressDialog();
        }

        @Override
        public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<ProjectBean>>>> response) {
            super.onError(response);
            dissmissProgressDialog();
            adapter.loadMoreFail();
        }

    };
    OkGoClient.getInstance()
            .postJsonData2(url1, params1,params2, jsonCallBack1);
}

    private void initListeners() {
        rlBack.setOnClickListener(this);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(type1==0){
                    Intent intent=new Intent(HomeProjectListActivity.this, ProjectDetailsActivity.class);
                    intent.putExtra("id",projectBeanList.get(position).getId());
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(HomeProjectListActivity.this, SanjiActivity.class);
                    intent.putExtra("type",type1);
                    startActivity(intent);
                }

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
}
