package com.example.software_engineeringproject.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.software_engineeringproject.R;
import com.example.software_engineeringproject.mySocket;
import com.example.software_engineeringproject.depends;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Fragment_Contacts extends Fragment {

    private TextView fragment_contacts_tv;
    private TextView fragment_contacts_tv2;
    private Button fragment_contacts_btn;
    private String id;
    private String target_id;
    private String[] contacts_list={};
    private mySocket mysocket;
    private depends dp;
    private String msg;

    BufferedReader bufReader;
    InputStreamReader reader;
    String s;
    InputStream is;

    //重写构造函数，用于在调用Fragment时传递参数
    public static Fragment_Contacts newInstance(String data){
        Fragment_Contacts fragment=new Fragment_Contacts();
        Bundle bundle=new Bundle();
        bundle.putString("data",data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_contacts,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragment_contacts_tv=view.findViewById(R.id.fragment_contacts_tv);
        fragment_contacts_tv2=view.findViewById(R.id.fragment_contacts_tv2);
        fragment_contacts_btn=view.findViewById(R.id.fragment_message_btn);

        if(getArguments()!=null){
            id=getArguments().getString("data");
        }
        dp=new depends();
        fragment_contacts_tv.setText("好友列表");

        new Thread(){
            public void run(){
                super.run();
                try{
                    mysocket=new mySocket();
                    Thread.sleep(1000);
                    msg=dp.Create_msg_for_client(1,"get_friends_info",id,null,null);
                    mysocket.send_msg(msg);
                    s = null;
                    is=mysocket.receive_msg();
                    //解析服务器返回的数据
                    reader = new InputStreamReader(is);
                    bufReader = new BufferedReader(reader);
                    while((s = bufReader.readLine()) != null) {
                        Log.e("socket", s);
                        JSONObject jo=new JSONObject((new String(s)));
                        String t=jo.getString("data");
                        //fragment_contacts_tv2.setText(t);
                        fragment_contacts_tv2.setText(dp.function(t));
                        Log.e("data", t);
                    }
                } catch (JSONException | InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

/*
        fragment_contacts_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    msg=dp.Create_msg_for_client(1,"get_friends_info",id,null,null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mysocket.send_msg(msg);
            }
        });
*/

        /*
        final SpannableStringBuilder style =new SpannableStringBuilder();
        style.append(id);
        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Log.e( "onClick: ","aaaa" );
            }
        };
        style.setSpan(clickableSpan,0,2,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        fragment_contacts_tv2.setText(style);
        ForegroundColorSpan foregroundColorSpan =new ForegroundColorSpan(Color.parseColor("#0000FF"));
        style.setSpan(foregroundColorSpan,2,2,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        fragment_contacts_tv2.setMovementMethod(LinkMovementMethod.getInstance());
        fragment_contacts_tv2.setText(style);
        */
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mysocket.finish_sending();
    }

}
