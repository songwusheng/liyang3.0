package com.heziz.liyang.ui.mine.fdl;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.heziz.liyang.R;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.fdl.MineFDLBean;
import com.heziz.liyang.bean.fdl.MinefdlFFFFBean;
import com.heziz.liyang.bigimage.JBrowseImgActivity;
import com.heziz.liyang.bigimage.util.JMatrixUtil;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineFDLdetailsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvGYL)
    TextView tvGYL;
    @BindView(R.id.tvGYDJ)
    TextView tvGYDJ;
    @BindView(R.id.tvGYSJ)
    TextView tvGYSJ;
    @BindView(R.id.ivPic)
    ImageView ivPic;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.tvSPJG)
    TextView tvSPJG;
    @BindView(R.id.btnOk)
    Button btnOk;
    private UserInfor userInfor;
    private MineFDLBean mineFDLBean;
    private String status="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_fdldetails);
        ButterKnife.bind(this);
        initViews();
        initListeners();

    }

    private void initViews() {
        userInfor=MyApplication.getInstance().getUserInfor();
        mineFDLBean=(MineFDLBean) getIntent().getSerializableExtra("mineFdlListBean");
        tvTitle.setText("非道路机械管理");
        tvName.setText(mineFDLBean.getProjectName());

        if(mineFDLBean.getAuditStatus()==0){
            if (!userInfor.getPosition().equals("3")){
                tvSPJG.setVisibility(View.GONE);
            }
        }else{
            if (!userInfor.getPosition().equals("3")){
                btnOk.setVisibility(View.GONE);
                rg.setVisibility(View.GONE);
            }
        }

        tvGYL.setText("购油数量："+mineFDLBean.getAmount()+"升");
        tvGYDJ.setText("购油等级："+mineFDLBean.getGrade());
        tvGYSJ.setText("购油时间："+mineFDLBean.getByTime());
        Glide.with(this).load(mineFDLBean.getInvoicePath()).into(ivPic);
        String s="";
        switch (mineFDLBean.getAuditStatus()){
            case 0:
                s="待审核";
                break;
            case 1:
                s="通过";
                break;
            case 2:
                s="不通过";
                break;

        }
        tvSPJG.setText("审批结果："+s);
    }

    private void initDatas() {
        String url2 = API.MINE_FDLJX_ZT_LIST + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
        Map<String, String> params1 = new HashMap<>();
        params1.put("auditStatus", status);
        params1.put("id", mineFDLBean.getId()+"");
//        if(userInfor.getPosition().equals("1")){
//            params1.put("auditorId", ""+userInfor.getStation());
//        }else  if(userInfor.getPosition().equals("2")){
//            params1.put("auditorId", ""+userInfor.getManagerId());
//        }
//        params1.put("auditTime", TimeUtils.getCurrentTime());
//        params1.put("endTime", TimeUtils.getCurrentTime());
        JsonCallBack1<SRequstBean<MinefdlFFFFBean>> jsonCallBack2 = new JsonCallBack1<SRequstBean<MinefdlFFFFBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<MinefdlFFFFBean>> response) {
//                ToastUtil.showToast("审批成功");
//                btnOk.setVisibility(View.GONE);
//                rg.setVisibility(View.GONE);
//                tvSPJG.setVisibility(View.INVISIBLE);
//                if(response.body().getData().getAuditStatus()==1){
//                    tvSPJG.setText("合格");
//                }else{
//                    tvSPJG.setText("不合格");
//                }

               Intent intent = new Intent();
                intent.setAction("mine_sp_success");
                sendBroadcast(intent);

                finish();

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<MinefdlFFFFBean>> response) {
                super.onError(response);
                ToastUtil.showToast("审批失败");
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData0(url2, params1,  jsonCallBack2);


    }

    private void initListeners() {
        btnOk.setOnClickListener(this);
        rlBack.setOnClickListener(this);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbhg:
                        status="1";
                        break;
                    case R.id.rbbhg:
                        status="2";
                        break;
                }
            }
        });
        ivPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                JImageShowUtil.displayImage(mineFDLBean.getInvoicePath(),ivPic,true);
                List<Rect> rects = new ArrayList<>();
                rects.add(JMatrixUtil.getDrawableBoundsInView(ivPic));
                JBrowseImgActivity.start(MineFDLdetailsActivity.this, new ArrayList<>(Arrays.asList(mineFDLBean.getInvoicePath())), 0, rects);
//                ivPic.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOk:
                initDatas();
                break;
            case R.id.rlBack:
                finish();
                break;
        }
    }
}
