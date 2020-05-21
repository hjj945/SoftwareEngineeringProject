package com.example.software_engineeringproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.software_engineeringproject.R;
import com.example.software_engineeringproject.depends;
import com.example.software_engineeringproject.mySocket;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Fragment_Message extends Fragment {

    private TextView fragment_message_tv1;
    private TextView fragment_message_tv2;
    private EditText fragment_message_et;
    private Button fragment_message_btn;
    private String message;

    private mySocket mysocket;
    private depends dp;
    private String msg;
    private String id;
    private String target_id="7645";
    private String message_box;

    BufferedReader bufReader;
    InputStreamReader reader;
    String s;
    InputStream is;

    //重写构造函数，用于在调用Fragment时传递参数
    public static Fragment_Message newInstance(String data){
        Fragment_Message fragment=new Fragment_Message();
        Bundle bundle=new Bundle();
        bundle.putString("data",data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_message,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragment_message_tv1 = view.findViewById(R.id.fragment_message_tv1);
        fragment_message_tv2 = view.findViewById(R.id.fragment_message_tv2);
        fragment_message_et = view.findViewById(R.id.fragment_message_et);
        fragment_message_btn = view.findViewById(R.id.fragment_message_btn);

        dp=new depends();
        if (getArguments() != null) {
            id = getArguments().getString("data");
        }

        fragment_message_tv1.setText("消息");

        new Thread(){
            public void run(){
                super.run();
                try{
                    mysocket=new mySocket();
                    Thread.sleep(1000);
                    msg=dp.Create_msg_for_client(1,"update_message",id,null,null);
                    mysocket.send_msg(msg);
                    is=mysocket.receive_msg();
                    //解析服务器返回的数据
                    reader = new InputStreamReader(is);
                    bufReader = new BufferedReader(reader);
                    s = null;
                    while((s = bufReader.readLine()) != null) {
                        Log.e("socket", s);
                        JSONObject jo=new JSONObject((new String(s)));
                        String t=jo.getString("data");
                        message_box=dp.show_message(t);
                        fragment_message_tv2.setText(message_box);
                    }
                } catch (IOException | InterruptedException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        fragment_message_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                message=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fragment_message_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    public void run(){
                        super.run();
                        try {
                            msg=dp.Create_msg_for_client(1,"send_message",id,target_id,message);
                            mysocket.send_msg(msg);
                            is=mysocket.receive_msg();
                            //解析服务器返回的数据
                            reader = new InputStreamReader(is);
                            bufReader = new BufferedReader(reader);
                            s = null;
                            while((s = bufReader.readLine()) != null) {
                                Log.e("socket", s);
                                JSONObject jo=new JSONObject((new String(s)));
                                String t=jo.getString("data");
                                message_box+=dp.show_message(t);
                                fragment_message_tv2.setText(message_box);
                            }
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

    }
        @Override
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);
        }

        @Override
        public void onDetach() {
            super.onDetach();
        }

}
