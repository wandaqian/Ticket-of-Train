package com.example.huoche;

/**
 * Created by 82667 on 2016/9/8.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.R.string;
import android.os.Handler;
import android.os.Message;

public class HttpThread extends Thread {
    String url;
    HttpURLConnection conn;
    int i;
    List<Map<String, Object>> list;
    Message ms = new Message();

    public HttpThread(String url, int a) {
        // TODO Auto-generated constructor stub
        this.url = url;
        i = a;
    }


    private void doGet() throws IOException {
        /*将username和password传给Tomcat服务器*/

        try {
            URL httpUrl = new URL(url);
            /*获取网络连接*/
            conn = (HttpURLConnection) httpUrl.openConnection();
            /*设置请求方法为GET方法*/
            conn.setRequestMethod("GET");
            /*设置访问超时时间*/
            conn.setReadTimeout(5000);
            switch (i) {
                case 1:
                    onlog(conn);
                    break;
                case 2:
                    register(conn);
                    break;
                case 3:
                    oncha(conn);
                    break;
                case 4:
                    onchepiao(conn);
                    break;
                case 5:
                    ongoumai(conn);
                    break;
                case 6:
                    onyigo(conn);
                    break;
                case 7:
                    ontuipiao(conn);
                    break;
                case 8:
                    ongaiqian(conn);
                    break;
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /*在run中调用doGet*/
    @Override
    public void run() {
        try {
            doGet();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void onlog(HttpURLConnection connection) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String str;
            if ((str = reader.readLine()) != null) {
                int a = Integer.parseInt(str);
                ms.arg1 = 1;
                LogFragment.handler.sendMessage(ms);
            } else {
                ms.arg1 = 2;
                LogFragment.handler.sendMessage(ms);
                System.out.println("用户名或者密码错误");

            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public void register(HttpURLConnection connection) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String str;
            if ((str = reader.readLine()) != null) {
                ms.arg1 = 3;
                Refragment.handler.sendMessage(ms);
                System.out.println("注册成功");
            } else {
                ms.arg1 = 4;
                Refragment.handler.sendMessage(ms);
                System.out.println("注册失败");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public  void oncha(HttpURLConnection connection) {


        try {
            ObjectInputStream oiStream = new ObjectInputStream (connection.getInputStream());

            try {
            list= (List<Map<String, Object>>) oiStream.readObject();
                if (list.size()==0){
                    ms.arg1 = 5;
                    ChaFragment.handler.sendMessage(ms);
                }else {
                    ms.arg1 = 6;
                    ChaFragment.handler.sendMessage(ms);
                    Jieguo.setList(list);
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }
    public  void onchepiao(HttpURLConnection connection) {


        try {
            ObjectInputStream oiStream = new ObjectInputStream (connection.getInputStream());

            try {
                list= (List<Map<String, Object>>) oiStream.readObject();

                    ms.arg1 = 7;
                    Jieguo.handler.sendMessage(ms);
                    Chepiao.setList(list);


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }
    public  void ongoumai(HttpURLConnection connection) {


        try {
            ObjectInputStream oiStream = new ObjectInputStream (connection.getInputStream());

            try {
                list= (List<Map<String, Object>>) oiStream.readObject();
             if (list==null){
                 ms.arg1=9;
                 Chepiao.handler.sendMessage(ms);

             }else {
                 ms.arg1 = 8;
                 Chepiao.handler.sendMessage(ms);
                 Chepiao.setList(list);
             }



            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }
    public  void onyigo(HttpURLConnection connection) {


        try {
            ObjectInputStream oiStream = new ObjectInputStream (connection.getInputStream());

            try {
                list= (List<Map<String, Object>>) oiStream.readObject();
                if (list==null){
                    ms.arg1=10;
                    ChaFragment.handler.sendMessage(ms);
                }else {
                    ms.arg1 = 11;
                    System.out.println("2345");
                    ChaFragment.handler.sendMessage(ms);
                    YigoFragment.setList(list);
                }



            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }
    public  void ontuipiao(HttpURLConnection connection) {
        BufferedReader reader = null;


        try { reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
String ss=reader.readLine();
            if (ss.equals("5")) {
                ms.arg1 = 12;
                YigoFragment.handler.sendMessage(ms);
            } else {
                ms.arg1 = 13;
                YigoFragment.handler.sendMessage(ms);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void ongaiqian(HttpURLConnection connection) {

        try {
            ObjectInputStream oiStream = new ObjectInputStream (connection.getInputStream());

            try {
                list= (List<Map<String, Object>>) oiStream.readObject();

                    ms.arg1 = 14;

                    YigoFragment.handler.sendMessage(ms);
                    Jieguo.setList(list);
                System.out.println("2");



            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }
}
