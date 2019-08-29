package com.heziz.liyang.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class TS {

	private static Toast Instance = null;

	public static void Show(Context ctx, String content) {
		if (Instance == null) {
			Instance = Toast.makeText(ctx, content, Toast.LENGTH_SHORT);
			Instance.setGravity(Gravity.CENTER,0,0);
		} else {
			if (StringUtil.isSet(content)) {
				Instance.setText(content);
			}
		}
		Instance.show();
	}
}
