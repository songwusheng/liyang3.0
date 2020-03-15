package com.heziz.liyang.ui.rcjc.jgfrcjc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heziz.liyang.R;
import com.heziz.liyang.adaper.xmjc.ImagePickerAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.UserInfor;
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
import com.heziz.liyang.utils.GlideImageLoader;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewCheckDetailsActivity extends BaseActivity  implements ImagePickerAdapter.OnRecyclerViewItemClickListener ,View.OnClickListener{

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList=new ArrayList<>(); //当前选择的所有图片
    private int maxImgCount = 9;               //允许选择图片最大数

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

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

    int position;

    private int num;

    private StringBuffer stringBuffer=new StringBuffer();

    private int status=1;
    private String xjjg="合格";

    private UserInfor userInfor;

    private BigCheckBean bigCheckBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_details);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initViews() {
        userInfor= MyApplication.getInstance().getUserInfor();
        rg.check(R.id.rb1);
        tvTitle.setText(getIntent().getStringExtra("title"));
        position=getIntent().getIntExtra("position",0);
        bigCheckBean=(BigCheckBean) getIntent().getSerializableExtra("bigCheckBean");
        switch (position){
            case 0:
                CleaningBean cleaningBean=(CleaningBean)getIntent().getSerializableExtra("cleaningBean");
                if(cleaningBean!=null){
                    selImageList.addAll(cleaningBean.getSelImageList());
                    etDes.setText(cleaningBean.getCleaningCommonts());
                   refreshRG(cleaningBean.getCleaningStatus());
                }
                break;
            case 1:
                CoverageBean coverageBean=(CoverageBean)getIntent().getSerializableExtra("coverageBean");
                if(coverageBean!=null){
                    selImageList.addAll(coverageBean.getSelImageList());
                    etDes.setText(coverageBean.getCoverageCommonts());
                    refreshRG(coverageBean.getCoverageStatus());
                }
                break;
            case 2:
                EnclosureBean enclosureBean=(EnclosureBean)getIntent().getSerializableExtra("enclosureBean");
                if(enclosureBean!=null){
                    selImageList.addAll(enclosureBean.getSelImageList());
                    etDes.setText(enclosureBean.getEnclosureCommonts());
                    refreshRG(enclosureBean.getEnclosureStatus());
                }
                break;
            case 3:
                HardeningBean hardeningBean=(HardeningBean)getIntent().getSerializableExtra("hardeningBean");
                if(hardeningBean!=null){
                    selImageList.addAll(hardeningBean.getSelImageList());
                    etDes.setText(hardeningBean.getHardeningCommonts());
                    refreshRG(hardeningBean.getHardeningStatus());
                }
                break;
            case 4:
                InoutBean inoutBean=(InoutBean)getIntent().getSerializableExtra("inoutBean");
                if(inoutBean!=null){
                    selImageList.addAll(inoutBean.getSelImageList());
                    etDes.setText(inoutBean.getInoutCommonts());
                    refreshRG(inoutBean.getInoutStatus());
                }
                break;
            case 5:
                OilsBean oilsBean=(OilsBean)getIntent().getSerializableExtra("oilsBean");
                if(oilsBean!=null){
                    selImageList.addAll(oilsBean.getSelImageList());
                    etDes.setText(oilsBean.getOilsCommonts());
                    refreshRG(oilsBean.getOilsStatus());
                }
                break;
            case 6:
                EngineBean engineBean=(EngineBean)getIntent().getSerializableExtra("engineBean");
                if(engineBean!=null){
                    selImageList.addAll(engineBean.getSelImageList());
                    etDes.setText(engineBean.getEngineCommonts());
                    refreshRG(engineBean.getEngineStatus());
                }
                break;
            case 7:
                PublicityBean publicityBean=(PublicityBean)getIntent().getSerializableExtra("publicityBean");
                if(publicityBean!=null){
                    selImageList.addAll(publicityBean.getSelImageList());
                    etDes.setText(publicityBean.getPublicityCommonts());
                    refreshRG(publicityBean.getPublicityStatus());
                }
                break;
            case 8:
                TransportBean transportBean=(TransportBean)getIntent().getSerializableExtra("transportBean");
                if(transportBean!=null){
                    selImageList.addAll(transportBean.getSelImageList());
                    etDes.setText(transportBean.getTransportCommonts());
                    refreshRG(transportBean.getTransportStatus());
                }
                break;
        }

        initImagePicker();
        initWidget();
    }

    private void refreshRG(int status){
        if(status==1){
            rg.check(R.id.rb1);
        }else{
            rg.check(R.id.rb2);
        }
    }
    private void initDatas() {

    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        status=1;
                        xjjg="合格";
                        break;
                    case R.id.rb2:
                        status=2;
                        xjjg="不合格";
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
    public void onItemClick(View view, int position,int num) {
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
                                Intent intent = new Intent(NewCheckDetailsActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(NewCheckDetailsActivity.this, ImageGridActivity.class);
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
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
            case R.id.btnSure:
                    if(selImageList.size()==0){
                        ToastUtil.showToast("至少上传一张图片");
                        return;
                    }else if (status==2&&etDes.getText().toString().equals("")){
                        ToastUtil.showToast("请填写不合格描述！");
                        return;
                    }else{
                        showProgressDialog();
                        uploadPicture();
                    }
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
                                dissmissProgressDialog();
                                Log.w("main","上传成功:" + stringBuffer.toString());

                                setDatas();

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

    private void setDatas() {

        Intent intent=new Intent();
        intent.putExtra("xjjg",xjjg);
        intent.putExtra("picnum",selImageList.size());
        String str = stringBuffer.toString();
        switch (position){
            case 0:
                CleaningBean cleaningBean=new CleaningBean();
                cleaningBean.setCleaningAccount(userInfor.getName());
                cleaningBean.setCleaningCommonts(etDes.getText().toString());
                cleaningBean.setCleaningStatus(status);
                cleaningBean.setCleaningImage(str.substring(0,str.length()-1));
                cleaningBean.setSelImageList(selImageList);

                bigCheckBean.setCleaningAccount(userInfor.getName());
                bigCheckBean.setCleaningCommonts(etDes.getText().toString());
                bigCheckBean.setCleaningStatus(status);
                bigCheckBean.setCleaningImage(str.substring(0,str.length()-1));
                intent.putExtra("cleaningBean",cleaningBean);
                break;
            case 1:
                CoverageBean coverageBean=new CoverageBean();
                coverageBean.setCoverageAccount(userInfor.getName());
                coverageBean.setCoverageCommonts(etDes.getText().toString());
                coverageBean.setCoverageStatus(status);
                coverageBean.setCoverageImage(str.substring(0,str.length()-1));
                coverageBean.setSelImageList(selImageList);

                bigCheckBean.setCoverageAccount(userInfor.getName());
                bigCheckBean.setCoverageCommonts(etDes.getText().toString());
                bigCheckBean.setCoverageStatus(status);
                bigCheckBean.setCoverageImage(str.substring(0,str.length()-1));
                intent.putExtra("coverageBean",coverageBean);
                break;
            case 2:
                EnclosureBean enclosureBean=new EnclosureBean();
                enclosureBean.setEnclosureAccount(userInfor.getName());
                enclosureBean.setEnclosureCommonts(etDes.getText().toString());
                enclosureBean.setEnclosureStatus(status);
                enclosureBean.setEnclosureImage(str.substring(0,str.length()-1));
                enclosureBean.setSelImageList(selImageList);

                bigCheckBean.setEnclosureAccount(userInfor.getName());
                bigCheckBean.setEnclosureCommonts(etDes.getText().toString());
                bigCheckBean.setEnclosureStatus(status);
                bigCheckBean.setEnclosureImage(str.substring(0,str.length()-1));
                intent.putExtra("enclosureBean",enclosureBean);
                break;
            case 3:
                HardeningBean hardeningBean=new HardeningBean();
                hardeningBean.setHardeningAccount(userInfor.getName());
                hardeningBean.setHardeningCommonts(etDes.getText().toString());
                hardeningBean.setHardeningStatus(status);
                hardeningBean.setHardeningImage(str.substring(0,str.length()-1));
                hardeningBean.setSelImageList(selImageList);

                bigCheckBean.setHardeningAccount(userInfor.getName());
                bigCheckBean.setHardeningCommonts(etDes.getText().toString());
                bigCheckBean.setHardeningStatus(status);
                bigCheckBean.setHardeningImage(str.substring(0,str.length()-1));
                intent.putExtra("hardeningBean",hardeningBean);

                break;
            case 4:
                InoutBean inoutBean=new InoutBean();
                inoutBean.setInoutAccount(userInfor.getName());
                inoutBean.setInoutCommonts(etDes.getText().toString());
                inoutBean.setInoutStatus(status);
                inoutBean.setInoutImage(str.substring(0,str.length()-1));
                inoutBean.setSelImageList(selImageList);

                bigCheckBean.setInoutAccount(userInfor.getName());
                bigCheckBean.setInoutCommonts(etDes.getText().toString());
                bigCheckBean.setInoutStatus(status);
                bigCheckBean.setInoutImage(str.substring(0,str.length()-1));
                intent.putExtra("inoutBean",inoutBean);
                break;
            case 5:
                OilsBean oilsBean=new OilsBean();
                oilsBean.setOilsAccount(userInfor.getName());
                oilsBean.setOilsCommonts(etDes.getText().toString());
                oilsBean.setOilsStatus(status);
                oilsBean.setOilsImage(str.substring(0,str.length()-1));
                oilsBean.setSelImageList(selImageList);

                bigCheckBean.setOilsAccount(userInfor.getName());
                bigCheckBean.setOilsCommonts(etDes.getText().toString());
                bigCheckBean.setOilsStatus(status);
                bigCheckBean.setOilsImage(str.substring(0,str.length()-1));
                intent.putExtra("oilsBean",oilsBean);
                break;
            case 6:
                EngineBean engineBean=new EngineBean();
                engineBean.setEngineAccount(userInfor.getName());
                engineBean.setEngineCommonts(etDes.getText().toString());
                engineBean.setEngineStatus(status);
                engineBean.setEngineImage(str.substring(0,str.length()-1));
                engineBean.setSelImageList(selImageList);

                bigCheckBean.setEngineAccount(userInfor.getName());
                bigCheckBean.setEngineCommonts(etDes.getText().toString());
                bigCheckBean.setEngineStatus(status);
                bigCheckBean.setEngineImage(str.substring(0,str.length()-1));
                intent.putExtra("engineBean",engineBean);
                break;
            case 7:
                PublicityBean publicityBean=new PublicityBean();
                publicityBean.setPublicityAccount(userInfor.getName());
                publicityBean.setPublicityCommonts(etDes.getText().toString());
                publicityBean.setPublicityStatus(status);
                publicityBean.setPublicityImage(str.substring(0,str.length()-1));
                publicityBean.setSelImageList(selImageList);

                bigCheckBean.setPublicityAccount(userInfor.getName());
                bigCheckBean.setPublicityCommonts(etDes.getText().toString());
                bigCheckBean.setPublicityStatus(status);
                bigCheckBean.setPublicityImage(str.substring(0,str.length()-1));
                intent.putExtra("publicityBean",publicityBean);
                break;
            case 8:
                TransportBean transportBean=new TransportBean();
                transportBean.setTransportAccount(userInfor.getName());
                transportBean.setTransportCommonts(etDes.getText().toString());
                transportBean.setTransportStatus(status);
                transportBean.setTransportImage(str.substring(0,str.length()-1));
                transportBean.setSelImageList(selImageList);

                bigCheckBean.setTransportAccount(userInfor.getName());
                bigCheckBean.setTransportCommonts(etDes.getText().toString());
                bigCheckBean.setTransportStatus(status);
                bigCheckBean.setTransportImage(str.substring(0,str.length()-1));
                intent.putExtra("transportBean",transportBean);
                break;
        }
        intent.putExtra("bigCheckBean",bigCheckBean);
        setResult(100,intent);
        finish();
    }
}

