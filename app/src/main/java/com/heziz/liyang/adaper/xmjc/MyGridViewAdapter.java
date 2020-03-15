package com.heziz.liyang.adaper.xmjc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.heziz.liyang.R;

import java.util.List;

/**
 * Created by sws on 2019-11-27.
 * from:
 * describe:
 */

public class MyGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private int lastPosition;//定义一个标记为最后选择的位置
    private String[] str = null;
    private List<String> list=null;
    public void setData(String[] str, int lastPos,List<String> list) {
        this.str = str;
        this.list=list;
        this.lastPosition = lastPos;
    }

    public void setSeclection(int position) {
        lastPosition = position;
    }

    public MyGridViewAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return str.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View mView, ViewGroup arg2) {
        MyGridViewAdapter.ViewHolder holder = null;
        if (mView == null) {
            holder = new MyGridViewAdapter.ViewHolder();
            mView = LayoutInflater.from(mContext).inflate(R.layout.gridview_item, null);
            holder.text = (RadioButton) mView.findViewById(R.id.tvName);
            holder.tvStatus = (TextView) mView.findViewById(R.id.tvStatus);
            mView.setTag(holder);
        } else {
            holder = (MyGridViewAdapter.ViewHolder) mView.getTag();
        }
        holder.text.setText(str[position] + "");
        holder.tvStatus.setText(list.get(position)+ "");
        if (lastPosition == position) {//最后选择的位置
            holder.text.setChecked(true);
            holder.tvStatus.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            holder.text.setChecked(false);
            holder.tvStatus.setTextColor(mContext.getResources().getColor(R.color.maincolor));
        //    holder.check.setBackgroundResource(R.drawable.button_unchecked);
        }
        return mView;
    }

    class ViewHolder {
        private RadioButton text;
        private TextView tvStatus;
    }
}
