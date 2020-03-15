package com.heziz.liyang.ui.rcjc.jgfrcjc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.xmjc.rcjc.NewCheckSelectProjectListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.rcjc.CheckProbean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack2;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewCheckSelectProActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.etPro)
    EditText etPro;
    @BindView(R.id.tvCancel)
    TextView tvCancel;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    NewCheckSelectProjectListAdapter adapter;
    List<CheckProbean> list=new ArrayList<>();
    Map<String, String> params1 = new HashMap<>();
    Map<String, String> params2 = new HashMap<>();
    private int pageNow = 1;

    private UserInfor userInfor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_check_select_pro);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initViews() {
        userInfor= MyApplication.getInstance().getUserInfor();
        adapter=new NewCheckSelectProjectListAdapter(this,list);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(recycleView);
        adapter.setEmptyView(R.layout.empty_view);
    }

    private void initDatas() {
        showProgressDialog();
        initProjectDataList();
    }

    private void initListeners() {
        tvCancel.setOnClickListener(this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent();
                intent.putExtra("checkbean",list.get(position));
                setResult(100,intent);
                finish();
            }
        });
        etPro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvCancel.setText("查询");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvCancel:
                if(tvCancel.getText().toString().equals("取消")){
                    finish();
                }else{
                    list.clear();
                    String s = etPro.getText().toString();
                    params1.put("name", s);
                    showProgressDialog();
                    initProjectDataList();
                }

                break;
        }
    }

    private void initProjectDataList() {
        if(userInfor.getPosition().equals("1")){
            params1.put("station", API.STATION);
        }else if(userInfor.getPosition().equals("2")){
            params1.put("managerRoleId", userInfor.getManagerId()+"");
        }else if(userInfor.getPosition().equals("3")){
            params1.put("createName", userInfor.getName()+"");

        }
        String url1 = API.RCJC_PROJECT_LIST + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();

        JsonCallBack2<SRequstBean<List<CheckProbean>>> jsonCallBack1 = new JsonCallBack2<SRequstBean<List<CheckProbean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<CheckProbean>>> response) {
//                projectBeanList.clear();
//                projectBeanList.addAll(response.body().getData());
//

                if (response.body().getData().size()!= 0) {
                    list.addAll(response.body().getData());
                }
                adapter.notifyDataSetChanged();
                dissmissProgressDialog();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<CheckProbean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
                adapter.loadMoreFail();
            }

        };
        OkGoClient.getInstance()
                .getJsonData0(url1, params1, jsonCallBack1);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent i = new Intent();
            setResult(200, i);
            finish();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
