package com.example.software_engineeringproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CreateAccountActivity extends AppCompatActivity {

    Button back_to_mainActivity_btn;
    Button CreateAccount_btn;
    EditText create_account_id_et;
    EditText Create_account_password_et;
    EditText Create_account_password_confirm_et;

    String id;
    String password;
    String password_confirm;

    BufferedReader bufReader;
    InputStreamReader reader;
    String s;
    InputStream is;

    mySocket mysocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        back_to_mainActivity_btn=(Button)findViewById(R.id.back_to_mainActivity_btn);
        CreateAccount_btn=(Button)findViewById(R.id.CreateAccount_btn);
        create_account_id_et=(EditText)findViewById(R.id.create_account_id_et);
        Create_account_password_et=(EditText)findViewById(R.id.create_account_password_et);
        Create_account_password_confirm_et=(EditText)findViewById(R.id.create_account_password_confirm_et);

        mysocket=new mySocket("84.32.16.105",12345);

        back_to_mainActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CreateAccountActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        CreateAccount_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message="";
                depends dp=new depends();
                if(true/*password!=null&&password==password_confirm*/){
                    try {
                        message=dp.Create_msg_for_client(1,"create_account",id,null,password);
                        mysocket.send_msg(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                new Thread(){
                    public void run(){
                        super.run();
                        Looper.prepare();
                        try{
                            is=mysocket.receive_msg();
                            //解析服务器返回的数据
                            reader = new InputStreamReader(is);
                            bufReader = new BufferedReader(reader);
                            s = null;
                            while((s = bufReader.readLine()) != null) {
                                Log.e("socket", s);
                                if(s=="0"){
                                    Toast.makeText(getApplicationContext(),"用户已经存在!",Toast.LENGTH_SHORT).show();
                                }else if(s=="1"){
                                    Toast.makeText(getApplicationContext(),"用户创建成功!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Looper.loop();
                    }
                }.start();
            }
        });

        create_account_id_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                id=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Create_account_password_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Create_account_password_confirm_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password_confirm=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mysocket.finish_sending();
    }
}
