package com.example.huoche;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

/**
 * Created by 82667 on 2016/9/8.
 */

public class ChaFragment extends Fragment {
    private TextView textView;
    private Button riqi,yigo;
    private EditText edmudidi, edchufadi, edriqi;
  private   List<Map<String, Object>> list;
    public static Handler handler;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cha, container, false);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.arg1==5){
                    Toast.makeText(getContext(), "无车票",
                            Toast.LENGTH_SHORT).show();
                }else if (msg.arg1==6){
                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.activity_main,new Jieguo()).commit();
                }else if(msg.arg1==10){
                    Toast.makeText(getContext(), "你还未购买过车票",
                            Toast.LENGTH_SHORT).show();
                }else if (msg.arg1==11){
                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.activity_main,new YigoFragment()).commit();
                }

            }
        };
        yigo= (Button) root.findViewById(R.id.btnyigou);
        yigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sd=((MainActivity) getActivity()).getYonghu();
                yigo(v, sd);
            }
        });
        edmudidi = (EditText) root.findViewById(R.id.etmudidi);
        edchufadi = (EditText) root.findViewById(R.id.etchufadi);
        edriqi = (EditText) root.findViewById(R.id.etriqi);
        textView = (TextView) root.findViewById(R.id.tvyonghu);
        textView.setText("用户：" + ((MainActivity) getActivity()).getYonghu());
        root.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chaxun(v, edchufadi.getText().toString(), edmudidi.getText().toString(), edriqi.getText().toString());
            }
        });
        root.findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        riqi = (Button) root.findViewById(R.id.btnriqi);
        riqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String thedate = String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth);
                        edriqi.setText(thedate);
                    }
                }, 2016, 4, 30).show();
            }
        });

        return root;
    }

    public void chaxun(View v, String chufadi, String mudidi, String date) {
        int a = 3;
        String url = "http://192.168.1.113:8080/hello/DataServiceServlet?key=3&chufadi=" + chufadi + "&mudidi=" + mudidi + "&date=" + date;
        HttpThread ht = new HttpThread(url, a);

        ht.start();
    }

    public  void yigo(View v, String yonghuname) {
        int a = 6;
        String url = "http://192.168.1.113:8080/hello/DataServiceServlet?key=6&yonghuname="+yonghuname;
        HttpThread ht = new HttpThread(url, a);
        ht.start();
    }

}
