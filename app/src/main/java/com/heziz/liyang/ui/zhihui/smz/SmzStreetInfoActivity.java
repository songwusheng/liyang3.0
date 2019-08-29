package com.heziz.liyang.ui.zhihui.smz;

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
import com.heziz.liyang.adaper.td.TdListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.td.TdStreetDataBean;
import com.heziz.liyang.bean.zh.TdDataBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SmzStreetInfoActivity extends BaseActivity {
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
    private UserInfor userInfor;
    Map<String,String> params1=new HashMap<>();
    TdListAdapter adapter;
    private List<TdStreetDataBean> projectBeanList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smz_street_info);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initViews() {
        tvTitle.setText("实名制考勤");
        ivIcon.setImageResource(R.drawable.zh_smz_icon);
        userInfor= MyApplication.getInstance().getUserInfor();

        adapter=new TdListAdapter(this,projectBeanList);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
    }

    private void initDatas() {
        getKqNum();
        if(userInfor.getPosition().equals("1")){
            params1.put("siteid",userInfor.getStation()+"");
        }else if(userInfor.getPosition().equals("2")){
            params1.put("managerRoleIds","["+userInfor.getManagerId()+"]");
        }
        initProjectData();
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
                Intent intent=new Intent(SmzStreetInfoActivity.this, SmzProjectListActivity.class);
                intent.putExtra("id",projectBeanList.get(position).getJdId());
                intent.putExtra("name",projectBeanList.get(position).getJdName());
////                intent.putExtra("object",projectBeanList.get(position).getManagerRoleId());
                startActivity(intent);
            }
        });
    }

    private void getKqNum() {
        String urlnum = API.ZH_DATA4+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        Map<String, String> paramsnum = new HashMap<>();
        if(userInfor.getPosition().equals("1")){
            paramsnum.put("siteid",userInfor.getStation()+"");
        }else if(userInfor.getPosition().equals("2")){
            paramsnum.put("managerRoleIds","["+userInfor.getManagerId()+"]");
        }else if(userInfor.getPosition().equals("3")){
            paramsnum.put("projectAcc",userInfor.getAccount()+"");
        }
        JsonCallBack1<SRequstBean<TdDataBean>> jsonCallBacknum = new JsonCallBack1<SRequstBean<TdDataBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<TdDataBean>> response) {

                if(response.body().getData()!=null){
                    TdDataBean bean=response.body().getData();
                    tvOnLine.setText(bean.getOnline()+"");
                    tvOffline.setText(bean.getOffline()+"");
                    tvTotal.setText(bean.getCount()+"");
                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<TdDataBean>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(urlnum, paramsnum, jsonCallBacknum);
    }

    private void initProjectData() {
        showProgressDialog();
        String url1 = API.SMZ_STREET_LIST+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();

        JsonCallBack1<SRequstBean<List<TdStreetDataBean>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<List<TdStreetDataBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<TdStreetDataBean>>> response) {
                projectBeanList.clear();
                projectBeanList.addAll(response.body().getData());
                adapter.notifyDataSetChanged();
//                showMark(projectBeanList);
                dissmissProgressDialog();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<TdStreetDataBean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(url1, params1, jsonCallBack1);
    }
}
