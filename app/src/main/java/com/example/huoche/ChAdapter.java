package com.example.huoche;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by 82667 on 2016/9/12.
 */

public class ChAdapter extends BaseAdapter {
    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    public ChAdapter(Context context, List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class Zujian{
        public TextView checi;
        public TextView shijian;
        public TextView didian;
        public TextView time;
        public TextView zuo;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    /**
     * 获得某一位置的数据
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    /**
     * 获得唯一标识
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Zujian zujian=null;
        if(convertView==null){
            zujian=new Zujian();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.list, null);
            zujian.shijian=(TextView)convertView.findViewById(R.id.shijian);
            zujian.didian=(TextView)convertView.findViewById(R.id.didian);
            zujian.checi=(TextView)convertView.findViewById(R.id.checi);
            zujian.time= (TextView) convertView.findViewById(R.id.time);
            zujian.zuo= (TextView) convertView.findViewById(R.id.tvqwe);
            convertView.setTag(zujian);
        }else{
            zujian=(Zujian)convertView.getTag();
        }
        //绑定数据
        zujian.checi.setText((String)data.get(position).get("checi"));
        zujian.didian.setText((String)(data.get(position).get("chufa")+"--"+data.get(position).get("mudidi")));
        zujian.shijian.setText((String)data.get(position).get("shijian"));
        zujian.time.setText(data.get(position).get("time")+"--"+data.get(position).get("time1"));
        zujian.zuo.setText((String)data.get(position).get("zuo"));
        return convertView;
    }
}



