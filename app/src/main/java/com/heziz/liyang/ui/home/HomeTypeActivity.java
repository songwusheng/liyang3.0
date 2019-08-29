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
import com.heziz.liyang.adaper.HomeListAdapter1;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.HomeListBean;
import com.heziz.liyang.bean.ProjectBean;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.utils.ListDataSave;
import com.heziz.liyang.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeTypeActivity extends BaseActivity implements View.OnClickListener {

    private int type;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    HomeListAdapter1 adapter;
    private UserInfor userInfor;
    private List<HomeListBean> homeListBeans=new ArrayList<>();
    ListDataSave dataSave;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_type);
        ButterKnife.bind(this);
        initViews();

        initDatas();
        initListeners();
    }



    private void initListeners() {
        rlBack.setOnClickListener(this);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(type==1){
                    Intent intent=new Intent(HomeTypeActivity.this,HomeTotalListActivity.class);
//                    intent.putExtra("type",type);
                    intent.putExtra("name",name);
                    intent.putExtra("jdname",homeListBeans.get(position).getPopedomName());
                    intent.putExtra("id",homeListBeans.get(position).getPopedom());
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(HomeTypeActivity.this,HomeProjectListActivity.class);
                    intent.putExtra("type",type);
                    intent.putExtra("name",name);
                    intent.putExtra("id",homeListBeans.get(position).getPopedom());
                    startActivity(intent);
                }


            }
        });
    }

    private void initViews() {
        dataSave = new ListDataSave(getApplicationContext(), "project_list");
        type=getIntent().getIntExtra("type",0);
        switch (type){
            case 1:
                name="总投资额";
                tvType.setText("总投资额\n(亿元)");
                break;
            case 2:
                name="智慧工地";
                tvType.setText("智慧工地\n(个)");
                break;
            case 3:
                name="信息化工地";
                tvType.setText("信息化工地\n(个)");
                break;
            case 4:
                name="市直管项目";
                tvType.setText("市直管项目\n(个)");
                break;
            case 5:
                name="区直管项目";
                tvType.setText("区直管项目\n(个)");
                break;
            case 6:
                name="房建项目";
                tvType.setText("房建项目\n(个)");
                break;
            case 7:
                name="市政项目";
                tvType.setText("市政项目\n(个)");
                break;
            case 8:
                name="园林项目";
                tvType.setText("园林项目\n(个)");
                break;
            case 9:
                name="交通项目";
                tvType.setText("交通项目\n(个)");
                break;
            case 10:
                name="水利项目";
                tvType.setText("水利项目\n(个)");
                break;
            case 11:
                name="地铁项目";
                tvType.setText("地铁项目\n(个)");
                break;
            case 12:
                name="其他项目";
                tvType.setText("其他项目\n(个)");
                break;
                default:{
                    tvTitle.setText(name);
                }
        }
        userInfor=MyApplication.getInstance().getUserInfor();
        adapter=new HomeListAdapter1(this,homeListBeans,type);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), home_bg_icon4);
//        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);

    }

    private void initDatas() {
        List<HomeListBean<List<ProjectBean>>> list=dataSave.getDataList("project");
        LogUtils.show("本地获取到的List长度："+list.size());
//        showProgressDialog();
//        String url = API.HOME_DATA2+"?access_token="+MyApplication.getInstance().getUserInfor().getUuid();
//        Map<String,String> params=new HashMap<>();
//        params.put("station","1551258680345413");
//        JsonCallBack1<SRequstBean<List<HomeListBean<List<ProjectBean>>>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<List<HomeListBean<List<ProjectBean>>>>>() {
//            @Override
//            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<HomeListBean<List<ProjectBean>>>>> response) {
//                List<HomeListBean<List<ProjectBean>>> list=response.body().getData();
                if(userInfor.getPosition().equals("2")) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getPopedom().equals(userInfor.getManagerId())) {
                            homeListBeans.add(list.get(i));
                        }
                    }
                }else{
//                    homeListBeans.addAll(response.body().getData());
                    homeListBeans.addAll(list);
                }
                adapter.notifyDataSetChanged();
//                dissmissProgressDialog();
//            }
//
//            @Override
//            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<HomeListBean<List<ProjectBean>>>>> response) {
//                super.onError(response);
//                dissmissProgressDialog();
//            }
//
//        };
//        OkGoClient.getInstance()
//                .postJsonData(url, params, jsonCallBack1);
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
