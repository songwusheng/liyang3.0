package com.heziz.liyang.ui.zhihui.fdl;

import android.content.Context;
import android.content.Intent;
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
import com.heziz.liyang.adaper.fdl.FdlStreetListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.ProjectBean;
import com.heziz.liyang.bean.StreetBean;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.fdl.FdlStreetListBean;
import com.heziz.liyang.bean.zh.FDLBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.utils.NumberUtils;
import com.heziz.liyang.view.SpinnerPopuwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FDLProjectListActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tvProjectName)
    TextView tvProjectName;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.tvOnLine)
    TextView tvOnLine;
    @BindView(R.id.tvOffline)
    TextView tvOffline;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.llSX)
    LinearLayout llSX;
    @BindView(R.id.llgx)
    LinearLayout llgx;
    @BindView(R.id.tvgx)
    TextView tvgx;
    @BindView(R.id.lljd)
    LinearLayout lljd;
    @BindView(R.id.tvjd)
    TextView tvjd;
    @BindView(R.id.lllx)
    LinearLayout lllx;
    @BindView(R.id.tvlx)
    TextView tvlx;
    @BindView(R.id.llsx)
    LinearLayout llsx;
    @BindView(R.id.tvsx)
    TextView tvsx;
    /**
     * 管辖级别数据
     */
    private List<String> gxData;
    /**
     * 街道数据
     */
    private List<StreetBean> streetBeanList = new ArrayList<>();
    private List<String> jdData;
    /**
     * 项目类型数据
     */
    private List<String> lxData;
    /**
     * 项目属性数据
     */
    private List<String> sxData;
    private String type_gx;
    private String type_jd;
    private String type_lx;
    private String type_sx;
    FdlStreetListAdapter adapter;
    private List<FdlStreetListBean> fdlStreetListBeanList = new ArrayList<>();
    private SpinnerPopuwindow gxSpinnerPopuwindow;
    private SpinnerPopuwindow jdSpinnerPopuwindow;
    private SpinnerPopuwindow lxSpinnerPopuwindow;
    private SpinnerPopuwindow sxSpinnerPopuwindow;
    Map<String, String> params1 = new HashMap<>();
    private UserInfor userInfor;
    private String position;
    private String managerId;
    private String jd;
    private StringBuffer projectIds = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdlproject_list);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initViews() {
        tvTitle.setText("非道路机械管理");
        tvProjectName.setText("项目名称");
        managerId = getIntent().getStringExtra("managerId");
        userInfor = MyApplication.getInstance().getUserInfor();
        position = userInfor.getPosition();
        if (!position.equals("3")) {
            jd = getIntent().getStringExtra("jd");
            tvjd.setText(jd);
        }
        adapter = new FdlStreetListAdapter(FDLProjectListActivity.this, fdlStreetListBeanList);
        LinearLayoutManager manager = new LinearLayoutManager(FDLProjectListActivity.this.getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.empty_view);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(FDLProjectListActivity.this, FDLProjectDetailListActivity.class);
                intent.putExtra("projectId", String.valueOf(fdlStreetListBeanList.get(position).getId()));
                intent.putExtra("name", fdlStreetListBeanList.get(position).getName());
                startActivity(intent);
            }
        });

        GXData();
        LXData();
        SXData();
    }

    private void initListeners() {
        btnSearch.setOnClickListener(this);
        llgx.setOnClickListener(this);
        if (position.equals("3")) {
            lljd.setOnClickListener(this);
        }
        lllx.setOnClickListener(this);
        llsx.setOnClickListener(this);
        rlBack.setOnClickListener(this);
    }

    private void initDatas() {
        showProgressDialog();
//      获取上边三个数据
        Map<String, String> params2 = new HashMap<>();
        if (position.equals("1") || position.equals("2")) {
            params2.put("managerId", managerId);
            getDLData(params2);
        } else if (position.equals("3")) {

            getIDS();
        }
//      获取街道数据
        String url = API.STREET_LIST;
        Map<String, String> params = new HashMap<>();
        JsonCallBack1<SRequstBean<List<StreetBean>>> jsonCallBack = new JsonCallBack1<SRequstBean<List<StreetBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<StreetBean>>> response) {
                List<StreetBean> list = response.body().getData();
                if (userInfor.getPosition().equals("2")) {
                    for (int i = 0; i < list.size(); i++) {
                        if (String.valueOf(list.get(i).getId()).equals(userInfor.getManagerId())) {
                            streetBeanList.add(list.get(i));
                        }
                    }
                } else {
                    streetBeanList.addAll(response.body().getData());
                }
                StreetData();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<StreetBean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url, params, jsonCallBack);


        params1.put("name", "");
        params1.put("gxjb", "");
        params1.put("pType", "");
        params1.put("diff", "");
        if (userInfor.getPosition().equals("3")) {
            params1.put("account", userInfor.getAccount());
        } else if (userInfor.getPosition().equals("2") || userInfor.getPosition().equals("1")) {
            params1.put("areaId", managerId);
        }
        initProjectData();

    }

    private void initProjectData() {

        String url1 = API.FDL_PROJECT_LIST + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();

        JsonCallBack1<SRequstBean<List<FdlStreetListBean>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<List<FdlStreetListBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<FdlStreetListBean>>> response) {
                fdlStreetListBeanList.clear();
                fdlStreetListBeanList.addAll(response.body().getData());
                adapter.notifyDataSetChanged();
                dissmissProgressDialog();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<FdlStreetListBean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData0(url1, params1, jsonCallBack1);
    }

    private void getDLData(Map<String, String> params2) {
        String url1 = API.ZH_DATA2 + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
        JsonCallBack1<SRequstBean<FDLBean>> jsonCallBack1 = new JsonCallBack1<SRequstBean<FDLBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<FDLBean>> response) {
                dissmissProgressDialog();
                if (response.body().getData() != null) {
                    FDLBean fdlBean = response.body().getData();
                    if (fdlBean.getTotalRecord() != 0) {
                        tvTotal.setText(NumberUtils.getTwoDecimal(fdlBean.getQualifiedNumber() * 100.0 / fdlBean.getTotalRecord()) + "%");
                    } else {
                        tvTotal.setText("0%");
                    }

                    tvOnLine.setText(fdlBean.getTotalRecord() + "");
                    tvOffline.setText(fdlBean.getTotalAmount() + "");
                } else {
                    tvTotal.setText("0");
                    tvOnLine.setText("0");
                    tvOffline.setText("0");
                }
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<FDLBean>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url1, params2, jsonCallBack1);
    }

    private void getIDS() {
        String url1 = API.PROJECT_LIST + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
        Map<String, String> params = new HashMap<>();
        params.put("station", API.STATION);
        params.put("createName", userInfor.getAccount());
        JsonCallBack1<SRequstBean<List<ProjectBean>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<List<ProjectBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<ProjectBean>>> response) {

                List<ProjectBean> list = response.body().getData();
                for (int i = 0; i < list.size(); i++) {
                    projectIds.append(list.get(i).getId() + "");
                    if (i != list.size() - 1) {
                        projectIds.append(",");
                    }
                }
                Map<String, String> params2 = new HashMap<>();
                params2.put("projectIds", projectIds.toString());
                getDLData(params2);

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<ProjectBean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(url1, params, jsonCallBack1);
    }

    private void go() {

        Window window;
        window = getWindow();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(window.getDecorView().getWindowToken(), 0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llgx:
                type_gx = tvgx.getText().toString();
                gxSpinnerPopuwindow = new SpinnerPopuwindow(FDLProjectListActivity.this, type_gx, gxData, gxitemsOnClick);
                gxSpinnerPopuwindow.showPopupWindow(llSX);
                gxSpinnerPopuwindow.setTitleText("管辖级别");//给下拉列表设置标题
                break;
            case R.id.lljd:
                type_jd = tvjd.getText().toString();
                jdSpinnerPopuwindow = new SpinnerPopuwindow(FDLProjectListActivity.this, type_jd, jdData, jditemsOnClick);
                jdSpinnerPopuwindow.showPopupWindow(llSX);
                jdSpinnerPopuwindow.setTitleText("街道");//给下拉列表设置标题
                break;
            case R.id.lllx:
                type_lx = tvlx.getText().toString();
                lxSpinnerPopuwindow = new SpinnerPopuwindow(FDLProjectListActivity.this, type_lx, lxData, lxitemsOnClick);
                lxSpinnerPopuwindow.showPopupWindow(llSX);
                lxSpinnerPopuwindow.setTitleText("项目类型");//给下拉列表设置标题
                break;
            case R.id.llsx:
                type_sx = tvsx.getText().toString();
                sxSpinnerPopuwindow = new SpinnerPopuwindow(FDLProjectListActivity.this, type_sx, sxData, sxitemsOnClick);
                sxSpinnerPopuwindow.showPopupWindow(llSX);
                sxSpinnerPopuwindow.setTitleText("项目属性");//给下拉列表设置标题
                break;
            case R.id.btnSearch:
                go();
                String s = etName.getText().toString();
                params1.put("name", s);
                initProjectData();
                break;
            case R.id.rlBack:
                finish();
                break;
        }
    }

    private AdapterView.OnItemClickListener gxitemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = gxData.get(gxSpinnerPopuwindow.getText());
            tvgx.setText(value);
            gxSpinnerPopuwindow.dismissPopupWindow();
            if (position == 0) {
                params1.put("gxjb", "");
            } else {
                params1.put("gxjb", position + "");
            }
            initProjectData();
        }
    };
    private AdapterView.OnItemClickListener jditemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = jdData.get(jdSpinnerPopuwindow.getText());
            tvjd.setText(value);
            jdSpinnerPopuwindow.dismissPopupWindow();
            if (userInfor.getPosition().equals("2")) {

            } else {
                if (position == 0) {
                    params1.put("areaId", "");
                } else {
                    params1.put("areaId", streetBeanList.get(position - 1).getId() + "");
                }
            }

            initProjectData();
        }
    };
    private AdapterView.OnItemClickListener lxitemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = lxData.get(lxSpinnerPopuwindow.getText());
            tvlx.setText(value);
            lxSpinnerPopuwindow.dismissPopupWindow();
            if (position == 0) {
                params1.put("pType", "");
            } else {
                params1.put("pType", position + "");
            }
            initProjectData();
        }
    };
    private AdapterView.OnItemClickListener sxitemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = sxData.get(sxSpinnerPopuwindow.getText());
            tvsx.setText(value);
            sxSpinnerPopuwindow.dismissPopupWindow();
            if (position == 0) {
                params1.put("diff", "");
                tvsx.setText("项目属性");
            } else if (position == 1) {
                params1.put("diff", position + "");
                tvsx.setText(value);
            } else if (position == 2) {
                params1.put("diff", position + "");
                tvsx.setText(value);
            } else if (position == 3) {
                params1.put("diff", "1,2");
                tvsx.setText(value);
            } else if (position == 4) {
                params1.put("diff", "0");
                tvsx.setText(value);
            }
            initProjectData();
        }
    };

    /**
     * 管辖级别数据
     */
    private void GXData() {
        gxData = new ArrayList<>();
        gxData.add("全部");
        gxData.add("市直管");
        gxData.add("区直管");
    }

    /**
     * 街道数据
     */
    private void StreetData() {
        jdData = new ArrayList<>();
        jdData.add("全部");
        for (int i = 0; i < streetBeanList.size(); i++) {
            jdData.add(streetBeanList.get(i).getName());
        }

    }

    /**
     * 街道数据
     */
    private void LXData() {
        lxData = new ArrayList<>();
        lxData.add("全部");
        lxData.add("房建");
        lxData.add("市政");
        lxData.add("交通");
        lxData.add("轨道");
        lxData.add("水务");
        lxData.add("园林");
        lxData.add("其他");
    }

    /**
     * 项目属性数据
     */
    private void SXData() {
        sxData = new ArrayList<>();
        sxData.add("全部");
        sxData.add("信息化工地");
        sxData.add("智慧工地");
        sxData.add("智慧&信息化工地");
        sxData.add("未申报智慧工地");
    }
}
