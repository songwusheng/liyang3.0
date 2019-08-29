package com.heziz.liyang.ui.rcjc;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heziz.liyang.R;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.fragment.rcjc.wdbygs.BhgFragment;
import com.heziz.liyang.fragment.rcjc.wdbygs.HgFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HGLActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvtoptitle)
    TextView tvtoptitle;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.rg)
    RadioGroup rgBottom;

    private String type;
    private HgFragment ycxmFragment;
    private BhgFragment wcxmFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdbgdzc1);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initViews() {
        type=getIntent().getStringExtra("type");
        if(type.equals("1")){
            tvtoptitle.setText("工地自查情况");
        }else{
            tvtoptitle.setText("网络人员检查情况");
        }
        tvTitle.setText("五达标一公示");
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (ycxmFragment == null) {
            ycxmFragment = new HgFragment(type);
            fragmentTransaction.add(R.id.flMain, ycxmFragment);
        }
        fragmentTransaction.commit();
    }

    private void hidFragment(FragmentTransaction fragmentTransaction) {
        if (ycxmFragment != null) {
            fragmentTransaction.hide(ycxmFragment);
        }
        if (wcxmFragment != null) {
            fragmentTransaction.hide(wcxmFragment);
        }

    }

    private void initDatas() {

    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
        rgBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                hidFragment(fragmentTransaction);
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.rb1:
                        if (ycxmFragment == null) {
                            ycxmFragment = new HgFragment(type);
                            fragmentTransaction.add(R.id.flMain, ycxmFragment);
                        }else{
                            fragmentTransaction.show(ycxmFragment);
                        }

                        break;
                    case R.id.rb2:
                        if (wcxmFragment == null) {
                            wcxmFragment = new BhgFragment(type);
                            fragmentTransaction.add(R.id.flMain, wcxmFragment);
                        }else{
                            fragmentTransaction.show(wcxmFragment);
                        }

                        break;

                }
                fragmentTransaction.commit();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
        }
    }
}
