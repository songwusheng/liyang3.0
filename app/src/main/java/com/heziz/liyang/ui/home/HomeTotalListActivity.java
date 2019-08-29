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
import com.heziz.liyang.adaper.home.HomeTotalListAdapter;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.HomeListBean;
import com.heziz.liyang.bean.ProjectBean;
import com.heziz.liyang.ui.project.ProjectDetailsActivity;
import com.heziz.liyang.utils.ListDataSave;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeTotalListActivity extends BaseActivity {

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    ListDataSave dataSave;
    HomeTotalListAdapter adapter;
    private List<ProjectBean> projectBeanList=new ArrayList<>();
    private List<HomeListBean> homeListBeans=new ArrayList<>();
    Map<String,String> params1=new HashMap<>();
    private String id;
    private String name;
    private String jdname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_total_list);
        ButterKnife.bind(this);
        initViews();
        initDatas();
    }

    private void initDatas() {
        List<HomeListBean<List<ProjectBean>>> list=dataSave.getDataList("project");

        for(int i=0;i<list.size();i++){
            if(id.equals(list.get(i).getPopedom())){
                projectBeanList.addAll(list.get(i).getList());
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void initViews() {
        id=getIntent().getStringExtra("id");
        name=getIntent().getStringExtra("name");
        jdname=getIntent().getStringExtra("jdname");
        tvTitle.setText(name);
        dataSave = new ListDataSave(getApplicationContext(), "project_list");
        adapter=new HomeTotalListAdapter(this,projectBeanList,jdname);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), home_bg_icon4);
//        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(HomeTotalListActivity.this, ProjectDetailsActivity.class);
                intent.putExtra("id",projectBeanList.get(position).getId());
                startActivity(intent);
            }
        });

        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
