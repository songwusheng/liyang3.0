package com.heziz.liyang.ui.zhihui.zcwj;
import android.os.Environment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.heziz.liyang.R;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.utils.ToastUtil;
import com.heziz.liyang.view.DoubleScaleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;
import com.tencent.smtt.sdk.TbsReaderView;


import java.io.File;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends BaseActivity implements TbsReaderView.ReaderCallback {

//    ProgressBar web_bar;
    TbsReaderView mTbsReaderView;
    RelativeLayout mRelativeLayout;

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.iv1)
    DoubleScaleImageView iv;

    // 文件的下载路径
//    private static final String base = "http://192.168.200.15:8088/xmgl/userfiles/upload/files/201807310923533619.xls";
    //    private static final String base = "http://192.168.200.15:8088/xmgl//userfiles/upload/files/20180803143620366.doc";
//    private static final String base = "http://192.168.200.15:8088/xmgl//userfiles/upload/files/201808061834294755.pdf";

    // 文件的存储路径
    private String savePath=Environment.getExternalStorageDirectory() + "/download/test/document/";
    private String docName;

    private String base;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview1);

        ButterKnife.bind(this);
        // 在代码中添加布局，这个我也不知道什么原因，网上很多人都说在布局文件中加载会出错


        mTbsReaderView = new TbsReaderView(this, this);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.tbsView);
        mRelativeLayout.addView(mTbsReaderView, new RelativeLayout.LayoutParams(-1, -1));

        // 日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new java.util.Date());
        base=getIntent().getStringExtra("url");
        showProgressDialog();
        if(base.endsWith("jpg")||base.endsWith("jpeg")||base.endsWith("png")||base.endsWith("bmp")){
            iv.setVisibility(View.VISIBLE);
            Glide.with(this).load(base).into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    dissmissProgressDialog();
                    iv.setImageDrawable(glideDrawable);
                }
            });

        }else{

            initDoc();
        }



        tvTitle.setText("政策文件");
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void downloadFile(final String url, final String path) {

        OkGo.<File>get(url).execute(new FileCallback(path,docName) {
            @Override
            public void onSuccess(Response<File> response) {
                displayFile(savePath, docName);
            }

            @Override
            public void onError(Response<File> response) {
                super.onError(response);
                dissmissProgressDialog();
                ToastUtil.showToast("下载失败");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTbsReaderView.onStop();
    }

    private void initDoc() {
        int i = base.lastIndexOf("/");
        docName = base.substring(i, base.length());
        Log.d("print", "---substring---" + docName);
        // 下载文件
        downloadFile(base, savePath);


    }


    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {
        Log.d("call", "==================+++++====-=-=++" + integer);
    }

    private String tbsReaderTemp = Environment.getExternalStorageDirectory() + "/TbsReaderTemp";
    private void displayFile(String filePath, String fileName) {

        //增加下面一句解决没有TbsReaderTemp文件夹存在导致加载文件失败
        String bsReaderTemp = tbsReaderTemp;
        File bsReaderTempFile = new File(bsReaderTemp);
        if (!bsReaderTempFile.exists()) {
            Log.d("print", "准备创建/TbsReaderTemp！！");
            boolean mkdir = bsReaderTempFile.mkdir();
            if (!mkdir) {
                Log.d("print", "创建/TbsReaderTemp失败！！！！！");
                dissmissProgressDialog();
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString("filePath", filePath+"/"+fileName);
        bundle.putString("tempPath", tbsReaderTemp);
        boolean result = mTbsReaderView.preOpen(getFileType(fileName), false);
        Log.d("print", "查看文档---" + result);
        dissmissProgressDialog();
        if (result) {
            mTbsReaderView.openFile(bundle);
        } else {

        }
    }

    /**
     * 后缀名的判断
     *
     * @param paramString
     * @return
     */
    private String getFileType(String paramString) {
        String str = "";

        if (TextUtils.isEmpty(paramString)) {
            Log.d("print", "paramString---->null");
            return str;
        }
        Log.d("print", "paramString:" + paramString);
        int i = paramString.lastIndexOf('.');
        if (i <= -1) {
            Log.d("print", "i <= -1");
            return str;
        }

        str = paramString.substring(i + 1);
        Log.d("print", "paramString.substring(i + 1)------>" + str);
        return str;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
           finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
