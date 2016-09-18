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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

/**
 * Created by 82667 on 2016/9/13.
 */

public class Chepiao extends Fragment {
    private TextView checi;
    private RadioButton yi, er, shang;
    private Button back, go;
    private EditText qw;
    public static List<Map<String, Object>> list = null;
    public static Handler handler;
    private String ch;

    public static void setList(List<Map<String, Object>> list) {
        Chepiao.list = list;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chepiao, container, false);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.arg1 == 8) {

                    yi.setText("一等座剩余：" + (String) list.get(0).get("yideng") + "张");
                    er.setText("二等座剩余：" + (String) list.get(0).get("erdeng") + "张");
                    shang.setText("商务座剩余：" + (String) list.get(0).get("shangwu") + "张");
                    Toast.makeText(getContext(), "购买成功",
                            Toast.LENGTH_SHORT).show();
                } else if (msg.arg1 == 9) {
                    Toast.makeText(getContext(), "购买失败",
                            Toast.LENGTH_SHORT).show();
                }

            }
        };
        checi = (TextView) root.findViewById(R.id.tvcheci);

        yi = (RadioButton) root.findViewById(R.id.rba);
        er = (RadioButton) root.findViewById(R.id.rbb);
        shang = (RadioButton) root.findViewById(R.id.rbc);

        back = (Button) root.findViewById(R.id.btnfanhui);
        go = (Button) root.findViewById(R.id.btngoumai);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (yi.isChecked()) {
                    int y = Integer.parseInt((String) list.get(0).get("yideng"));
                    if (y >= 1) {
                        goumai(v, ch, "yideng", (String) list.get(0).get("yideng"), ((MainActivity) getActivity()).getYonghu());
                    }else {
                        Toast.makeText(getContext(), "购买失败",
                                Toast.LENGTH_SHORT).show();
                    }

                } else if (er.isChecked()) {
                    int q = Integer.parseInt((String) list.get(0).get("erdeng"));
                    if (q >= 1) {
                        goumai(v, ch, "erdeng", (String) list.get(0).get("erdeng"), ((MainActivity) getActivity()).getYonghu());
                    }else {
                        Toast.makeText(getContext(), "购买失败",
                                Toast.LENGTH_SHORT).show();
                    }
                } else if (shang.isChecked()) {
                    int x = Integer.parseInt((String) list.get(0).get("shangwu"));
                    if (x >= 1) {
                        goumai(v, ch, "shangwu", (String) list.get(0).get("shangwu"), ((MainActivity) getActivity()).getYonghu());
                    }else {
                        Toast.makeText(getContext(), "购买失败",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        ch = (String) list.get(0).get("checi");

        checi.setText("车次：" + ch);
        yi.setText("一等座剩余：" + (String) list.get(0).get("yideng") + "张");
        er.setText("二等座剩余：" + (String) list.get(0).get("erdeng") + "张");
        shang.setText("商务座剩余：" + (String) list.get(0).get("shangwu") + "张");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        return root;
    }

    public void goumai(View v, String checi, String zuowei, String shu, String name) {
        int a = 5;

        String url = "http://192.168.1.113:8080/hello/DataServiceServlet?key=5&checi=" + checi + "&zuowei=" + zuowei + "&shu=" + shu + "&name=" + name;
        HttpThread ht = new HttpThread(url, a);
        ht.start();
    }

}
