package com.example.huoche;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

/**
 * Created by 82667 on 2016/9/14.
 */

public class YigoFragment extends Fragment {
    private ListView listView=null;
    private TextView hu;
    private Button fanhui,tuipiao,gaiqan;
    private EditText che,gai;
    public static List<Map<String, Object>> list;
    public static Handler handler;
    public static void setList(List<Map<String, Object>> list) {
        YigoFragment.list = list;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_yigo, container, false);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.arg1==12){
                    Toast.makeText(getContext(), "退票成功",
                            Toast.LENGTH_SHORT).show();
                }else if (msg.arg1==13){
                    Toast.makeText(getContext(), "退票失败",
                            Toast.LENGTH_SHORT).show();
                }else if (msg.arg1==14){
                    System.out.println("1");
                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.activity_main,new Jieguo()).commit();
                }

            }
        };
        gai= (EditText) root.findViewById(R.id.etgai);

        che= (EditText) root.findViewById(R.id.etche);
        gaiqan= (Button) root.findViewById(R.id.btngai);
        gaiqan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gaiqian(v, ((MainActivity) getActivity()).getYonghu(),gai.getText().toString());
            }
        });
        tuipiao= (Button) root.findViewById(R.id.btntui);
        tuipiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tuipiao(v, ((MainActivity) getActivity()).getYonghu(),che.getText().toString());
            }
        });

        hu= (TextView) root.findViewById(R.id.tvhu);
        listView= (ListView) root.findViewById(R.id.lvyigo);
        listView.setAdapter(new ChAdapter(getContext(), list));
        hu.setText("用户：" + ((MainActivity) getActivity()).getYonghu());
        fanhui= (Button) root.findViewById(R.id.btnhou);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        return root;
    }
    public  void tuipiao(View v, String username,String checihao) {
        int a = 7;
        String url = "http://192.168.1.113:8080/hello/DataServiceServlet?key=7&username="+username+"&checihao="+checihao;
        HttpThread ht = new HttpThread(url, a);
        ht.start();
    }
    public  void gaiqian(View v, String username,String checihao) {
        int a = 8;
        String url = "http://192.168.1.113:8080/hello/DataServiceServlet?key=8&username="+username+"&checihao="+checihao;
        HttpThread ht = new HttpThread(url, a);
        ht.start();
    }
}
