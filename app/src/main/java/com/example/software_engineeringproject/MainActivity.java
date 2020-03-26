package com.example.software_engineeringproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    //各控件初始化
    private Button Start_btn;
    private Button CreateAccount_btn;
    private EditText Id_et;
    private EditText Password_et;

    private  Socket socket;
    private OutputStream os;
    private StringBuffer sb;
    private InputStream is;
    private BufferedReader bufReader;
    private InputStreamReader reader;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Start_btn=(Button)findViewById(R.id.Start_btn);
        CreateAccount_btn=(Button)findViewById(R.id.CreateAccount_btn);
        Id_et=(EditText)findViewById(R.id.Id_et);
        Password_et=(EditText)findViewById(R.id.Password_et);

        //socket测试
        new Thread() {
            public void run(){
                super.run();
                try {
                    //1.创建监听指定服务器地址以及指定服务器监听的端口号
                    Log.e("socket", "start sending message");
                    socket = new Socket("84.32.16.105", 12345);
                    os = socket.getOutputStream();
                    //2.拿到客户端的socket对象的输出流发送给服务器数据
                    //OutputStream os = socket.getOutputStream();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        //点击创建用户按钮后跳转至创建用户界面
        CreateAccount_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        Start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run(){
                        super.run();
                        try {
                            Log.e("socket","sending message");
                            os.write("Udp_Message".getBytes());
                            os.flush();
                            /*
                            //拿到socket的输入流，这里存储的是服务器返回的数据
                            is = socket.getInputStream();
                            //解析服务器返回的数据
                            reader = new InputStreamReader(is);
                            bufReader = new BufferedReader(reader);
                            s = null;
                            sb = new StringBuffer();
                            while((s = bufReader.readLine()) != null){
                                sb.append(s);
                            }
                             */
                            /*
                            //1.创建监听指定服务器地址以及指定服务器监听的端口号
                            Log.e("socket", "start sending message");
                            Socket socket = new Socket("84.32.16.105", 12345);
                            //2.拿到客户端的socket对象的输出流发送给服务器数据
                            OutputStream os = socket.getOutputStream();
                            //写入要发送给服务器的数据
                            os.write("Udp_Message".getBytes());
                            os.flush();
                            socket.shutdownOutput();
                            //拿到socket的输入流，这里存储的是服务器返回的数据
                            InputStream is = socket.getInputStream();
                            //解析服务器返回的数据
                            InputStreamReader reader = new InputStreamReader(is);
                            BufferedReader bufReader = new BufferedReader(reader);
                            String s = null;
                            final StringBuffer sb = new StringBuffer();
                            while((s = bufReader.readLine()) != null){
                                sb.append(s);
                            }
                            Log.e("socket", sb.toString());
                            //3、关闭IO资源（注：实际开发中需要放到finally中）
                            bufReader.close();
                            reader.close();
                            is.close();
                            os.close();
                            socket.close();
                            Log.e("socket", "finish sending message");
                             */
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("socket", "finish sending message");
        //3、关闭IO资源（注：实际开发中需要放到finally中）
        try {
            /*
            bufReader.close();
            reader.close();
            is.close();
            */
            socket.shutdownOutput();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
