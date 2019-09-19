package com.heziz.liyang.ui.zhihui.sjj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.sjj.SjjStreetListAdapter;
import com.heziz.liyang.adaper.sp.SpListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.sjj.SjjDeviceNumBean;
import com.heziz.liyang.bean.sjj.SjjStreetDeviceNumBean;
import com.heziz.liyang.bean.sp.SpDevicezlxNumBean;
import com.heziz.liyang.bean.sp.SpzlxNumBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.ui.zhihui.sp.SpStreetDeviceListActivity;
import com.heziz.liyang.ui.zhihui.sp.SpStreetProjectActivity;
import com.heziz.liyang.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SjjStreetDataListActivity extends BaseActivity implements View.OnClickListener {
    //@BindView(R.id.ivIcon)
    //ImageView ivIcon;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.tvOther)
    TextView tvOther;
    @BindView(R.id.tvOnLine)
    TextView tvOnLine;
    @BindView(R.id.tvOffline)
    TextView tvOffline;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    SjjStreetListAdapter adapter;
    private List<SjjStreetDeviceNumBean> projectBeanList=new ArrayList<>();

    private UserInfor userInfor;
    Map<String,String> params1=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_street_device_list);
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
//        type=getIntent().getStringExtra("type");
        tvTitle.setText("升降机监管");
        //ivIcon.setImageResource(R.drawable.zh_sp_icon1);
        adapter=new SjjStreetListAdapter(this,projectBeanList);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(recycleView);
        adapter.setEmptyView(R.layout.empty_view);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(SjjStreetDataListActivity.this, SjjProjectListActivity.class);
                intent.putExtra("id",String.valueOf(projectBeanList.get(position).getId()));
                intent.putExtra("name",projectBeanList.get(position).getName());
//                intent.putExtra("object",projectBeanList.get(position).getManagerRoleId());
                startActivity(intent);
            }
        });
    }

    private void initDatas() {
        String urlnum = API.SJJ_STREET_NUM;
        Map<String, String> paramsnum = new HashMap<>();
        if(userInfor.getPosition().equals("1")){
            paramsnum.put("station",userInfor.getStation()+"");
        }else if(userInfor.getPosition().equals("2")){
            paramsnum.put("managerRoleIds","["+userInfor.getManagerId()+"]");
        }else if(userInfor.getPosition().equals("3")){
            paramsnum.put("station",API.STATION+"");
            paramsnum.put("createName",userInfor.getAccount()+"");
        }
        JsonCallBack1<SRequstBean<String>> jsonCallBacknum = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {

                String res=response.body().getData().replace("\\","");
                LogUtils.show(res);
                Gson gson=new Gson();
                SjjDeviceNumBean bean=gson.fromJson(res, SjjDeviceNumBean.class);
                tvOnLine.setText(bean.getOnline()+"");
                tvOffline.setText(bean.getOffline()+"");
                tvOther.setText((Integer.valueOf(bean.getTotal())-Integer.valueOf(bean.getOffline())-Integer.valueOf(bean.getOnline()))+"");
                tvTotal.setText(bean.getTotal()+"");
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(urlnum, paramsnum, jsonCallBacknum);

        if(userInfor.getPosition().equals("1")){
            params1.put("station",userInfor.getStation()+"");
        }else if(userInfor.getPosition().equals("2")){
            params1.put("managerRoleIds","["+userInfor.getManagerId()+"]");
        }
        initProjectData();
    }

    private void initProjectData() {
        showProgressDialog();
        String url1 = API.SJJ_STREET_LIST;

        JsonCallBack1<SRequstBean<List<SjjStreetDeviceNumBean>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<List<SjjStreetDeviceNumBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<SjjStreetDeviceNumBean>>> response) {
                projectBeanList.clear();
                projectBeanList.addAll(response.body().getData());
                adapter.notifyDataSetChanged();
//                showMark(projectBeanList);
                dissmissProgressDialog();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<SjjStreetDeviceNumBean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(url1, params1, jsonCallBack1);
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
