package com.example.software_engineeringproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    //各控件初始化
    private Button Start_btn;
    private Button CreateAccount_btn;
    private EditText Id_et;
    private EditText Password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Start_btn=(Button)findViewById(R.id.Start_btn);
        CreateAccount_btn=(Button)findViewById(R.id.CreateAccount_btn);
        Id_et=(EditText)findViewById(R.id.Id_et);
        Password_et=(EditText)findViewById(R.id.Password_et);

        //socket功能测试代码

        //socket功能测试结束

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
            UdpTest udptest=new UdpTest();
            udptest.get_message("hello!");
                try {
                    udptest.send_message();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
