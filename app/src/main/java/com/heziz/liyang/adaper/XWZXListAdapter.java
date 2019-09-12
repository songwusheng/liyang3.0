package com.heziz.liyang.adaper;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.liyang.R;
import com.heziz.liyang.bean.mine.XWZXBean;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class XWZXListAdapter extends BaseQuickAdapter<XWZXBean,BaseViewHolder> {
    private Context context;
    public XWZXListAdapter(Context context, List<XWZXBean> list) {
        super(R.layout.xwzx_list_item,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, XWZXBean item) {
        helper.setText(R.id.tvTitle1, item.getOriginalName().replace("."+item.getFileExtension(),""));
        helper.setText(R.id.tvTime, item.getUpdateTime().split(" ")[0]);
//        helper.setText(R.id.tvtotal,item.getListSize()+"");
//        helper.setText(R.id.tvzh,item.getBuild()+"");
//        helper.setText(R.id.tvcb,item.getDiff()+"");
////                .setText(R.id.tweetText, item.getText())
////                .setText(R.id.tweetDate, item.getCreatedAt())
////                .setVisible(R.id.tweetRT, item.isRetweet())
////                .linkify(R.id.tweetText);
//        if (item.get)
//        String type=item.getFileExtension();
//        if(type.equals("pdf")){
//            Glide.with(mContext).load(R.mipmap.wj_pdf).crossFade().into((ImageView) helper.getView(R.id.ivlx));
//        }else if(type.equals("txt")){
//            Glide.with(mContext).load(R.mipmap.wj_txt).crossFade().into((ImageView) helper.getView(R.id.ivlx));
//        }else if(type.equals("jpg")){
//            Glide.with(mContext).load(R.mipmap.wj_img).crossFade().into((ImageView) helper.getView(R.id.ivlx));
//        }else if(type.equals("jpeg")){
//            Glide.with(mContext).load(R.mipmap.wj_img).crossFade().into((ImageView) helper.getView(R.id.ivlx));
//        }else if(type.equals("gif")){
//            Glide.with(mContext).load(R.mipmap.wj_img).crossFade().into((ImageView) helper.getView(R.id.ivlx));
//        }else if(type.equals("doc")||type.equals("docx")){
//            Glide.with(mContext).load(R.mipmap.wj_word).crossFade().into((ImageView) helper.getView(R.id.ivlx));
//        }else if(type.equals("xls")||type.equals("xlsx")){
//            Glide.with(mContext).load(R.mipmap.wj_xls).crossFade().into((ImageView) helper.getView(R.id.ivlx));
//        }else if(type.equals("ppt")||type.equals("pptx")){
//            Glide.with(mContext).load(R.mipmap.wj_ppt).crossFade().into((ImageView) helper.getView(R.id.ivlx));
//        }else if(type.equals("png")){
//            Glide.with(mContext).load(R.mipmap.wj_img).crossFade().into((ImageView) helper.getView(R.id.ivlx));
//        }else if(type.equals("bmp")){
//            Glide.with(mContext).load(R.mipmap.wj_img).crossFade().into((ImageView) helper.getView(R.id.ivlx));
//        }else{
//            Glide.with(mContext).load(R.drawable.xwzx_wj_icon).crossFade().into((ImageView) helper.getView(R.id.ivlx));

        //}
    }
}
