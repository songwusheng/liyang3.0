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
import com.heziz.liyang.fragment.rcjc.wdbygs.hgl.AQBhgFragment;
import com.heziz.liyang.fragment.rcjc.wdbygs.hgl.AQHgFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AQZLHGLActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvtoptitle)
    TextView tvtoptitle;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.rg)
    RadioGroup rgBottom;

    private String type;
    private AQHgFragment ycxmFragment;
    private AQBhgFragment wcxmFragment;
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
            tvTitle.setText("安全检查");
            tvtoptitle.setText("安全检查情况");
        }else{
            tvTitle.setText("质量检查");
            tvtoptitle.setText("质量检查情况");
        }

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (ycxmFragment == null) {
            ycxmFragment = new AQHgFragment(type);
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
                            ycxmFragment = new AQHgFragment(type);
                            fragmentTransaction.add(R.id.flMain, ycxmFragment);
                        }else{
                            fragmentTransaction.show(ycxmFragment);
                        }

                        break;
                    case R.id.rb2:
                        if (wcxmFragment == null) {
                            wcxmFragment = new AQBhgFragment(type);
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
