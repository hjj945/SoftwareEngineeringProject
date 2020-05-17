package com.example.software_engineeringproject.fragment;

import android.content.Context;
import android.os.Bundle;
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

public class Fragment_Message extends Fragment {

    private TextView fragment_message_tv1;
    private TextView fragment_message_tv2;
    private EditText fragment_message_et;
    private Button fragment_message_btn;

    private String msg;

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

        if (getArguments() != null) {
            msg = getArguments().getString("data");
            fragment_message_tv2.setText(msg);
        }

        fragment_message_tv1.setText("消息");
        /*
        //更新消息
        new Thread(){
            public void run(){
                super.run();

            }
        }.start();
         */
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
