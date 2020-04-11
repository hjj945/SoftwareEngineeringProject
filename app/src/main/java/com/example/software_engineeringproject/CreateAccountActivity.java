package com.example.software_engineeringproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        mysocket.receive_msg();
/*
        new Thread(){
            public void run(){
                super.run();
                try {
                    reader = new InputStreamReader(is);
                    bufReader = new BufferedReader(reader);
                    s = null;
                    while((s = bufReader.readLine()) != null) {
                        Log.e("socket", s);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
 */

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
