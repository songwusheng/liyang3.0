package com.heziz.liyang.ui.zhihui.smz;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heziz.liyang.R;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.ui.project.ProjectGKActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SmzDetailsActivity extends BaseActivity {

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.cb)
    TextView cb;

    private SmzD1Fragment smzD1Fragment;
    private SmzD2Fragment smzD2Fragment;
    private SmzD3Fragment smzD3Fragment;

    private String id;
    private Long proid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smz_details);
        ButterKnife.bind(this);

        initViews();

        initListeners();
    }



    private void initViews() {
        tvTitle.setText("实名制考勤");

        id=getIntent().getStringExtra("id");
        proid=getIntent().getLongExtra("proid",0);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (smzD1Fragment == null) {
            smzD1Fragment = SmzD1Fragment.newInstance(id);
            fragmentTransaction.add(R.id.fl1, smzD1Fragment);
        }
        if (smzD2Fragment == null) {
            smzD2Fragment = SmzD2Fragment.newInstance(id);
            fragmentTransaction.add(R.id.fl2, smzD2Fragment);
        }
        if (smzD3Fragment == null) {
            smzD3Fragment = SmzD3Fragment.newInstance(id);
            fragmentTransaction.add(R.id.fl3, smzD3Fragment);
        }
        fragmentTransaction.commit();
    }

    private void initListeners() {

        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SmzDetailsActivity.this, ProjectGKActivity.class);
                intent.putExtra("id",proid);
                startActivity(intent);
            }
        });
    }
}
