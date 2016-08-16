package com.hkbank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hkbank.R;
import com.hkbank.pojo.SMSInfo;

import java.util.List;

/**
 * Created by junyuli on 8/16/16.
 */
public class SMSDataAdapter extends BaseAdapter {

    private Context context;
    private List<SMSInfo> list;

    public SMSDataAdapter(Context context, List<SMSInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sms_listview, null);
        }

        SMSInfo info = list.get(position);
        TextView _idText = (TextView) convertView.findViewById(R.id._id);
        TextView bodyText = (TextView) convertView.findViewById(R.id.body);

        _idText.setText(info.get_id());
        bodyText.setText(info.getSmsBody());

        return convertView;
    }
}
