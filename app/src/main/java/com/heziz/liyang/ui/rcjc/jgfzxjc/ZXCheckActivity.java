package com.heziz.liyang.ui.rcjc.jgfzxjc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.xmjc.zxjc.ZXCheckListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.JPushCommBean;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.rcjc.jgf.ZXCheckListBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.RequestBean;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.view.SpinnerPopuwindow1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 监管方---专项检查
 *
 */

public class ZXCheckActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.tb)
    TabLayout tb;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    ZXCheckListAdapter adapter;
    List<ZXCheckListBean> listBeans=new ArrayList<>();
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
    //分类按钮
    @BindView(R.id.tvFl)
    TextView tvFl;
    @BindView(R.id.llFL)
    LinearLayout llFL;
    /**分类数据*/
    private List<String> flData;
    private String type_fl;
    private SpinnerPopuwindow1 flSpinnerPopuwindow;

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
        setContentView(R.layout.activity_zx_check);

        ButterKnife.bind(this);
        initViews();
        showProgressDialog();
        initDatas();
        initListeners();
    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
        tvFl.setOnClickListener(this);
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
                Intent intent=new Intent(mContext,ZXCheckDetailsctivity.class);
                intent.putExtra("id",listBeans.get(position).getId());
                intent.putExtra("type",type);
                intent.putExtra("checkname",listBeans.get(position).getCheckName());
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
        GXData();

        userInfor=MyApplication.getInstance().getUserInfor();
        jPushCommBean=MyApplication.getInstance().getjPushCommBean();
        //title=getIntent().getStringExtra("title");
        tvTitle.setText("专项检查");

        tb.addTab(tb.newTab().setText("检查记录"));
        tb.addTab(tb.newTab().setText("通知整改"));
        tb.addTab(tb.newTab().setText("待复查"));

        adapter=new ZXCheckListAdapter(this,listBeans);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(recycleView);
        adapter.setEmptyView(R.layout.empty_view);

        refreshJPUSH(jPushCommBean.getZxdfc());
        zdyjPushReceiver=new ZDYJPushReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("jpush_refresh");
        registerReceiver(zdyjPushReceiver,intentFilter);

    }

    private void initDatas() {
        String url1 = API.ZXJC_LIST+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        if(userInfor.getPosition().equals("2")){
            params1.put("managerRoleIds",userInfor.getManagerId()+"");
        }else if (userInfor.getPosition().equals("1")){
            params1.put("siteId",API.STATION);
        }
        params1.put("type","2");
        if(type==1){
            params1.put("pass","1");
            params1.put("endStatus","");
        }else if (type==2){
            params1.put("endStatus","");
            params1.put("pass","2");
        }else if (type==3){
            params1.put("endStatus","5");
            params1.put("pass","");
        }

        params2.put("pageNow",pageNow+"");
        params2.put("pageSize","20");
        JsonCallBack1<SRequstBean<RequestBean<List<ZXCheckListBean>>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<RequestBean<List<ZXCheckListBean>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<ZXCheckListBean>>>> response) {
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
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<ZXCheckListBean>>>> response) {
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
                Intent intent=new Intent(mContext,NewZXCheckActivity.class);
                //if(userInfor.getPosition().equals("3")){
                    intent.putExtra("title","新增专项检查");
                //}else{
                //    intent.putExtra("title","日常检查");
                //}
                startActivity(intent);
                break;
            case R.id.ivSearch:
                params1.put("projectName",etName.getText().toString());
                refresh();
                break;
            case R.id.tvFl:
                type_fl = tvFl.getText().toString();
                flSpinnerPopuwindow = new SpinnerPopuwindow1(ZXCheckActivity.this,type_fl,flData,flitemsOnClick);
                flSpinnerPopuwindow.showPopupWindow(llFL);
                flSpinnerPopuwindow.setTitleText("分类");//给下拉列表设置标题
                break;
        }
    }

    //private void deleteList(int id,int position) {
    //    params3.put("id",id+"");
    //    params3.put("endStatus",listBeans.get(position).getEndStatus()+"");
    //    String url1 = API.ZXCHECK_DELETE + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
    //
    //    JsonCallBack2<SRequstBean<String>> jsonCallBack1 = new JsonCallBack2<SRequstBean<String>>() {
    //        @Override
    //        public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
    //            dissmissProgressDialog();
    //            recycleView.closeMenu();
    //            listBeans.remove(position);
    //            adapter.notifyDataSetChanged();
    //            ToastUtil.showToast("删除成功");
    //        }
    //
    //        @Override
    //        public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
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
    private AdapterView.OnItemClickListener flitemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = flData.get(flSpinnerPopuwindow.getText());
            tvFl.setText(value);
            flSpinnerPopuwindow.dismissPopupWindow();
            if(position==0){
                params1.put("eventName","");
                params1.put("customEventType","");
            }else{
                if(position==10){
                    params1.put("customEventType","2");
                    params1.put("eventName","");
                }else{
                    params1.put("eventName",flData.get(position));
                    params1.put("customEventType","");
                }

            }
            //initProjectData();
            refresh();
        }
    };
    /**
     * 分类数据 "道路保洁","裸露地面覆盖","工地围挡","道路硬化","出入口冲洗","油品管理","工程机械","渣土运输管理","责任公示牌"
     */
    private void GXData() {
        flData = new ArrayList<>();
        flData.add("全部分类");
        flData.add("道路保洁");
        flData.add("裸露地面覆盖");
        flData.add("工地围挡");
        flData.add("道路硬化");
        flData.add("出入口冲洗");
        flData.add("油品管理");
        flData.add("工程机械");
        flData.add("渣土运输管理");
        flData.add("责任公示牌");
        flData.add("自定义检查");
    }

    public class ZDYJPushReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //{"badge":"0","zcdfc":"0","zg":"0","zgdsh":"0","zxdfc":"0","zxdzg":"0"}
            jPushCommBean=(JPushCommBean) intent.getSerializableExtra("jPushCommBean");
            refreshJPUSH(jPushCommBean.getZxdfc());

        }
    }

    private void refreshJPUSH(String pushNum){
        if(pushNum.equals("0")){
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
