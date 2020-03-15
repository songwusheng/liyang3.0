package com.heziz.liyang.ui.rcjc.xmfzc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.xmjc.rcjc.NewCheckAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.rcjc.CheckProbean;
import com.heziz.liyang.bean.rcjc.NewCheckBean;
import com.heziz.liyang.bean.rcjc.check.BigCheckBean;
import com.heziz.liyang.bean.rcjc.check.CleaningBean;
import com.heziz.liyang.bean.rcjc.check.CoverageBean;
import com.heziz.liyang.bean.rcjc.check.EnclosureBean;
import com.heziz.liyang.bean.rcjc.check.EngineBean;
import com.heziz.liyang.bean.rcjc.check.HardeningBean;
import com.heziz.liyang.bean.rcjc.check.InoutBean;
import com.heziz.liyang.bean.rcjc.check.OilsBean;
import com.heziz.liyang.bean.rcjc.check.PublicityBean;
import com.heziz.liyang.bean.rcjc.check.TransportBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.ui.rcjc.jgfrcjc.NewCheckDetailsActivity;
import com.heziz.liyang.ui.rcjc.jgfrcjc.NewCheckSelectProActivity;
import com.heziz.liyang.utils.TimeUtils;
import com.heziz.liyang.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目方新增自查
 */
public class NewZCActivity extends BaseActivity implements View.OnClickListener {

    public static final int SELECT_PROJECT_CODE = 100;
    public static final int CLEANING_CHECk_CODE = 101;
    public static final int COVERAGE_CHECK_CODE = 102;
    public static final int ENCLOSURE_CHECK_CODE = 103;
    public static final int HARDENING_CHECK_CODE = 104;
    public static final int INOUT_CHECK_CODE = 105;
    public static final int OILS_CHECK_CODE = 106;
    public static final int ENGINE_CHECK_CODE = 107;
    public static final int PUBLICITY_CHECK_CODE = 108;
    public static final int TRANSPORT_CHECK_CODE = 109;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.tvSelectPro)
    TextView tvSelectPro;
    NewCheckAdapter adapter;
    List<NewCheckBean> list = new ArrayList<>();
    private String id = "";

    //选择项目返回的项目信息
    CheckProbean checkProbean;

    //接收每一项返回来的检查实体类
    CleaningBean cleaningBean;
    CoverageBean coverageBean;
    EnclosureBean enclosureBean;
    EngineBean engineBean;
    HardeningBean hardeningBean;
    InoutBean inoutBean;
    OilsBean oilsBean;
    PublicityBean publicityBean;
    TransportBean transportBean;

    //所有项目的大类
    BigCheckBean bigCheckBean=new BigCheckBean();
    private int endStatus;

    private UserInfor userInfor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_day_check);
        ButterKnife.bind(this);
        initViews();
        //initDatas();
        initListeners();
    }

    private void initViews() {
        userInfor=MyApplication.getInstance().getUserInfor();
        //“道路保洁、裸露地面覆盖、工地围挡、道路硬化、出入口冲洗、油品管理、工程机械、渣土运输管理、责任公示牌”
        tvTitle.setText(getIntent().getStringExtra("title"));
        list.add(new NewCheckBean("1", "道路保洁", "1"));
        list.add(new NewCheckBean("2", "裸露地面覆盖", "1"));
        list.add(new NewCheckBean("3", "工地围挡", "1"));
        list.add(new NewCheckBean("4", "道路硬化", "1"));
        list.add(new NewCheckBean("5", "出入口冲洗", "1"));
        list.add(new NewCheckBean("6", "油品达标", "1"));
        list.add(new NewCheckBean("7", "工程机械", "1"));
        list.add(new NewCheckBean("8", "渣土运输", "1"));
        list.add(new NewCheckBean("9", "责任公示牌", "1"));
        adapter = new NewCheckAdapter(this, list);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
    }

    private void initDatas() {
        String url1 = API.XMF_ZC_CHECK_NEW+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        JsonCallBack1<SRequstBean<String>> jsonCallBack1 = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                dissmissProgressDialog();
                ToastUtil.showToast("提交成功");
                finish();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData9(url1, bigCheckBean,jsonCallBack1);
    }


    private void initListeners() {
        rlBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        tvSelectPro.setOnClickListener(this);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (checkProbean != null) {
                    Intent intent = new Intent(mContext, NewCheckDetailsActivity.class);
                    intent.putExtra("title", list.get(position).getName());
                    intent.putExtra("position", position);
                    intent.putExtra("bigCheckBean", bigCheckBean);
                    switch (view.getId()) {
                        case R.id.llunCheck:
                            switch (position) {
                                case 0:
                                    startActivityForResult(intent, CLEANING_CHECk_CODE);
                                    break;
                                case 1:
                                    startActivityForResult(intent, COVERAGE_CHECK_CODE);
                                    break;
                                case 2:
                                    startActivityForResult(intent, ENCLOSURE_CHECK_CODE);
                                    break;
                                case 3:
                                    startActivityForResult(intent, HARDENING_CHECK_CODE);
                                    break;
                                case 4:
                                    startActivityForResult(intent, INOUT_CHECK_CODE);
                                    break;
                                case 5:
                                    startActivityForResult(intent, OILS_CHECK_CODE);
                                    break;
                                case 6:
                                    startActivityForResult(intent, ENGINE_CHECK_CODE);
                                    break;
                                case 7:
                                    startActivityForResult(intent, PUBLICITY_CHECK_CODE);
                                    break;
                                case 8:
                                    startActivityForResult(intent, TRANSPORT_CHECK_CODE);
                                    break;
                            }
                            break;
                        case R.id.llCheck:
                            switch (position) {
                                case 0:
                                    intent.putExtra("cleaningBean",cleaningBean);
                                    startActivityForResult(intent, CLEANING_CHECk_CODE);
                                    break;
                                case 1:
                                    intent.putExtra("coverageBean",coverageBean);
                                    startActivityForResult(intent, COVERAGE_CHECK_CODE);
                                    break;
                                case 2:
                                    intent.putExtra("enclosureBean",enclosureBean);
                                    startActivityForResult(intent, ENCLOSURE_CHECK_CODE);
                                    break;
                                case 3:
                                    intent.putExtra("hardeningBean",hardeningBean);
                                    startActivityForResult(intent, HARDENING_CHECK_CODE);
                                    break;
                                case 4:
                                    intent.putExtra("inoutBean",inoutBean);
                                    startActivityForResult(intent, INOUT_CHECK_CODE);
                                    break;
                                case 5:
                                    intent.putExtra("oilsBean",oilsBean);
                                    startActivityForResult(intent, OILS_CHECK_CODE);
                                    break;
                                case 6:
                                    intent.putExtra("engineBean",engineBean);
                                    startActivityForResult(intent, ENGINE_CHECK_CODE);
                                    break;
                                case 7:
                                    intent.putExtra("publicityBean",publicityBean);
                                    startActivityForResult(intent, PUBLICITY_CHECK_CODE);

                                    break;
                                case 8:
                                    intent.putExtra("transportBean",transportBean);
                                    startActivityForResult(intent, TRANSPORT_CHECK_CODE);
                                    break;
                            }
                            break;
                    }
                } else {
                    ToastUtil.showToast("请选择项目");
                    return;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlBack:
                finish();
                break;
            case R.id.tvSelectPro:
                Intent intent = new Intent(mContext, NewCheckSelectProActivity.class);
                startActivityForResult(intent, SELECT_PROJECT_CODE);
                break;
            case R.id.btnSubmit:
                if(cleaningBean!=null&&coverageBean!=null&&engineBean!=null&&enclosureBean!=null&&hardeningBean!=null&&inoutBean!=null&&oilsBean!=null&&publicityBean!=null&&transportBean!=null){
                    if(cleaningBean.getCleaningStatus()==1&&coverageBean.getCoverageStatus()==1&&enclosureBean.getEnclosureStatus()==1&&engineBean.getEngineStatus()==1&&hardeningBean.getHardeningStatus()==1&&inoutBean.getInoutStatus()==1&&oilsBean.getOilsStatus()==1&&publicityBean.getPublicityStatus()==1&&transportBean.getTransportStatus()==1){
                        endStatus=2;
                        //ToastUtil.showToast("全部合格");
                    }else{
                        endStatus=4;
                        //ToastUtil.showToast("不是全部合格");
                    }
                    bigCheckBean.setEndStatus(endStatus);
                    bigCheckBean.setStartTime(TimeUtils.getCurrentTime());
                    showProgressDialog();
                    initDatas();
                }else{
                    ToastUtil.showToast("请检查全部项目再提交！");
                }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==100){
            if(requestCode!=100){
                String xjjg=data.getStringExtra("xjjg");
                int picnum=data.getIntExtra("picnum",0);
                list.get(requestCode-101).setXjjg(xjjg);
                list.get(requestCode-101).setPicNum(picnum);
                list.get(requestCode-101).setStatus("2");
                bigCheckBean=(BigCheckBean) data.getSerializableExtra("bigCheckBean");
            }
            switch (requestCode) {
                case SELECT_PROJECT_CODE:
                    checkProbean = (CheckProbean) data.getSerializableExtra("checkbean");
                    tvSelectPro.setText(checkProbean.getProjectName());

                    //bigCheckBean.setChangeProjectAccount(checkProbean.getChangeProjectAccount());
                    //bigCheckBean.setManagerRoleIds(checkProbean.getManagerRoleIds());
                    //bigCheckBean.setProjectAdress(checkProbean.getProjectAdress());
                    bigCheckBean.setProjectId(checkProbean.getProjectId());
                    //bigCheckBean.setProjectName(checkProbean.getProjectName());
                    //bigCheckBean.setSiteId(checkProbean.getStation());
                    //bigCheckBean.setStartPopedomAccount(userInfor.getName());
                    //bigCheckBean.setCommitCommonts("");

                    break;
                case CLEANING_CHECk_CODE:
                    cleaningBean = (CleaningBean) data.getSerializableExtra("cleaningBean");
                    break;
                case COVERAGE_CHECK_CODE:
                    coverageBean = (CoverageBean) data.getSerializableExtra("coverageBean");
                    break;
                case ENCLOSURE_CHECK_CODE:
                    enclosureBean = (EnclosureBean) data.getSerializableExtra("enclosureBean");
                    break;
                case ENGINE_CHECK_CODE:
                    engineBean = (EngineBean) data.getSerializableExtra("engineBean");
                    break;
                case HARDENING_CHECK_CODE:
                    hardeningBean = (HardeningBean) data.getSerializableExtra("hardeningBean");
                    break;
                case INOUT_CHECK_CODE:
                    inoutBean = (InoutBean) data.getSerializableExtra("inoutBean");
                    break;
                case OILS_CHECK_CODE:
                    oilsBean = (OilsBean) data.getSerializableExtra("oilsBean");
                    break;
                case PUBLICITY_CHECK_CODE:
                    publicityBean = (PublicityBean) data.getSerializableExtra("publicityBean");
                    break;
                case TRANSPORT_CHECK_CODE:
                    transportBean = (TransportBean) data.getSerializableExtra("transportBean");
                    break;
            }
            if(requestCode!=100){
                adapter.notifyDataSetChanged();
            }

        }

    }
}
