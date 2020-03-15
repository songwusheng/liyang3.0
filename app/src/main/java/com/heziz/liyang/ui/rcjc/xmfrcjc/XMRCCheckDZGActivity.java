package com.heziz.liyang.ui.rcjc.xmfrcjc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.xmjc.rcjc.xmf.XMFCheckZGListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.JPushCommBean;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.rcjc.xmf.XMFCheckListBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.RequestBean;
import com.heziz.liyang.network.SRequstBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目方日常检查待整改
 */
public class XMRCCheckDZGActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.tb)
    TabLayout tb;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    XMFCheckZGListAdapter adapter;
    List<XMFCheckListBean> listBeans=new ArrayList<>();
    String title;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.btnNew)
    Button btnNew;
    @BindView(R.id.etName)
    EditText etName;
    Map<String,String> params1=new HashMap<>();
    Map<String,String> params2=new HashMap<>();
    Map<String,String> params3=new HashMap<>();
    private int pageNow=1;

    private UserInfor userInfor;

    private int type=1;

    private ZDYJPushReceiver zdyjPushReceiver;
    private JPushCommBean jPushCommBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xm_rcdzg_check);

        ButterKnife.bind(this);
        initViews();
        showProgressDialog();
        initDatas();
        initListeners();
    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
        btnNew.setOnClickListener(this);
        tb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                    type=tab.getPosition()+1;
                   refresh();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                type=tab.getPosition()+1;
                refresh();
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mContext,XMFRCDZGDetailsctivity.class);
                intent.putExtra("id",listBeans.get(position).getId());
                intent.putExtra("type",type);
                intent.putExtra("popedomName",listBeans.get(position).getPopedomName());
                //intent.putExtra("bean",listBeans.get(position));
                startActivityForResult(intent,100);
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNow++;
                initDatas();
            }
        });
    }

    private void initViews() {
        userInfor=MyApplication.getInstance().getUserInfor();

        tvTitle.setText("日常检查整改");

        tb.addTab(tb.newTab().setText("整改记录"));
        //tb.addTab(tb.newTab().setText("通知整改"));
        tb.addTab(tb.newTab().setText("待整改"));

        adapter=new XMFCheckZGListAdapter(this,listBeans);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(recycleView);
        adapter.setEmptyView(R.layout.empty_view);

        jPushCommBean=MyApplication.getInstance().getjPushCommBean();
        refreshJPUSH(jPushCommBean.getZg());
        zdyjPushReceiver=new ZDYJPushReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("jpush_refresh");
        registerReceiver(zdyjPushReceiver,intentFilter);

    }

    private void initDatas() {
        String url1 = API.XMF_ZC_CHECK_LIST+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        params1.put("changeAccount",userInfor.getAccount());
        params1.put("type","2");
        if(type==1){
            params1.put("endStatus","6");
            params1.remove("pass");
        }else{
            params1.remove("endStatus");
            params1.put("pass","2");
        }

        params2.put("pageNow",pageNow+"");
        params2.put("pageSize","20");
        JsonCallBack1<SRequstBean<RequestBean<List<XMFCheckListBean>>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<RequestBean<List<XMFCheckListBean>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<XMFCheckListBean>>>> response) {
                dissmissProgressDialog();
                if (response.body().getData()!=null){

//                            carDDetailsBeanList.addAll(response.body().getData().getList());
                    if(response.body().getData().getList().size()!=0){
                        listBeans.addAll(response.body().getData().getList());
                        if(listBeans.size()<20){
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
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<XMFCheckListBean>>>> response) {
                super.onError(response);
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData2(url1, params1,params2,jsonCallBack1);
    }
    private void refresh(){
        listBeans.clear();
        pageNow=1;
        showProgressDialog();
        initDatas();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
            case R.id.btnNew:
                params1.put("projectName",etName.getText().toString());
                refresh();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==100){
            if(requestCode==100){
                refresh();
            }
        }
    }

    public class ZDYJPushReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //{"badge":"0","zcdfc":"0","zg":"0","zgdsh":"0","zxdfc":"0","zxdzg":"0"}
            jPushCommBean=(JPushCommBean) intent.getSerializableExtra("jPushCommBean");
            refreshJPUSH(jPushCommBean.getZg());

        }
    }

    private void refreshJPUSH(String pushNum){
        int num=Integer.valueOf(pushNum);
        if(num<=0){
            tvNum.setVisibility(View.GONE);
        }else{
            tvNum.setText(pushNum);
            tvNum.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(zdyjPushReceiver);
    }
}
