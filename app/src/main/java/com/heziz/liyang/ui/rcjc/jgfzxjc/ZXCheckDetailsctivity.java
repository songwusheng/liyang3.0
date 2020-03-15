package com.heziz.liyang.ui.rcjc.jgfzxjc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heziz.liyang.R;
import com.heziz.liyang.adaper.xmjc.ImagePickerAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.rcjc.jgf.ZXCheckDetailsBean;
import com.heziz.liyang.bean.rcjc.jgf.ZXCheckSHBean;
import com.heziz.liyang.image.ImagePreviewActivity;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack0;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.utils.ScreenUtils;
import com.heziz.liyang.utils.StringUtil;
import com.heziz.liyang.utils.TimeUtils;
import com.heziz.liyang.utils.ToastUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 监管方的专项检查列表-->>检查记录详情
 */
public class ZXCheckDetailsctivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener,View.OnClickListener {

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvProName)
    TextView tvProName;
    @BindView(R.id.tvRy)
    TextView tvRy;
    @BindView(R.id.tvTime)
    TextView tvTime;

    @BindView(R.id.tvJCTitle)
    TextView tvJCTitle;
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recyclerViewZG)
    RecyclerView recyclerViewZG;
    @BindView(R.id.etDes)
    EditText etDes;
    @BindView(R.id.etZGDes)
    EditText etZGDes;
    @BindView(R.id.etBHGDes)
    EditText etBHGDes;
    @BindView(R.id.llZGJG)
    LinearLayout llZGJG;
    @BindView(R.id.llFCJG)
    LinearLayout llFCJG;
    @BindView(R.id.llSH)
    LinearLayout llSH;
    @BindView(R.id.tvZG)
    TextView tvZG;
    @BindView(R.id.tvZGTime)
    TextView tvZGTime;
    @BindView(R.id.tvZG1)
    TextView tvZG1;
    @BindView(R.id.tvZGTime1)
    TextView tvZGTime1;
    @BindView(R.id.btnZGHG)
    Button btnZGHG;
    @BindView(R.id.btnBHG)
    Button btnBHG;
    @BindView(R.id.tvStatus1)
    TextView tvStatus1;

    private Dialog dialog;
    private ZXCheckSHBean zxCheckSHBean;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList=new ArrayList<>(); //当前选择的所有图片
    private ImagePickerAdapter adapter1;
    private ArrayList<ImageItem> selImageList1=new ArrayList<>(); //当前选择的所有图片
    private int maxImgCount = 0;               //允许选择图片最大数
    public static final int REQUEST_CODE_PREVIEW = 101;

    private int id;
    private int type;
    private String checkName;

    private ZXCheckDetailsBean zxCheckDetailsBean;

    private UserInfor userInfor;

    private String changeName;

    private boolean pushFlag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zx_check_details);

        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();

    }

    private void initViews() {
        userInfor=MyApplication.getInstance().getUserInfor();
        id=getIntent().getIntExtra("id",0);
        type=getIntent().getIntExtra("type",0);
        checkName=getIntent().getStringExtra("checkname");
        //检查人员
        tvRy.setText(checkName);

        if(type==1){
            tvTitle.setText("检查记录");
        }else if (type==2){
            tvTitle.setText("通知整改");
        }else if (type==3){
            tvTitle.setText("专项检查复查");
        }
        //巡检图片适配器初始化
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount,1);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter1 = new ImagePickerAdapter(this, selImageList1, maxImgCount,2);
        adapter1.setOnItemClickListener(this);
        recyclerViewZG.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewZG.setHasFixedSize(true);
        recyclerViewZG.setAdapter(adapter1);

    }

    private void initDatas() {
        String url1 = API.ZXCHECK_DETAILS+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        Map<String,String> params1=new HashMap<>();
        params1.put("id",id+"");
        JsonCallBack1<SRequstBean<ZXCheckDetailsBean>> jsonCallBack1 = new JsonCallBack1<SRequstBean<ZXCheckDetailsBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<ZXCheckDetailsBean>> response) {
                dissmissProgressDialog();
                if (response.body().getData()!=null){
                    zxCheckDetailsBean=response.body().getData();
                    int status=zxCheckDetailsBean.getEndStatus();
                    if(status==2){
                        tvStatus.setText("合格");
                    }else{
                        tvStatus.setText("不合格");
                    }
                    if(status==2||status==4||status==7){
                        llFCJG.setVisibility(View.GONE);
                        llZGJG.setVisibility(View.GONE);
                        llSH.setVisibility(View.GONE);
                    }else{
                        if(status==5){
                            llFCJG.setVisibility(View.GONE);
                            llZGJG.setVisibility(View.VISIBLE);
                            llSH.setVisibility(View.VISIBLE);
                            zxCheckSHBean=new ZXCheckSHBean(userInfor.getName(),"",TimeUtils.getCurrentTime(),6,zxCheckDetailsBean.getId());
                        }else if (status==6){
                            llFCJG.setVisibility(View.VISIBLE);
                            llZGJG.setVisibility(View.VISIBLE);
                            llSH.setVisibility(View.GONE);
                            tvZG1.setText(zxCheckDetailsBean.getChangeName());
                            tvStatus1.setText("整改合格");
                            etBHGDes.setText(zxCheckDetailsBean.getCheckComments());
                            tvZGTime1.setText(zxCheckDetailsBean.getCheckTime());
                        }

                        tvZG.setText(zxCheckDetailsBean.getChangeEvnetName());
                        tvZGTime.setText(zxCheckDetailsBean.getChangeEventTime());
                        refreshImage1(zxCheckDetailsBean.getChangeEventImage());
                        etZGDes.setEnabled(false);
                        etZGDes.setText(zxCheckDetailsBean.getChangeEventCommonts());
                    }

                    tvProName.setText(zxCheckDetailsBean.getProjectName());
                    tvJCTitle.setText(zxCheckDetailsBean.getCustomEventName());
                    //tvStatus.setText(getStatus1(zxCheckDetailsBean.getEndStatus()));
                    refreshImage(zxCheckDetailsBean.getCustomEventImage());
                    etDes.setText(zxCheckDetailsBean.getCustomEventCommonts());
                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<ZXCheckDetailsBean>> response) {
                super.onError(response);
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url1, params1,jsonCallBack1);
    }

    private void initListeners() {
        btnBHG.setOnClickListener(this);
        btnZGHG.setOnClickListener(this);
        rlBack.setOnClickListener(this);
    }

    private void refreshImage(String imgURL){
        selImageList.clear();
        if(!StringUtil.isNull(imgURL)){
            List<String> imgs= Arrays.asList(imgURL.split(","));
            selImageList.clear();
            if(imgs.size()!=0){
                for (int i=0;i<imgs.size();i++){
                    ImageItem item=new ImageItem();
                    item.setPath(imgs.get(i));
                    selImageList.add(item);
                }
            }
        }
        adapter.setImages(selImageList);

    }
    private void refreshImage1(String imgURL){
        selImageList1.clear();
        if(!StringUtil.isNull(imgURL)){
            List<String> imgs= Arrays.asList(imgURL.split(","));

            if(imgs.size()!=0){
                for (int i=0;i<imgs.size();i++){
                    ImageItem item=new ImageItem();
                    item.setPath(imgs.get(i));
                    selImageList1.add(item);
                }
            }
        }
        adapter1.setImages(selImageList1);

    }

    private String getStatus1(int status){
        String ss="";
        switch (status){
            case 1:
                ss="未检查";
                break;
            case 2:
                ss="合格";
                break;
            case 3:
                ss="不合格";
                break;
            case 4:
                ss="通知整改";
                break;
            case 5:
                ss="整改待确认";
                break;
            case 6:
                ss="整改通过";
                break;
            case 7:
                ss="整改不通过";
                break;
        }
        return ss;
    }

    @Override
    public void onItemClick(View view, int position,int num) {
//打开预览
        Intent intentPreview = new Intent(this, ImagePreviewActivity.class);
        if(num==1){
            intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
        }else{
            intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter1.getImages());
        }

        intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
        intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
        startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
            case R.id.btnBHG:
                pushFlag=true;
                showBHGDialog();
                break;
            case R.id.btnZGHG:
                pushFlag=false;
                showHGDialog();
                break;
        }
    }

    private void showHGDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext,R.style.no_border_dialog);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.alter_dialog_delet_view, null);
        Button btnUp= (Button) v.findViewById(R.id.btnOk);
        Button btnCancel= (Button) v.findViewById(R.id.btnCancle);
        TextView tvTitle2= (TextView) v.findViewById(R.id.tvTitle2);
        tvTitle2.setText("您确认整改合格吗?");
        LinearLayout llDialog= (LinearLayout) v.findViewById(R.id.llDialog);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) llDialog.getLayoutParams();
        lp.width = ScreenUtils.getScreenWidth(ZXCheckDetailsctivity.this);
        llDialog.setLayoutParams(lp);
        dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setContentView(v);

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                dialog.dismiss();
                showProgressDialog();
                setFCJG();

            }

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    private void showBHGDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext,R.style.no_border_dialog);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.alter_dialog_zx_sh_view, null);
        Button btnUp= (Button) v.findViewById(R.id.btnOk);
        Button btnCancel= (Button) v.findViewById(R.id.btnCancle);
        EditText etDes1=v.findViewById(R.id.etDes);
        LinearLayout llDialog= (LinearLayout) v.findViewById(R.id.llDialog);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) llDialog.getLayoutParams();
        lp.width = ScreenUtils.getScreenWidth(ZXCheckDetailsctivity.this);
        llDialog.setLayoutParams(lp);
        dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setContentView(v);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(etDes1.getText().toString().equals("")){
                    ToastUtil.showToast("请输入不合格描述");
                    return;
                }
                dialog.dismiss();
                showProgressDialog();
                zxCheckSHBean.setCheckComments(etDes1.getText().toString());
                zxCheckSHBean.setEndStatus(7);
                setFCJG();

            }

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void setFCJG(){
        zxCheckSHBean.setCheckTime(TimeUtils.getCurrentTime());
        String url1 = API.ZXCHECK_SH+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        JsonCallBack1<SRequstBean<String>> jsonCallBack1 = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                dissmissProgressDialog();
                if(pushFlag){
                    getName();
                }else{
                    pushj1();
                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData9(url1, zxCheckSHBean,jsonCallBack1);
    }

    private void getName() {
        Map<String,String> params3=new HashMap<>();
        params3.put("account",userInfor.getName());
        String url1 = API.RM+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        JsonCallBack1<SRequstBean<String>> jsonCallBack1 = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                changeName=response.body().getData();
                push();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url1, params3,jsonCallBack1);
    }
    private void push() {
        Map<String,Object> params2=new HashMap<>();
        params2.put("account",zxCheckDetailsBean.getChangeEvnetAccount());
        params2.put("title","整改通知");
        params2.put("context","您有专项检查巡检待整改\n"+"复查人:"+changeName+"\n"+TimeUtils.getCurrentTime()+"\n复查时间段："+TimeUtils.getCurrentHour()+"-"+TimeUtils.getCurrentHour1()+"\n请及时整改");
        params2.put("subtitle","您有专项检查巡检待整改\n"+"复查人:"+changeName+"\n"+TimeUtils.getCurrentTime()+"\n复查时间段："+TimeUtils.getCurrentHour()+"-"+TimeUtils.getCurrentHour1()+"\n请及时整改");
        params2.put("zxdzg",1);
        String url1 = API.ZXJC_NOTICE+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        JsonCallBack0<String> jsonCallBack1 = new JsonCallBack0<String>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                pushj1();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<String> response) {
                super.onError(response);
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData11(url1, params2,jsonCallBack1);
    }


    private void pushj1() {
        Map<String,Object> params2=new HashMap<>();
        params2.put("account",zxCheckDetailsBean.getStartPopedomAccount());
        params2.put("zxdfc",1);
        String url1 = API.ZXJC_NOTICE_NO+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        JsonCallBack0<String> jsonCallBack1 = new JsonCallBack0<String>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                dissmissProgressDialog();
                Intent intent = new Intent();
                setResult(100, intent);
                finish();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<String> response) {
                super.onError(response);
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData11(url1, params2,jsonCallBack1);
    }
}
