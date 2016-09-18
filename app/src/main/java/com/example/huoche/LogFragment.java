package com.example.huoche;

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

public class LogFragment extends Fragment {
    private EditText username;
    private EditText password;
    private Button sign,reg;
    HttpURLConnection con;
public static Handler handler;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_log,container,false);
         handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
               if (msg.arg1==1){
                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.activity_main,new ChaFragment()).commit();
                }else if (msg.arg1==2){
                    Toast.makeText(getContext(), "用户名或密码错误",
                            Toast.LENGTH_SHORT).show();
                }

            }
        };
        username= (EditText) rootView.findViewById(R.id.editText);
        password= (EditText) rootView.findViewById(R.id.editText2);
        sign= (Button) rootView.findViewById(R.id.button);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setYonghu(username.getText().toString());
                onLogin(v,username.getText().toString(),password.getText().toString());

            }
        });
reg= (Button) rootView.findViewById(R.id.btndr);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.activity_main,new Refragment()).commit();
            }
        });
        return rootView;

    }

    public void onLogin(View v,String username,String password)
    {int a=1;
        String url = "http://192.168.1.113:8080/hello/DataServiceServlet?key=1&username="+username+"&password="+password;
       HttpThread ht= new HttpThread(url ,a);

ht.start();
    }



}
