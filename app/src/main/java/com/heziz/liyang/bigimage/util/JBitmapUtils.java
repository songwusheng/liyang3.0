package com.heziz.liyang.bigimage.util;

import android.content.Context;

import com.heziz.liyang.bigimage.beans.JPhotosInfos;



/**
 * Created by Jincancan.
 */
public class JBitmapUtils {

    public static float getImgScale(float width, float height){
        return width * 1.0f / height;
    }

    /**
     * 计算小图与全屏大图时候的缩放度，用于起始动画
     * @param context
     * @param infos
     * @return
     */
    public static float getCurrentPicOriginalScale(Context context, JPhotosInfos infos){

        float mScale;

        int width = infos.getWidth();
        int height = infos.getHeight();

        float imgScale = JBitmapUtils.getImgScale(width, height);
        float mWindowScale = JWindowUtil.getWindowScale(context);

        if(imgScale >= mWindowScale){
            mScale = width * 1.0f / JWindowUtil.getWindowWidth(context);
        }else{
            mScale = height * 1.0f / JWindowUtil.getWindowHeight(context);
        }
        return mScale;
    }
}
