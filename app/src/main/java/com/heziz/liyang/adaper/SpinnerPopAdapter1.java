package com.heziz.liyang.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.heziz.liyang.R;
import java.util.List;

/**
 * Created by sws on 2019-04-28.
 * from:
 * describe:
 */

public class SpinnerPopAdapter1 extends BaseAdapter {
    private List<String> content;
    private Context context;
    private LayoutInflater mInflater;

    public SpinnerPopAdapter1(Context context, List<String> content){
        this.context = context;
        this.content = content;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return content == null ? 0 : content.size();
    }

    @Override
    public Object getItem(int position) {
        return content.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.popuwindow_spinner_item2,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_spinner = (TextView) convertView.findViewById(R.id.tv_spinner);
//            viewHolder.check = (CheckableLayout) convertView.findViewById(R.id.check);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String spinnerText = content.get(position);
        viewHolder.tv_spinner.setText(spinnerText);
        return convertView;
    }
    public static class ViewHolder{
        TextView tv_spinner;
//        public CheckableLayout check;
    }
}
