package com.heziz.liyang.ui.mine.mineinfo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heziz.liyang.R;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.ui.LoginActivity;
import com.heziz.liyang.utils.ScreenUtils;
import com.heziz.liyang.utils.StringUtil;
import com.heziz.liyang.utils.TS;
import com.heziz.liyang.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineXGMMActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.btn1)
    Button btn;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_xgmm);

        ButterKnife.bind(this);
        initViews();

        initeListners();
    }

    private void initViews() {

        tvTitle.setText("修改密码");
    }

    private void initDatas(String str1,String str2) {
        showProgressDialog();
        String urlnum = API.PASSWORD_XG;
        Map<String, String> paramsnum = new HashMap<>();
        paramsnum.put("access_token", MyApplication.getInstance().getUserInfor().getUuid());
        paramsnum.put("newPassword",str1);
        JsonCallBack1<SRequstBean<String>> jsonCallBacknum = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                dissmissProgressDialog();
                String res=response.body().getData();
                if("success".equals(res)) {

                    showDialog();

                }else{
                    ToastUtil.showToast("修改失败");
                }
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                ToastUtil.showToast("服务器异常");
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(urlnum, paramsnum, jsonCallBacknum);


}

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MineXGMMActivity.this,R.style.no_border_dialog);
        LayoutInflater inflater = LayoutInflater.from(MineXGMMActivity.this);
        View v = inflater.inflate(R.layout.alter_dialog_view, null);
        Button btnUp= (Button) v.findViewById(R.id.btnUp);
        LinearLayout llDialog= (LinearLayout) v.findViewById(R.id.llDialog);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) llDialog.getLayoutParams();
        lp.width = ScreenUtils.getScreenWidth(MineXGMMActivity.this);
        llDialog.setLayoutParams(lp);
        dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setContentView(v);
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                Intent intent = new Intent();
                intent.setAction("xg_password_success");
                sendBroadcast(intent);
                Intent intent1=new Intent(MineXGMMActivity.this, LoginActivity.class);
                startActivity(intent1);
                finish();
            }

        });
    }

    private void initeListners() {
        rlBack.setOnClickListener(this);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
            case R.id.btn1:
                String str1=et1.getText().toString();
                String str2=et2.getText().toString();
                if(StringUtil.isEmpty(str1)||StringUtil.isEmpty(str2)){
                    TS.Show(MineXGMMActivity.this,"密码不能为空");
                    return;
                }
                if(str1.length()<6){
                    TS.Show(MineXGMMActivity.this,"密码不能少于六位");
                    return;
                }
                if(!str1.equals(str2)){
                    TS.Show(MineXGMMActivity.this,"两次输入密码不一致，请重新输入");
                    return;
                }
                ScreenUtils.hiddenKeyBord(MineXGMMActivity.this);
                initDatas(str1,str2);
                break;
        }
    }
}
