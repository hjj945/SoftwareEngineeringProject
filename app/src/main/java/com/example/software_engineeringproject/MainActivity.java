package com.example.software_engineeringproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button to_CreateAccount_btn;
    private EditText Id_et;
    private EditText Password_et;
    //账号，密码
    private String Id;
    private String Password;
    private String Message;
    //socket初始化
    private Socket socket;
    private OutputStream os;
    private InputStream is;
    private BufferedReader bufReader;
    private InputStreamReader reader;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Start_btn=(Button)findViewById(R.id.Start_btn);
        to_CreateAccount_btn=(Button)findViewById(R.id.to_CreateAccount_btn);
        Id_et=(EditText)findViewById(R.id.Id_et);
        Password_et=(EditText)findViewById(R.id.Password_et);

        //socket测试
        new Thread() {
            public void run(){
                super.run();
                try {
                    Looper.prepare();
                    Log.e("socket", "start sending message");
                    socket = new Socket("84.32.16.105", 12345);
                    os = socket.getOutputStream();
                    is = socket.getInputStream();
                    //解析服务器返回的数据
                    reader = new InputStreamReader(is);
                    bufReader = new BufferedReader(reader);
                    s = null;
                    Toast.makeText(getApplicationContext(),"12345",Toast.LENGTH_SHORT).show();
                    while((s = bufReader.readLine()) != null) {
                        Log.e("socket", s);
                        //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                    }
                    Looper.loop();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        //点击创建用户按钮后跳转至创建用户界面
        to_CreateAccount_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        Id_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null)
                    Id=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Password_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null)
                    Password=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

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
                            Message="{id:"+Id+",password:"+Password+"}";
                            os.write(Message.getBytes());
                            os.flush();
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
        /*
        new Thread(){
            public void run(){
                super.run();
                try {
                    os.write("Finish".getBytes());
                    os.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
         */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new Thread(){
            public void run(){
                super.run();
                Log.e("socket", "finish sending message");
                //关闭IO资源
                try {
                    os.write("Finish".getBytes());
                    os.flush();

                    bufReader.close();
                    reader.close();
                    is.close();
                    os.close();
                    socket.shutdownOutput();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
