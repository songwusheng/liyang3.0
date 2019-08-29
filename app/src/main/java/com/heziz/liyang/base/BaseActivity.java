package com.heziz.liyang.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.heziz.liyang.R;
import com.heziz.liyang.utils.StatusBarCompat;


public class BaseActivity extends AppCompatActivity {

	protected Context mContext;
	private Toast mToast;

	private ProgressDialog mProgressDialog;
	private LinearLayout titleMenu;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		mContext = this;
		mProgressDialog = new ProgressDialog(this, R.style.wait_dialog);
		mProgressDialog.setCanceledOnTouchOutside(false);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 竖屏
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//			//透明状态栏
//			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
////            //透明导航栏
////            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//		} else {
//			titleMenu = (LinearLayout) findViewById(R.id.titleMenu);
//			titleMenu.setVisibility(View.GONE);
//		}
//		StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.maincolor));
		StatusBarCompat.translucentStatusBar(this, true);
	}

	private boolean isDestroyed = false;

	public boolean isActivityDestory() {
		return isDestroyed;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		isDestroyed = true;
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	protected void showProgressDialog() {
		if(mProgressDialog != null && !mProgressDialog.isShowing()){
			mProgressDialog.show();
			mProgressDialog.setContentView(R.layout.common_request_layout);
		}
	}

	protected void dissmissProgressDialog() {
		if(mProgressDialog != null && mProgressDialog.isShowing()){
			mProgressDialog.dismiss();
		}
	}

	protected void toast(int res) {
		if (mToast == null){
			mToast = Toast.makeText(mContext, res, Toast.LENGTH_SHORT);
		}else{
			mToast.setText(res);
		}
		mToast.show();
	}

	protected void toast(String str) {
		if (mToast == null){
			mToast = Toast.makeText(mContext, str, Toast.LENGTH_SHORT);
		}else{
			mToast.setText(str);
		}
		mToast.show();
	}


	protected void startMyActivity(Class<?> clazz) {
		Intent intent = new Intent(this, clazz);
		startActivity(intent);
	}

	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		View mDecorView = getWindow().getDecorView();

		mDecorView.setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//						| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
						| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//						| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
//						| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
						| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
		);
	}

}
