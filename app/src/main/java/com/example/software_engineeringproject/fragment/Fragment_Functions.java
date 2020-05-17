package com.example.software_engineeringproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.software_engineeringproject.R;

public class Fragment_Functions extends Fragment {

    private Button btn1,btn2,btn3,btn4;
    private Fragment_Chessbox fragment_chessbox=new Fragment_Chessbox();

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
        View view=inflater.inflate(R.layout.fragment_functions,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn1=view.findViewById(R.id.fragment_functions_btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.User_fl_Container,fragment_chessbox).addToBackStack(null).commitAllowingStateLoss();
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
