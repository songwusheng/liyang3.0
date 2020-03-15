package com.heziz.liyang.ui.rcjc.jgfrcjc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heziz.liyang.R;
import com.heziz.liyang.adaper.xmjc.ImagePickerAdapter;
import com.heziz.liyang.adaper.xmjc.MyGridViewAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.rcjc.CheckDetailsBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.utils.StringUtil;
import com.heziz.liyang.utils.TimeUtils;
import com.heziz.liyang.utils.ToastUtil;
import com.heziz.liyang.view.MyGridView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImagePreviewActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 监管方的日常检查列表-->>检查记录详情
 */
public class CheckDetailsctivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener, View.OnClickListener {
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    //九个模块
    @BindView(R.id.myGridView)
    MyGridView goodsGridView;
    MyGridViewAdapter mAdapter;
    //返回和标题
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    //单子的基本信息
    @BindView(R.id.tvProName)
    TextView tvProName;
    @BindView(R.id.tvJCRy)
    TextView tvJCRy;
    @BindView(R.id.tvJCTime)
    TextView tvJCTime;
    //单子的检查相关信息
    @BindView(R.id.tvJCTitle)
    TextView tvJCTitle;
    @BindView(R.id.tvJCStatus)
    TextView tvJCStatus;
    @BindView(R.id.etJCDes)
    EditText etJCDes;
    @BindView(R.id.recyclerViewJC)
    RecyclerView recyclerViewJC;
    //整改相关信息
    @BindView(R.id.llZGJG)
    LinearLayout llZGJG;
    @BindView(R.id.recyclerViewZG)
    RecyclerView recyclerViewZG;
    @BindView(R.id.etZGDes)
    EditText etZGDes;
    @BindView(R.id.tvZGRy)
    TextView tvZGRy;
    @BindView(R.id.tvZGTime)
    TextView tvZGTime;
    //复查相关信息
    @BindView(R.id.llFCJG)
    LinearLayout llFCJG;
    @BindView(R.id.llFCJG1)
    LinearLayout llFCJG1;
    @BindView(R.id.tvFCRy)
    TextView tvFCRy;
    @BindView(R.id.tvFCTime)
    TextView tvFCTime;
    @BindView(R.id.etFCDes)
    EditText etFCDes;
    @BindView(R.id.tvFCStatus)
    TextView tvFCStatus;

    //复查操作
    @BindView(R.id.llFCcz)
    LinearLayout llFCcz;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    private ImagePickerAdapter adapterJC;
    private ArrayList<ImageItem> selImageListJC = new ArrayList<>(); //当前选择的所有图片
    private ImagePickerAdapter adapterZG;
    private ArrayList<ImageItem> selImageListZG = new ArrayList<>(); //当前选择的所有图片

    private String[] goods = new String[]{"道路保洁", "裸露地面覆盖", "工地围挡", "道路硬化", "出入口冲洗", "油品管理", "工程机械", "渣土运输管理", "责任公示牌"};
    private String[] classs = new String[]{"Cleaning","Coverage","Enclosure","Hardening","Inout","Oils","Engine","Transport","Publicity"};
    //九个模块的状态列表
    private List<String> statusList = new ArrayList<>();
    private CheckDetailsBean checkDetailsBean;
    //上个界面传过来的列表类型：检查记录、通知整改、待复查
    private int type;
    //上个界面传过来的检查id
    private int id;

    Map<String,String> params1=new HashMap<>();

    //当前选中的九个分类的位置
    private int currentPosition;
    //当前登录的用户信息
    private UserInfor userInfor;
    //上个界面传过来的检查人员信息
    private String popedomName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_check_details);

        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();

    }

    private void initViews() {
        userInfor= MyApplication.getInstance().getUserInfor();
        type = getIntent().getIntExtra("type", 0);
        id = getIntent().getIntExtra("id", 0);
        popedomName = getIntent().getStringExtra("popedomName");

        tvJCRy.setText(popedomName);
        if (type == 1) {
            tvTitle.setText("检查记录记录");
        } else if (type == 2) {
            tvTitle.setText("通知整改");
        } else{
            tvTitle.setText("待复查");
        }

        //复查结果默认选中合格
        rg.check(R.id.rb1);
        //巡检图片适配器初始化
        adapterJC = new ImagePickerAdapter(this, selImageListJC, 0, 1);
        adapterJC.setOnItemClickListener(this);
        recyclerViewJC.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewJC.setHasFixedSize(true);
        recyclerViewJC.setAdapter(adapterJC);
        //整改图片适配器初始化
        adapterZG = new ImagePickerAdapter(this, selImageListZG, 0, 2);
        adapterZG.setOnItemClickListener(this);
        recyclerViewZG.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewZG.setHasFixedSize(true);
        recyclerViewZG.setAdapter(adapterZG);
        //九个分类状态初始化
        for (int i = 0; i < 9; i++) {
            statusList.add(getStatus(0));
        }
        mAdapter = new MyGridViewAdapter(mContext);
        mAdapter.setData(goods, 0, statusList);//传数组, 并指定默认值
        goodsGridView.setAdapter(mAdapter);

        showProgressDialog();
        getDetails();

    }

    private void getDetails() {
        String url1 = API.XMF_CHECK_DETAILS + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
        Map<String, String> params1 = new HashMap<>();
        params1.put("id", id + "");
        JsonCallBack1<SRequstBean<CheckDetailsBean>> jsonCallBack1 = new JsonCallBack1<SRequstBean<CheckDetailsBean>>() {
            @Override
            public void onSuccess(Response<SRequstBean<CheckDetailsBean>> response) {
                dissmissProgressDialog();
                if (response.body().getData() != null) {
                    //ToastUtil.showToast("获取详情成功");
                    checkDetailsBean = response.body().getData();
                    ////接收上个界面传过来的检查记录详情类  更新界面
                    //checkDetailsBean = (CheckDetailsBean) getIntent().getSerializableExtra("bean");
                    tvProName.setText(checkDetailsBean.getProjectName());
                    //tvJCRy.setText(checkDetailsBean.getWgName());
                    tvJCTime.setText(checkDetailsBean.getCreateTime());
                    refreshImage(checkDetailsBean.getCleaningImage());
                    refreshImage1(checkDetailsBean.getChangeCleaningImage());

                    statusList.set(0, getStatus(checkDetailsBean.getCleaningStatus()));
                    statusList.set(1, getStatus(checkDetailsBean.getCoverageStatus()));
                    statusList.set(2, getStatus(checkDetailsBean.getEnclosureStatus()));
                    statusList.set(3, getStatus(checkDetailsBean.getHardeningStatus()));
                    statusList.set(4, getStatus(checkDetailsBean.getInoutStatus()));
                    statusList.set(5, getStatus(checkDetailsBean.getOilsStatus()));
                    statusList.set(6, getStatus(checkDetailsBean.getEngineStatus()));
                    statusList.set(7, getStatus(checkDetailsBean.getTransportStatus()));
                    statusList.set(8, getStatus(checkDetailsBean.getPublicityStatus()));
                    mAdapter.setSeclection(currentPosition);
                    mAdapter.notifyDataSetChanged();

                    chengeStatus(currentPosition);

                }

            }

            @Override
            public void onError(Response<SRequstBean<CheckDetailsBean>> response) {
                super.onError(response);
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url1, params1, jsonCallBack1);
    }

    private void initDatas() {

    }

    private void initListeners() {
        btnSubmit.setOnClickListener(this);
        rlBack.setOnClickListener(this);
        goodsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                currentPosition=position;
                mAdapter.setSeclection(position);//传值更新
                mAdapter.notifyDataSetChanged();

                chengeStatus(position);

            }
        });

    }

    private void chengeStatus(int position){
        if (statusList.get(position).equals("待复查")) {
            llZGJG.setVisibility(View.VISIBLE);
            if(checkDetailsBean.getEndStatus()!=4){
                llFCJG.setVisibility(View.VISIBLE);
                llFCJG1.setVisibility(View.GONE);
                llFCcz.setVisibility(View.VISIBLE);
                etFCDes.setEnabled(true);
                etFCDes.setText("");
            }
            setDatas(position,2);
        } else if (statusList.get(position).equals("整改合格")){
            llFCJG.setVisibility(View.VISIBLE);
            llFCJG1.setVisibility(View.VISIBLE);
            llZGJG.setVisibility(View.VISIBLE);
            llFCcz.setVisibility(View.GONE);
            etFCDes.setEnabled(false);
            setDatas(position,3);
        }else{
            llFCJG.setVisibility(View.GONE);
            llFCJG1.setVisibility(View.GONE);
            llZGJG.setVisibility(View.GONE);
            llFCcz.setVisibility(View.GONE);
            setDatas(position,1);
        }
    }

    private void setDatas(int position,int type) {
        String imgs = null;
        String imgs1 = null;
        switch (position) {
            case 0:
                tvJCTitle.setText("道路保洁");
                tvJCStatus.setText(getStatus2(checkDetailsBean.getCleaningStatus()));
                etJCDes.setText(checkDetailsBean.getCleaningCommonts());
                imgs = checkDetailsBean.getCleaningImage();
                if(checkDetailsBean.getCleaningStatus()>=3){
                    imgs1 = checkDetailsBean.getChangeCleaningImage();
                    etZGDes.setText(checkDetailsBean.getChangeCleaningCommonts());
                    tvZGTime.setText(checkDetailsBean.getChangeCleaningTime());
                    //tvZGStatus.setText(getStatus(checkDetailsBean.getCleaningStatus()));
                    tvZGRy.setText(checkDetailsBean.getChangeCleaningName());
                    refreshImage1(imgs1);
                    if (checkDetailsBean.getCleaningStatus() == 4) {
                        tvFCRy.setText(checkDetailsBean.getCheckCleaningName());
                        etFCDes.setText(checkDetailsBean.getCheckCleaningCommonts());
                        tvFCTime.setText(checkDetailsBean.getCheckCleaningTime());
                        tvFCStatus.setText(getStatus(checkDetailsBean.getCleaningStatus()));
                    }
                }

                //tvZGTime.setText(checkDetailsBean.getChangeCleaningTime());

                break;
            case 1:
                tvJCTitle.setText("裸露地面覆盖");
                tvJCStatus.setText(getStatus2(checkDetailsBean.getCoverageStatus()));
                etJCDes.setText(checkDetailsBean.getCoverageCommonts());
                imgs = checkDetailsBean.getCoverageImage();
                if (checkDetailsBean.getCoverageStatus()>=3){
                    imgs1 = checkDetailsBean.getChangeCoverageImage();
                    etZGDes.setText(checkDetailsBean.getChangeCoverageCommonts());
                    tvZGTime.setText(checkDetailsBean.getChangeCoverageTime());
                    //tvZGStatus.setText(getStatus(checkDetailsBean.getCoverageStatus()));
                    tvZGRy.setText(checkDetailsBean.getChangeCoverageName());
                    refreshImage1(imgs1);
                    if (checkDetailsBean.getCoverageStatus() == 4) {
                        tvFCRy.setText(checkDetailsBean.getCheckCoverageName());
                        etFCDes.setText(checkDetailsBean.getCheckCoverageCommonts());
                        tvFCTime.setText(checkDetailsBean.getCheckCoverageTime());
                        tvFCStatus.setText(getStatus(checkDetailsBean.getCoverageStatus()));
                    }
                }
                //tvZGTime.setText(checkDetailsBean.getChangeCoverageTime());
                break;
            case 2:
                tvJCTitle.setText("工地围挡");
                tvJCStatus.setText(getStatus2(checkDetailsBean.getEnclosureStatus()));
                etJCDes.setText(checkDetailsBean.getEnclosureCommonts());
                imgs = checkDetailsBean.getEnclosureImage();
                if (checkDetailsBean.getEnclosureStatus()>=3) {
                    imgs1 = checkDetailsBean.getChangeEnclosureImage();
                    etZGDes.setText(checkDetailsBean.getChangeEnclosureCommonts());
                    tvZGTime.setText(checkDetailsBean.getChangeEnclosureTime());
                    //tvFCStatus.setText(getStatus(checkDetailsBean.getEnclosureStatus()));
                    tvZGRy.setText(checkDetailsBean.getChangeEnclosureName());
                    refreshImage1(imgs1);
                    if (checkDetailsBean.getEnclosureStatus() == 4) {
                        tvFCRy.setText(checkDetailsBean.getCheckEnclosureName());
                        etFCDes.setText(checkDetailsBean.getCheckEnclosureCommonts());
                        tvFCTime.setText(checkDetailsBean.getCheckEnclosureTime());
                        tvFCStatus.setText(getStatus(checkDetailsBean.getEnclosureStatus()));
                    }
                }
                //tvZGTime.setText(checkDetailsBean.getChangeEnclosureTime());
                break;
            case 3:
                tvJCTitle.setText("道路硬化");
                tvJCStatus.setText(getStatus2(checkDetailsBean.getHardeningStatus()));
                etJCDes.setText(checkDetailsBean.getHardeningCommonts());
                imgs = checkDetailsBean.getHardeningImage();
                if (checkDetailsBean.getHardeningStatus()>=3) {
                    imgs1 = checkDetailsBean.getChangeHardeningImage();
                    etZGDes.setText(checkDetailsBean.getChangeHardeningCommonts());
                    tvZGTime.setText(checkDetailsBean.getChangeHardeningTime());
                    //tvFCStatus.setText(getStatus(checkDetailsBean.getHardeningStatus()));
                    tvZGRy.setText(checkDetailsBean.getChangeHardeningName());
                    refreshImage1(imgs1);
                    if (checkDetailsBean.getHardeningStatus() == 4) {
                        tvFCRy.setText(checkDetailsBean.getCheckHardeningName());
                        etFCDes.setText(checkDetailsBean.getCheckHardeningCommonts());
                        tvFCTime.setText(checkDetailsBean.getCheckHardeningTime());
                        tvFCStatus.setText(getStatus(checkDetailsBean.getHardeningStatus()));
                    }
                }
                //tvZGTime.setText(checkDetailsBean.getChangeHardeningTime());
                break;
            case 4:
                tvJCTitle.setText("出入口冲洗");
                tvJCStatus.setText(getStatus2(checkDetailsBean.getInoutStatus()));
                etJCDes.setText(checkDetailsBean.getInoutCommonts());
                imgs = checkDetailsBean.getInoutImage();
                if (checkDetailsBean.getInoutStatus()>=3) {
                    imgs1 = checkDetailsBean.getChangeInoutImage();
                    etZGDes.setText(checkDetailsBean.getChangeInoutCommonts());
                    tvZGTime.setText(checkDetailsBean.getChangeInoutTime());
                    //tvFCStatus.setText(getStatus(checkDetailsBean.getInoutStatus()));
                    tvZGRy.setText(checkDetailsBean.getChangeInoutName());
                    refreshImage1(imgs1);
                    if (checkDetailsBean.getInoutStatus() == 4) {
                        tvFCRy.setText(checkDetailsBean.getCheckInoutName());
                        etFCDes.setText(checkDetailsBean.getCheckInoutCommonts());
                        tvFCTime.setText(checkDetailsBean.getCheckInoutTime());
                        tvFCStatus.setText(getStatus(checkDetailsBean.getInoutStatus()));
                    }
                }
                //tvZGTime.setText(checkDetailsBean.getChangeInoutTime());
                break;
            case 5:
                tvJCTitle.setText("油品管理");
                tvJCStatus.setText(getStatus2(checkDetailsBean.getOilsStatus()));
                etJCDes.setText(checkDetailsBean.getOilsCommonts());
                imgs = checkDetailsBean.getOilsImage();
                if (checkDetailsBean.getOilsStatus()>=3) {
                    imgs1 = checkDetailsBean.getChangeOilsImage();
                    etZGDes.setText(checkDetailsBean.getChangeOilsCommonts());
                    tvZGTime.setText(checkDetailsBean.getChangeOilsTime());
                    //tvFCStatus.setText(getStatus(checkDetailsBean.getOilsStatus()));
                    tvZGRy.setText(checkDetailsBean.getChangeOilsName());
                    refreshImage1(imgs1);
                    if (checkDetailsBean.getOilsStatus() == 4) {
                        tvFCRy.setText(checkDetailsBean.getCheckOilsName());
                        etFCDes.setText(checkDetailsBean.getCheckOilsCommonts());
                        tvFCTime.setText(checkDetailsBean.getCheckOilsTime());
                        tvFCStatus.setText(getStatus(checkDetailsBean.getOilsStatus()));
                    }
                }
                //tvZGTime.setText(checkDetailsBean.getChangeOilsTime());
                break;
            case 6:
                tvJCTitle.setText("工程机械");
                tvJCStatus.setText(getStatus2(checkDetailsBean.getEngineStatus()));
                etJCDes.setText(checkDetailsBean.getEngineCommonts());
                imgs = checkDetailsBean.getEngineImage();
                if (checkDetailsBean.getEngineStatus()>=3) {
                    imgs1 = checkDetailsBean.getChangeEngineImage();
                    etZGDes.setText(checkDetailsBean.getChangeEngineCommonts());
                    tvZGTime.setText(checkDetailsBean.getChangeEngineTime());
                    //tvFCStatus.setText(getStatus(checkDetailsBean.getEngineStatus()));
                    tvZGRy.setText(checkDetailsBean.getChangeEngineName());
                    refreshImage1(imgs1);
                    if (checkDetailsBean.getEngineStatus() == 4) {
                        tvFCRy.setText(checkDetailsBean.getCheckEngineName());
                        etFCDes.setText(checkDetailsBean.getCheckEngineCommonts());
                        tvFCTime.setText(checkDetailsBean.getCheckEngineTime());
                        tvFCStatus.setText(getStatus(checkDetailsBean.getEngineStatus()));
                    }
                }
                //tvZGTime.setText(checkDetailsBean.getChangeEngineTime());
                break;
            case 7:
                tvJCTitle.setText("渣土运输管理");
                tvJCStatus.setText(getStatus2(checkDetailsBean.getTransportStatus()));
                etJCDes.setText(checkDetailsBean.getTransportCommonts());
                imgs = checkDetailsBean.getTransportImage();
                if (checkDetailsBean.getTransportStatus()>=3) {
                    imgs1 = checkDetailsBean.getChangeTransportImage();
                    etZGDes.setText(checkDetailsBean.getChangeTransportCommonts());
                    tvZGTime.setText(checkDetailsBean.getChangeTransportTime());
                    //tvFCStatus.setText(getStatus(checkDetailsBean.getTransportStatus()));
                    tvZGRy.setText(checkDetailsBean.getChangeTransportName());
                    refreshImage1(imgs1);
                    if (checkDetailsBean.getTransportStatus() == 4) {
                        tvFCRy.setText(checkDetailsBean.getCheckTransportName());
                        etFCDes.setText(checkDetailsBean.getCheckTransportCommonts());
                        tvFCTime.setText(checkDetailsBean.getCheckTransportTime());
                        tvFCStatus.setText(getStatus(checkDetailsBean.getTransportStatus()));
                    }
                }
                //tvZGTime.setText(checkDetailsBean.getChangeTransportTime());
                break;
            case 8:
                tvJCTitle.setText("责任公示牌");
                tvJCStatus.setText(getStatus2(checkDetailsBean.getPublicityStatus()));
                etJCDes.setText(checkDetailsBean.getPublicityCommonts());
                imgs = checkDetailsBean.getPublicityImage();
                if (checkDetailsBean.getPublicityStatus()>=3) {
                    imgs1 = checkDetailsBean.getChangePublicityImage();
                    etZGDes.setText(checkDetailsBean.getChangePublicityCommonts());
                    tvZGTime.setText(checkDetailsBean.getChangePublicityTime());
                    //tvFCStatus.setText(getStatus(checkDetailsBean.getPublicityStatus()));
                    tvZGRy.setText(checkDetailsBean.getChangePublicityName());
                    refreshImage1(imgs1);
                    if (checkDetailsBean.getPublicityStatus() == 4) {
                        tvFCRy.setText(checkDetailsBean.getCheckPublicityName());
                        etFCDes.setText(checkDetailsBean.getCheckPublicityCommonts());
                        tvFCTime.setText(checkDetailsBean.getCheckPublicityTime());
                        tvFCStatus.setText(getStatus(checkDetailsBean.getPublicityStatus()));
                    }
                }
                //tvZGTime.setText(checkDetailsBean.getChangePublicityTime());
                break;
        }
        refreshImage(imgs);
    }

    private void refreshImage(String imgURL) {
        selImageListJC.clear();
        if (!StringUtil.isNull(imgURL)) {
            List<String> imgs = Arrays.asList(imgURL.split(","));
            selImageListJC.clear();
            if (imgs.size() != 0) {
                for (int i = 0; i < imgs.size(); i++) {
                    ImageItem item = new ImageItem();
                    item.setPath(imgs.get(i));
                    selImageListJC.add(item);
                }
            }
        }
        adapterJC.setImages(selImageListJC);

    }

    private void refreshImage1(String imgURL) {
        selImageListZG.clear();
        if (!StringUtil.isNull(imgURL)) {
            List<String> imgs = Arrays.asList(imgURL.split(","));

            if (imgs.size() != 0) {
                for (int i = 0; i < imgs.size(); i++) {
                    ImageItem item = new ImageItem();
                    item.setPath(imgs.get(i));
                    selImageListZG.add(item);
                }
            }
        }
        adapterZG.setImages(selImageListZG);

    }

    private String getStatus(int status) {
        String ss = "";
        switch (status) {
            case 1:
                ss = "合格";
                break;
            case 2:
                ss = "不合格";
                break;
            case 3:
                ss = "待复查";
                break;
            case 4:
                ss = "整改合格";
                break;
            default: {
                ss = "审核中";
            }

        }
        return ss;
    }
    private String getStatus2(int status) {
        String ss = "";
        switch (status) {
            case 1:
                ss = "合格";
                break;
            default: {
                ss = "不合格";
            }

        }
        return ss;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlBack:
                finish();
                break;
            case R.id.btnSubmit:
                if(!rb1.isChecked()&&etFCDes.getText().toString().equals("")){
                    ToastUtil.showToast("请填写复查描述");
                    return;
                }
                showProgressDialog();
                setFCJG();
                break;
        }
    }

    private void setFCJG() {
        Map<String,String> params2=new HashMap<>();
        params2.put("id",checkDetailsBean.getId()+"");
        params2.put("check"+classs[currentPosition]+"Account",userInfor.getName());
        params2.put("check"+classs[currentPosition]+"Commonts",etFCDes.getText().toString());
        //params2.put("change"+classs[currentPosition]+"Image",str.substring(0,str.length()-1));
        params2.put("check"+classs[currentPosition]+"Time", TimeUtils.getCurrentTime());
        params2.put(classs[currentPosition].toLowerCase()+"Status",rb1.isChecked()?"4":"2");
        String url1 = API.CHANGE_STATUS_CHECK + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
        JsonCallBack1<SRequstBean<String>> jsonCallBack1 = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(Response<SRequstBean<String>> response) {
                //ToastUtil.showToast("更改成功");
                //dissmissProgressDialog();
                //Intent intent = new Intent();
                //setResult(100, intent);
                //finish();
                getDetails();
            }

            @Override
            public void onError(Response<SRequstBean<String>> response) {
                super.onError(response);
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData10(url1, params2, jsonCallBack1);
    }



    @Override
    public void onItemClick(View view, int position, int num) {
        switch (position) {
            default:
                //打开预览
                Intent intentPreview = new Intent();
                if (num == 1) {
                    intentPreview.setClass(mContext,ImagePreviewActivity.class);
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapterJC.getImages());
                } else {
                    if(statusList.get(position).equals("整改完成")||statusList.get(currentPosition).equals("整改合格")){
                        intentPreview.setClass(mContext,ImagePreviewActivity.class);
                    }else{
                        intentPreview.setClass(mContext, ImagePreviewDelActivity.class);
                    }
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapterZG.getImages());
                }

                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }



}
