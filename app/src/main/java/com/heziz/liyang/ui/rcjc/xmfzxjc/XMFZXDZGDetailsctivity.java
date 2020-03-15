package com.heziz.liyang.ui.rcjc.xmfzxjc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.heziz.liyang.image.ImagePreviewActivity;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack0;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.utils.GlideImageLoader;
import com.heziz.liyang.utils.StringUtil;
import com.heziz.liyang.utils.TimeUtils;
import com.heziz.liyang.utils.ToastUtil;
import com.heziz.liyang.view.SelectDialog;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目方的自查列表-->>检查记录详情
 */
public class XMFZXDZGDetailsctivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener, View.OnClickListener {
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    //检查相关
    @BindView(R.id.tvProName)
    TextView tvProName;
    @BindView(R.id.tvJCRy)
    TextView tvJCRy;
    @BindView(R.id.tvJCTime)
    TextView tvJCTime;
    @BindView(R.id.tvJCTitle)
    TextView tvJCTitle;
    @BindView(R.id.tvJCStatus)
    TextView tvJCStatus;
    @BindView(R.id.etJCDes)
    EditText etJCDes;
    @BindView(R.id.recyclerViewJC)
    RecyclerView recyclerViewJC;
    //整改相关
    @BindView(R.id.llZGJG)
    LinearLayout llZGJG;
    @BindView(R.id.llZGJG1)
    LinearLayout llZGJG1;
    @BindView(R.id.tvZGRy)
    TextView tvZGRy;
    @BindView(R.id.tvZGTime)
    TextView tvZGTime;
    @BindView(R.id.etZGDes)
    EditText etZGDes;
    @BindView(R.id.recyclerViewZG)
    RecyclerView recyclerViewZG;
    //复查相关
    @BindView(R.id.llFCJG)
    LinearLayout llFCJG;
    @BindView(R.id.tvFCRy)
    TextView tvFCRy;
    @BindView(R.id.tvFCTime)
    TextView tvFCTime;
    @BindView(R.id.tvFCStatus)
    TextView tvFCStatus;

    //复查相关
    @BindView(R.id.llFCJG2)
    LinearLayout llFCJG2;
    @BindView(R.id.tvFCRy2)
    TextView tvFCRy2;
    @BindView(R.id.tvFCTime2)
    TextView tvFCTime2;
    @BindView(R.id.etFCDes2)
    EditText etFCDes2;
    @BindView(R.id.tvFCStatus2)
    TextView tvFCStatus2;

    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList = new ArrayList<>(); //当前选择的所有图片
    private ImagePickerAdapter adapter1;
    private ArrayList<ImageItem> selImageList1 = new ArrayList<>(); //当前选择的所有图片
    private int maxImgCount = 9;               //允许选择图片最大数
    private String[] classs = new String[]{"Cleaning", "Coverage", "Enclosure", "Hardening", "Inout", "Oils", "Engine", "Transport", "Publicity"};
    private ZXCheckDetailsBean checkDetailsBean;

    private int type;

    private int id;

    private int num;

    private StringBuffer stringBuffer = new StringBuffer();

    Map<String, String> params1 = new HashMap<>();

    private int currentStatus;

    private UserInfor userInfor;

    private String checkname;
    private String changeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmf_zx_details);

        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();

    }

    private void initViews() {
        userInfor = MyApplication.getInstance().getUserInfor();
        type = getIntent().getIntExtra("type", 0);
        id = getIntent().getIntExtra("id", 0);
        checkname = getIntent().getStringExtra("checkname");

        tvJCRy.setText(checkname);
        if (type == 1) {
            tvTitle.setText("整改记录");
        } else if (type == 2) {
            tvTitle.setText("待整改");
        }
        initImagePicker();
        //巡检图片适配器初始化
        adapter = new ImagePickerAdapter(this, selImageList, 0, 1);
        adapter.setOnItemClickListener(this);
        recyclerViewJC.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewJC.setHasFixedSize(true);
        recyclerViewJC.setAdapter(adapter);

        adapter1 = new ImagePickerAdapter(this, selImageList1, 0, 2);
        adapter1.setOnItemClickListener(this);
        recyclerViewZG.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewZG.setHasFixedSize(true);
        recyclerViewZG.setAdapter(adapter1);


        getDetails();

    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        //imagePicker.setSelectedImages(selImageList);
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    private void getDetails() {
        String url1 = API.ZXCHECK_DETAILS + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
        Map<String, String> params1 = new HashMap<>();
        params1.put("id", id + "");
        JsonCallBack1<SRequstBean<ZXCheckDetailsBean>> jsonCallBack1 = new JsonCallBack1<SRequstBean<ZXCheckDetailsBean>>() {
            @Override
            public void onSuccess(Response<SRequstBean<ZXCheckDetailsBean>> response) {
                dissmissProgressDialog();
                if (response.body().getData() != null) {
                    checkDetailsBean = response.body().getData();
                    tvProName.setText(checkDetailsBean.getProjectName());
                    tvJCTime.setText(checkDetailsBean.getCreateTime());
                    tvJCStatus.setText(getStatus(checkDetailsBean.getEndStatus()));
                    tvJCTitle.setText(checkDetailsBean.getCustomEventName());
                    refreshImage(checkDetailsBean.getCustomEventImage());
                    etJCDes.setText(checkDetailsBean.getCustomEventCommonts());
                    currentStatus = checkDetailsBean.getEndStatus();
                    chengeStatus(checkDetailsBean.getEndStatus());

                }

            }

            @Override
            public void onError(Response<SRequstBean<ZXCheckDetailsBean>> response) {
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

    }

    private void chengeStatus(int position) {
        if (position == 4 || position == 7) {
            llZGJG.setVisibility(View.VISIBLE);
            llZGJG1.setVisibility(View.GONE);
            llFCJG.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.VISIBLE);
            selImageList1.clear();
            adapter1 = new ImagePickerAdapter(XMFZXDZGDetailsctivity.this, selImageList1, maxImgCount, 2);
            adapter1.setOnItemClickListener(XMFZXDZGDetailsctivity.this);
            adapter1.setImages(selImageList1);
            recyclerViewZG.setAdapter(adapter1);
            etZGDes.setEnabled(true);
            etZGDes.setText("");
            tvZGTime.setText(checkDetailsBean.getChangeEventTime());
            if(position == 7){
                llFCJG2.setVisibility(View.VISIBLE);
                tvFCRy2.setText(checkDetailsBean.getChangeName());
                tvFCTime2.setText(checkDetailsBean.getCheckTime());
                tvFCStatus2.setText(getStatus1(checkDetailsBean.getEndStatus()));
                etFCDes2.setText(checkDetailsBean.getCheckComments());
            }
        } else if (position == 6) {
            llZGJG.setVisibility(View.VISIBLE);
            llZGJG1.setVisibility(View.VISIBLE);
            llFCJG.setVisibility(View.VISIBLE);
            btnSubmit.setVisibility(View.GONE);
            adapter1 = new ImagePickerAdapter(XMFZXDZGDetailsctivity.this, selImageList1, 0, 2);
            adapter1.setOnItemClickListener(XMFZXDZGDetailsctivity.this);
            //整改内容
            tvZGRy.setText(checkDetailsBean.getChangeEvnetName());
            tvZGTime.setText(checkDetailsBean.getChangeEventTime());
            recyclerViewZG.setAdapter(adapter1);
            etZGDes.setEnabled(false);
            etZGDes.setText(checkDetailsBean.getChangeEventCommonts());
            refreshImage1(checkDetailsBean.getChangeEventImage());
            //复查内容
            tvFCRy.setText(checkDetailsBean.getChangeName());
            tvFCTime.setText(checkDetailsBean.getCheckTime());
            tvFCStatus.setText(getStatus1(checkDetailsBean.getEndStatus()));
        } else {
            llZGJG.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.GONE);
            llFCJG.setVisibility(View.GONE);
        }
    }


    private void refreshImage(String imgURL) {
        selImageList.clear();
        if (!StringUtil.isNull(imgURL)) {
            List<String> imgs = Arrays.asList(imgURL.split(","));
            selImageList.clear();
            if (imgs.size() != 0) {
                for (int i = 0; i < imgs.size(); i++) {
                    ImageItem item = new ImageItem();
                    item.setPath(imgs.get(i));
                    selImageList.add(item);
                }
            }
        }
        adapter.setImages(selImageList);

    }

    private void refreshImage1(String imgURL) {
        selImageList1.clear();
        if (!StringUtil.isNull(imgURL)) {
            List<String> imgs = Arrays.asList(imgURL.split(","));

            if (imgs.size() != 0) {
                for (int i = 0; i < imgs.size(); i++) {
                    ImageItem item = new ImageItem();
                    item.setPath(imgs.get(i));
                    selImageList1.add(item);
                }
            }
        }
        adapter1.setImages(selImageList1);

    }

    private String getStatus(int status) {
        String ss = "";
        if (status == 2) {
            ss = "合格";
        } else {
            ss = "不合格";
        }
        return ss;
    }

    private String getStatus1(int status) {
        String ss = "";
        switch (status) {
            case 1:
                ss = "未检查";
                break;
            case 2:
                ss = "合格";
                break;
            case 3:
                ss = "不合格";
                break;
            case 4:
                ss = "通知整改";
                break;
            case 5:
                ss = "整改待确认";
                break;
            case 6:
                ss = "整改通过";
                break;
            case 7:
                ss = "整改不通过";
                break;
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
                if (selImageList1.size() == 0) {
                    ToastUtil.showToast("至少上传一张图片");
                    return;
                }
                showProgressDialog();
                uploadPicture();
                break;
        }
    }

    private void setZGJG() {
        String str = stringBuffer.toString();
        params1.put("id", checkDetailsBean.getId() + "");
        params1.put("changeEvnetAccount", userInfor.getName());
        params1.put("changeEventCommonts", etZGDes.getText().toString());
        params1.put("changeEventImage", str.substring(0, str.length() - 1));
        params1.put("changeEventTime", TimeUtils.getCurrentTime());
        params1.put("endStatus", "5");
        String url1 = API.ZXCHECK_SH + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
        JsonCallBack1<SRequstBean<String>> jsonCallBack1 = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(Response<SRequstBean<String>> response) {
                //ToastUtil.showToast("更改成功");
                getName();

            }

            @Override
            public void onError(Response<SRequstBean<String>> response) {
                super.onError(response);
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData10(url1, params1, jsonCallBack1);
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void onItemClick(View view, int position, int num) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                /**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 */
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList1.size());
                                Intent intent = new Intent(XMFZXDZGDetailsctivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList1.size());
                                Intent intent1 = new Intent(XMFZXDZGDetailsctivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);
                break;
            default:
                //打开预览
                Intent intentPreview = new Intent();
                if (num == 1) {
                    intentPreview.setClass(XMFZXDZGDetailsctivity.this, ImagePreviewActivity.class);
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                } else {
                    if (currentStatus == 4) {
                        intentPreview.setClass(XMFZXDZGDetailsctivity.this, ImagePreviewDelActivity.class);
                    } else {
                        intentPreview.setClass(XMFZXDZGDetailsctivity.this, ImagePreviewActivity.class);
                    }
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter1.getImages());
                }

                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    ArrayList<ImageItem> images = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList1.addAll(images);
                    adapter1.setImages(selImageList1);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList1.clear();
                    selImageList1.addAll(images);
                    adapter1.setImages(selImageList1);
                }
            }
        }
    }

    private void uploadPicture() {
//        //上传单个文件
        String url = API.IMAGE_FILE_UPLOAD;
//    File  file = new File(file.getAbsolutePath(), file.getName());
        List<File> list1 = new ArrayList<>();
        for (int i = 0; i < selImageList1.size(); i++) {
            //list1.add(new File(selImageList.get(i).path));
            File file = new File(selImageList1.get(i).path);
            OkGo.<String>post(url)
                    .tag(this)
                    .params("file", file)
                    .isMultipart(true)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            num++;
                            ToastUtil.showToast("上传成功" + num);
                            String res = response.body();
                            String[] ress = res.split(",");
                            String a = ress[2].replace("\"", "");
                            String b = ress[1].replace("\"", "");
                            Log.e("main", "上传成功" + a + b);
                            StringBuffer string = new StringBuffer();
                            string.append(a + b);
                            string.append(",");
                            stringBuffer.append(string.toString());

                            if (num == selImageList1.size()) {
                                Log.w("main", "上传成功:" + stringBuffer.toString());
                                setZGJG();
                            }


                        }

                        @Override
                        public void onError(Response<String> response) {
//                            Log.e("main","上传失败" + response.body());
                            ToastUtil.showToast("上传失败");
                            dissmissProgressDialog();
                        }
                    });
        }

    }

    private void getName() {
        Map<String, String> params3 = new HashMap<>();
        params3.put("account", userInfor.getName());
        String url1 = API.RM + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
        JsonCallBack1<SRequstBean<String>> jsonCallBack1 = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(Response<SRequstBean<String>> response) {
                changeName = response.body().getData();
                push();
            }

            @Override
            public void onError(Response<SRequstBean<String>> response) {
                super.onError(response);
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url1, params3, jsonCallBack1);
    }

    private void push() {
        Map<String, Object> params2 = new HashMap<>();
        params2.put("account", checkDetailsBean.getStartPopedomAccount());
        params2.put("title", "复查通知");
        params2.put("context", "您有专项检查巡检待复查\n" + "整改人:" + changeName + "\n" + TimeUtils.getCurrentTime() + "\n整改时间段：" + TimeUtils.getCurrentHour() + "-" + TimeUtils.getCurrentHour1() + "\n请及时复查");
        params2.put("subtitle", "您有专项检查巡检待复查\n" + "整改人:" + changeName + "\n" + TimeUtils.getCurrentTime() + "\n整改时间段：" + TimeUtils.getCurrentHour() + "-" + TimeUtils.getCurrentHour1() + "\n请及时复查");
        params2.put("zxdfc", 1);
        String url1 = API.ZXJC_NOTICE + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
        JsonCallBack0<String> jsonCallBack1 = new JsonCallBack0<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                pushj1();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData11(url1, params2, jsonCallBack1);
    }


    private void pushj1() {
        Map<String, Object> params2 = new HashMap<>();
        params2.put("account", userInfor.getAccount());
        params2.put("zxdzg", 1);
        String url1 = API.ZXJC_NOTICE_NO + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
        JsonCallBack0<String> jsonCallBack1 = new JsonCallBack0<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                dissmissProgressDialog();
                Intent intent = new Intent();
                setResult(100, intent);
                finish();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData11(url1, params2, jsonCallBack1);
    }

}
