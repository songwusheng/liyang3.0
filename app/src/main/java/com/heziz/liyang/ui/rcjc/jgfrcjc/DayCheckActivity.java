package com.heziz.liyang.ui.rcjc.jgfrcjc;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.xmjc.rcjc.JGCheckListAdapter;
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

public class DayCheckActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.tb)
    TabLayout tb;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    JGCheckListAdapter adapter;
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
    @BindView(R.id.ivSearch)
    ImageView ivSearch;

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
        setContentView(R.layout.activity_day_check);

        ButterKnife.bind(this);
        initViews();
        showProgressDialog();
        initDatas();
        initListeners();
    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
        btnNew.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
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
        //adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
        //    @Override
        //    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        //        switch (view.getId()){
        //            case R.id.tv_delete:
        //                showProgressDialog();
        //                deleteList(listBeans.get(position).getId(),position);
        //
        //                break;
        //        }
        //    }
        //});

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mContext,CheckDetailsctivity.class);
                intent.putExtra("type",type);
                intent.putExtra("id",listBeans.get(position).getId());
                intent.putExtra("popedomName",listBeans.get(position).getPopedomName());
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
        jPushCommBean=MyApplication.getInstance().getjPushCommBean();
        title=getIntent().getStringExtra("title");
        tvTitle.setText(title);

        tb.addTab(tb.newTab().setText("检查记录"));
        tb.addTab(tb.newTab().setText("通知整改"));
        tb.addTab(tb.newTab().setText("待复查"));

        adapter=new JGCheckListAdapter(this,listBeans);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(recycleView);
        adapter.setEmptyView(R.layout.empty_view);

        refreshJPUSH(jPushCommBean.getZgdsh());
        zdyjPushReceiver=new ZDYJPushReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("jpush_refresh");
        registerReceiver(zdyjPushReceiver,intentFilter);

    }

    private void initDatas() {
        String url1 = API.XMF_ZC_CHECK_LIST+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        params1.put("changeAccount",userInfor.getAccount());
        params1.put("type","2");
        if(userInfor.getPosition().equals("1")){
            params1.put("siteId",API.STATION);
        }else if(userInfor.getPosition().equals("2")){
            params1.put("managerRoleIds",userInfor.getManagerId()+"");
        }
        if(type==1){
            params1.put("pass","1");
            params1.remove("endStatus");
        }else if(type==2){
            params1.remove("endStatus");
            params1.put("pass","2");
        }else{
            params1.put("endStatus","5");
            params1.remove("pass");
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
                Intent intent=new Intent(mContext,NewDayCheckActivity.class);
                //if(userInfor.getPosition().equals("3")){
                    intent.putExtra("title","新增日常检查");
                //}else{
                //    intent.putExtra("title","日常检查");
                //}
                startActivity(intent);
                break;
            case R.id.ivSearch:
                params1.put("projectName",etName.getText().toString());
                refresh();
                break;
        }
    }

    //private void deleteList(int id,int position) {
    //    params3.put("ids",id+"");
    //    String url1 = API.RCJC_DELETE_CHECK + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
    //
    //    JsonCallBack2<SRequstBean<DeleteCheckBean>> jsonCallBack1 = new JsonCallBack2<SRequstBean<DeleteCheckBean>>() {
    //        @Override
    //        public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<DeleteCheckBean>> response) {
    //            dissmissProgressDialog();
    //            recycleView.closeMenu();
    //            listBeans.remove(position);
    //            adapter.notifyDataSetChanged();
    //            ToastUtil.showToast("删除成功");
    //        }
    //
    //        @Override
    //        public void onError(com.lzy.okgo.model.Response<SRequstBean<DeleteCheckBean>> response) {
    //            super.onError(response);
    //            dissmissProgressDialog();
    //            ToastUtil.showToast("删除失败");
    //        }
    //
    //    };
    //    OkGoClient.getInstance()
    //            .getJsonData0(url1, params3, jsonCallBack1);
    //}

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

            refreshJPUSH(jPushCommBean.getZgdsh());

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
