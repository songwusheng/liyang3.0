package com.heziz.liyang.ui.rcjc.jgfzxjc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heziz.liyang.R;
import com.heziz.liyang.adaper.xmjc.ImagePickerAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.rcjc.CheckProbean;
import com.heziz.liyang.bean.rcjc.jgf.NewZXCheckBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack0;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.ui.rcjc.jgfrcjc.NewCheckSelectProActivity;
import com.heziz.liyang.utils.GlideImageLoader;
import com.heziz.liyang.utils.ScreenUtils;
import com.heziz.liyang.utils.TimeUtils;
import com.heziz.liyang.utils.ToastUtil;
import com.heziz.liyang.view.SelectDialog;
import com.heziz.liyang.view.SpinnerPopuwindow;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewZXCheckActivity extends BaseActivity implements View.OnClickListener,ImagePickerAdapter.OnRecyclerViewItemClickListener {
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    public static final int SELECT_PROJECT_CODE = 102;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList=new ArrayList<>(); //当前选择的所有图片
    private int maxImgCount = 9;               //允许选择图片最大数

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.tvSelectPro)
    TextView tvSelectPro;
    @BindView(R.id.tvSelectType)
    TextView tvSelectType;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.btnSure)
    Button btnSure;
    @BindView(R.id.etDes)
    EditText etDes;
    private UserInfor userInfor;

    //选择项目返回的项目信息
    CheckProbean checkProbean;

    NewZXCheckBean newZXCheckBean=new NewZXCheckBean();
    private int num;

    private StringBuffer stringBuffer=new StringBuffer();

    private int status=1;
    //自定义巡检类型对话框
    private Dialog dialog;
    /**分类数据*/
    private List<String> flData;
    private String type_fl;
    private SpinnerPopuwindow flSpinnerPopuwindow;

    private String checkName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_zxcheck);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initViews() {
        GXData();

        userInfor= MyApplication.getInstance().getUserInfor();
        rg.check(R.id.rb1);
        tvTitle.setText(getIntent().getStringExtra("title"));

        newZXCheckBean.setEndStatus("2");
        initImagePicker();
        initWidget();
    }

    private void initDatas() {

    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
        tvSelectPro.setOnClickListener(this);
        tvSelectType.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        newZXCheckBean.setEndStatus("2");
                        status=1;
                        break;
                    case R.id.rb2:
                        newZXCheckBean.setEndStatus("4");
                        status=2;
                        break;
                }
            }
        });
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

    private void initWidget() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount,1);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter.setImages(selImageList);

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
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(NewZXCheckActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(NewZXCheckActivity.this, ImageGridActivity.class);
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
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
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
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }else if (resultCode==100){
                checkProbean = (CheckProbean) data.getSerializableExtra("checkbean");
                tvSelectPro.setText(checkProbean.getProjectName());
                newZXCheckBean.setProjectId(checkProbean.getProjectId());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
            case R.id.btnSure:
                if (checkProbean!=null){
                    if(newZXCheckBean.getCustomEventName()!=null){
                        if(selImageList.size()==0){
                            ToastUtil.showToast("至少上传一张图片");
                            return;
                        }else{
                            showProgressDialog();
                            uploadPicture();
                        }
                    }else{
                        ToastUtil.showToast("请选择巡检类型！");
                        return;
                    }

                }else{
                    ToastUtil.showToast("请选择项目！");
                }

                break;
            case R.id.tvSelectPro:
                Intent intent = new Intent(mContext, NewCheckSelectProActivity.class);
                startActivityForResult(intent, SELECT_PROJECT_CODE);
                break;
            case R.id.tvSelectType:
                type_fl = tvSelectType.getText().toString();
                flSpinnerPopuwindow = new SpinnerPopuwindow(NewZXCheckActivity.this,type_fl,flData,flitemsOnClick);
                flSpinnerPopuwindow.showPopupWindow(tvSelectType);
                flSpinnerPopuwindow.setTitleText("巡检类型");//给下拉列表设置标题
                break;
        }
    }

    private void uploadPicture() {
//        //上传单个文件
        String url = API.IMAGE_FILE_UPLOAD;
//    File  file = new File(file.getAbsolutePath(), file.getName());
        List<File> list1=new ArrayList<>();
        for(int i=0;i<selImageList.size();i++){
            //list1.add(new File(selImageList.get(i).path));
            File file=new File(selImageList.get(i).path);
            OkGo.<String>post(url)
                    .tag(this)
                    .params("file", file)
                    .isMultipart(true)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            num++;
                            ToastUtil.showToast("上传成功"+num);
                            String res=response.body();
                            String[] ress=res.split(",");
                            String a=ress[2].replace("\"","");
                            String b=ress[1].replace("\"","");
                            Log.e("main","上传成功" + a+b);
                            StringBuffer string=new StringBuffer();
                            string.append(a+b);
                            string.append(",");
                            stringBuffer.append(string.toString());

                            if(num==selImageList.size()){
                                Log.w("main","上传成功:" + stringBuffer.toString());
                                setData();
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

    private void setData() {
        String str=stringBuffer.toString();
        newZXCheckBean.setCustomEventImage(str.substring(0,str.length()-1));
        newZXCheckBean.setCustomEventCommonts(etDes.getText().toString());
        newZXCheckBean.setStartPopedomAccount(userInfor.getName());
        newZXCheckBean.setCustomEventTime(TimeUtils.getCurrentTime());
        String url1 = API.NEW_ZXCHECK+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        JsonCallBack1<SRequstBean<String>> jsonCallBack1 = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(Response<SRequstBean<String>> response) {
                //dissmissProgressDialog();
                //finish();
                if(status==2){
                    getName();
                }else{
                    dissmissProgressDialog();
                    finish();
                }

            }

            @Override
            public void onError(Response<SRequstBean<String>> response) {
                super.onError(response);
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData9(url1, newZXCheckBean,jsonCallBack1);
    }
    private void getName() {
        Map<String,String> params3=new HashMap<>();
        params3.put("account",userInfor.getName());
        String url1 = API.RM+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        JsonCallBack1<SRequstBean<String>> jsonCallBack1 = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(Response<SRequstBean<String>> response) {
                checkName=response.body().getData();
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
                .getJsonData1(url1, params3,jsonCallBack1);
    }
    private void push() {
        Map<String,Object> params2=new HashMap<>();
        params2.put("account",checkProbean.getCreateName());
        params2.put("title","整改通知");
        params2.put("context","您有专项检查巡检待整改\n"+"巡检人:"+checkName+"\n"+TimeUtils.getCurrentTime()+"\n巡检时间段："+TimeUtils.getCurrentHour()+"-"+TimeUtils.getCurrentHour1()+"\n请及时更改");
        params2.put("subtitle","您有专项检查巡检待整改\n"+"巡检人:"+checkName+"\n"+TimeUtils.getCurrentTime()+"\n巡检时间段："+TimeUtils.getCurrentHour()+"-"+TimeUtils.getCurrentHour1()+"\n请及时更改");
        params2.put("zxdzg",1);
        String url1 = API.ZXJC_NOTICE+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        JsonCallBack0<String> jsonCallBack1 = new JsonCallBack0<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                dissmissProgressDialog();
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
                .postJsonData11(url1, params2,jsonCallBack1);
    }
    private AdapterView.OnItemClickListener flitemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = flData.get(flSpinnerPopuwindow.getText());
            flSpinnerPopuwindow.dismissPopupWindow();
            if(position!=9){
                tvSelectType.setText(value);
                newZXCheckBean.setCustomEventName(flData.get(position));
                newZXCheckBean.setCustomEventType("1");
            }else{
                newZXCheckBean.setCustomEventType("2");

                showZDYDialog();
            }
        }
    };
    /**
     * 分类数据 "道路保洁","裸露地面覆盖","工地围挡","道路硬化","出入口冲洗","油品管理","工程机械","渣土运输管理","责任公示牌"
     */
    private void GXData() {
        flData = new ArrayList<>();
        flData.add("道路保洁");
        flData.add("裸露地面覆盖");
        flData.add("工地围挡");
        flData.add("道路硬化");
        flData.add("出入口冲洗");
        flData.add("油品管理");
        flData.add("工程机械");
        flData.add("渣土运输管理");
        flData.add("责任公示牌");
        flData.add("自定义类型");
    }

    private void showZDYDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext,R.style.no_border_dialog);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.alter_dialog_zx_sh_view, null);
        Button btnUp= (Button) v.findViewById(R.id.btnOk);
        Button btnCancel= (Button) v.findViewById(R.id.btnCancle);
        EditText etDes1=v.findViewById(R.id.etDes);
        etDes1.setHint("请输入自定义巡检类型");
        LinearLayout llDialog= (LinearLayout) v.findViewById(R.id.llDialog);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) llDialog.getLayoutParams();
        lp.width = ScreenUtils.getScreenWidth(NewZXCheckActivity.this);
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
                    ToastUtil.showToast("请输入自定义巡检类型");
                    return;
                }
                tvSelectType.setText(etDes1.getText().toString());
                newZXCheckBean.setCustomEventName(etDes1.getText().toString());
                dialog.dismiss();

            }

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}


