package com.heziz.liyang.ui.mine.fdl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.fdl.MineFDLListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.fdl.MineFDLBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.RequestBean;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.view.SpinnerPopuwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineFdlListActivity extends BaseActivity implements View.OnClickListener {
    private int page = 1;
    private UserInfor userInfor;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tvSx)
    TextView tvSx;
    MineFDLListAdapter adapter;
    private List<MineFDLBean> list = new ArrayList<>();

    private String type_sx;
    private SpinnerPopuwindow sxSpinnerPopuwindow;
    private List<String> sxData;

    Map<String, String> params1 = new HashMap<>();
    Map<String, String> params2 = new HashMap<>();
    MyBroadcastReceiver mBroadcastReceiver;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_fdl_list);
        ButterKnife.bind(this);

        initViews();
        initDatas1();
        initListeners();
    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        tvSx.setOnClickListener(this);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                initDatas();
            }
        });

        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("mine_sp_success");
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    private void initViews() {
        tvTitle.setText("非道路机械管理");
        userInfor = MyApplication.getInstance().getUserInfor();
        SXData();
        adapter = new MineFDLListAdapter(this, list);
        LinearLayoutManager manager = new LinearLayoutManager(this.getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(recycleView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                currentPosition=position;
                Intent intent = new Intent(mContext, MineFDLdetailsActivity.class);
                intent.putExtra("mineFdlListBean", list.get(position));
                startActivity(intent);
            }
        });
    }

    private void initDatas1() {
        params1.put("auditStatus", "");
        params1.put("name", "");
        params2.put("pageSize", "20");
        if (userInfor.getPosition().equals("1")) {
            params1.put("siteId", userInfor.getStation() + "");
        } else if (userInfor.getPosition().equals("2")) {
            params1.put("managerId", "[" + userInfor.getManagerId() + "]");
        } else if (userInfor.getPosition().equals("3")) {
            params1.put("createBy", userInfor.getAccount() + "");
        }
        showProgressDialog();
        initDatas();
    }

    private void initDatas() {
        String url2 = API.MINE_FDLJX_LIST + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
        params2.put("pageNow", page + "");
        JsonCallBack1<SRequstBean<RequestBean<List<MineFDLBean>>>> jsonCallBack2 = new JsonCallBack1<SRequstBean<RequestBean<List<MineFDLBean>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<MineFDLBean>>>> response) {
                if (response.body().getData() != null) {
//                            carDDetailsBeanList.addAll(response.body().getData().getList());
                    if (response.body().getData().getList().size() != 0) {
                        list.addAll(response.body().getData().getList());
                        adapter.loadMoreComplete();
                    } else {
                        if(page==1){
                            adapter.setEmptyView(R.layout.empty_view);
                        }else{
                            adapter.loadMoreEnd();
                        }
                    }
                } else {
                    adapter.loadMoreFail();
                }
                dissmissProgressDialog();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<MineFDLBean>>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData2(url2, params1, params2,  jsonCallBack2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlBack:
                finish();
                break;
            case R.id.btnSearch:
                go();
                String s = etName.getText().toString();
//                if(s.equals("")){
//                    ToastUtil.showToast("请输入项目名称");
//                }else{
                params1.put("name", s);
                refresh();
//                }
                break;
            case R.id.tvSx:
                type_sx = tvSx.getText().toString();
                sxSpinnerPopuwindow = new SpinnerPopuwindow(MineFdlListActivity.this, type_sx, sxData, jditemsOnClick);
                sxSpinnerPopuwindow.showPopupWindow(ll);
                sxSpinnerPopuwindow.setTitleText("状态");//给下拉列表设置标题
                break;
        }
    }

    private AdapterView.OnItemClickListener jditemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = sxData.get(sxSpinnerPopuwindow.getText());
            tvSx.setText(value);
            sxSpinnerPopuwindow.dismissPopupWindow();
//            if(userInfor.getPosition().equals("2")){
//
//            }else{
            if (position == 0) {
                params1.put("auditStatus", "");
            } else {
                params1.put("auditStatus", position - 1 + "");
            }
//            }
            refresh();
        }
    };

    private void refresh() {
        list.clear();
        page = 1;
        showProgressDialog();
        initDatas();
    }

    /**
     * 筛选数据
     */
    private void SXData() {
//        1未上传图片，2检查结束，状态正常，3不正常，4不正常，已通知项目方整改，5项目方整改完毕，检查结束
        sxData = new ArrayList<>();
        sxData.add("全部");
        sxData.add("待审核");
        sxData.add("通过");
        sxData.add("不通过");
    }

    private void go() {

        Window window;
        window = getWindow();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(window.getDecorView().getWindowToken(), 0);
    }

    class MyBroadcastReceiver extends BroadcastReceiver {
        public static final String TAG = "MyBroadcastReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("mine_sp_success")) {
                list.get(currentPosition).setAuditStatus(1);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }
}