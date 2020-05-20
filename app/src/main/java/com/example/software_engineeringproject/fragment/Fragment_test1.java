package com.example.software_engineeringproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.software_engineeringproject.mySocket;

import java.io.Serializable;
import java.net.Socket;

public class Fragment_test1 extends Fragment {

    private TextView test1_tv;
    private EditText test1_et;
    private Button sendMsg_btn;
    private String text;
    private String target;

    //重写构造函数，用于在调用Fragment时传递参数
    public static Fragment_test1 newInstance(String data){
        Fragment_test1 fragment_test=new Fragment_test1();
        Bundle bundle=new Bundle();
        bundle.putString("data",data);
        fragment_test.setArguments(bundle);
        return fragment_test;
    }

    public static Fragment_test1 newInstance(mySocket mysocket){
        Fragment_test1 fragment_test=new Fragment_test1();
        Bundle bundle=new Bundle();
        bundle.putSerializable("socket", (Serializable) mysocket);
        fragment_test.setArguments(bundle);
        return fragment_test;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.test1,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        test1_tv=(TextView)view.findViewById(R.id.test1_tv);
        test1_et=(EditText)view.findViewById(R.id.test1_et);
        sendMsg_btn=(Button)view.findViewById(R.id.sendMsg_btn);

        if(getArguments()!=null){
            target=getArguments().getString("data");
        }

        test1_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                text+=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        test1_tv.setTextIsSelectable(true);

        sendMsg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test1_et.setText("");
                text+="\n";
                test1_tv.setText(text);
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
