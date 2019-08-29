package com.heziz.liyang.ui.newjm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.HomeListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.HomeListBean;
import com.heziz.liyang.bean.ProjectBean;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.ui.home.HomeProjectListActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TdSmzAqZlActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    String title;
    HomeListAdapter adapter;
    private int type;
    private List<HomeListBean<List<ProjectBean>>> homeListBeans = new ArrayList<>();

    private UserInfor userInfor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_td_smz_aq_zl);
        ButterKnife.bind(this);

        initViews();

        initDatas();

        initListeners();
    }



    private void initListeners() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!userInfor.getPosition().equals("3")) {
                    Intent intent = new Intent(TdSmzAqZlActivity.this, HomeProjectListActivity.class);
                    intent.putExtra("type", 0);
                    intent.putExtra("type1", type);
                    intent.putExtra("name", homeListBeans.get(position).getPopedomName());
                    intent.putExtra("id", homeListBeans.get(position).getPopedom());
                    startActivity(intent);
                }


            }
        });
    }

    private void initViews() {
        type=getIntent().getIntExtra("type",0);
        title=getIntent().getStringExtra("title");
        tvTitle.setText(title);
        userInfor = MyApplication.getInstance().getUserInfor();
        adapter = new HomeListAdapter(this, homeListBeans);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), home_bg_icon4);
//        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
    }

    private void initDatas() {
        showProgressDialog();
        String url = API.HOME_DATA2 + "?access_token=" + userInfor.getUuid();
        Map<String, String> params = new HashMap<>();
        params.put("station", API.STATION);
        if (userInfor.getPosition().equals("3")) {
            params.put("createName", userInfor.getAccount());
        }
        JsonCallBack1<SRequstBean<List<HomeListBean<List<ProjectBean>>>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<List<HomeListBean<List<ProjectBean>>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<HomeListBean<List<ProjectBean>>>>> response) {
                List<HomeListBean<List<ProjectBean>>> list = response.body().getData();
                if (list.size()==0){
                    adapter.setEmptyView(R.layout.empty_view);
                }else{
                    if (userInfor.getPosition().equals("2")) {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getPopedom().equals(userInfor.getManagerId())) {
                                homeListBeans.add(list.get(i));
                            }
                        }
                    } else if (userInfor.getPosition().equals("3")) {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getListSize() != 0) {
                                homeListBeans.add(list.get(i));
                            }
                        }
                    } else {
                        homeListBeans.addAll(response.body().getData());
                    }
                }
                //dataSave.setDataList("project", list);

                adapter.notifyDataSetChanged();
                dissmissProgressDialog();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<HomeListBean<List<ProjectBean>>>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(url, params, jsonCallBack1);
    }
}
