package com.example.huoche;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 82667 on 2016/9/12.
 */

public class Jieguo extends Fragment {
    private TextView yonghu;
    private Button back;
    private ListView listView=null;
    public static List<Map<String, Object>> list;
    public static Handler handler;
    public static void setList(List<Map<String, Object>> list) {
        Jieguo.list = list;
    }

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_jieguo, container, false);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
               if (msg.arg1==7){
                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.activity_main,new Chepiao()).commit();
                }

            }
        };

        yonghu= (TextView) root.findViewById(R.id.tvyonghu);
       listView= (ListView) root.findViewById(R.id.listview);

        listView.setAdapter(new MyAdapter(getContext(), list));
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String ss= (String) list.get(position).get("checi");
        System.out.println(ss);
        chepiao(view,ss);
    }
});


        yonghu.setText("用户：" + ((MainActivity) getActivity()).getYonghu());
        back= (Button) root.findViewById(R.id.btnback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        return root;
    }
    public  void chepiao(View v, String checi) {
        int a = 4;
        String url = "http://192.168.1.113:8080/hello/DataServiceServlet?key=4&checi="+checi;
        HttpThread ht = new HttpThread(url, a);
        ht.start();
    }

}
