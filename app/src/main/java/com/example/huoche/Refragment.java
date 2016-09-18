package com.example.huoche;

/**
 * Created by 82667 on 2016/9/17.
 */

import android.content.Intent;
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
import android.widget.Toast;

import java.net.HttpURLConnection;

/**
 * Created by 82667 on 2016/9/8.
 */

public class Refragment extends Fragment {
    private EditText username;
    private EditText password;
    private Button regist,fanhui;
    HttpURLConnection con;
    public static Handler handler;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_re,container,false);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.arg1==3){
                    Toast.makeText(getContext(), "注册成功",
                            Toast.LENGTH_SHORT).show();
                }else if (msg.arg1==4){
                    Toast.makeText(getContext(), "注册失败",
                            Toast.LENGTH_SHORT).show();
                }

            }
        };
        username= (EditText) rootView.findViewById(R.id.etre);
        password= (EditText) rootView.findViewById(R.id.etre2);

        regist= (Button) rootView. findViewById(R.id.btnre);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(v,username.getText().toString(),password.getText().toString());
            }
        });
        fanhui= (Button) rootView.findViewById(R.id.btnrefan);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        return rootView;

    }



    public void register(View v,String username,String password)
    {int a=2;
        String url = "http://192.168.1.113:8080/hello/DataServiceServlet?key=2&username="+username+"&password="+password;
        HttpThread jj=  new HttpThread(url ,a);

        jj.start();
    }

}
