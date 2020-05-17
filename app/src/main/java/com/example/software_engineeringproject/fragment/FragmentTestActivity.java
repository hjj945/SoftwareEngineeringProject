package com.example.software_engineeringproject.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.software_engineeringproject.R;

public class FragmentTestActivity extends AppCompatActivity {

    private Button fragmentChange_btn;
    private Fragment_test1 fragmentTest1;
    //private Fragment_test2 fragmentTest2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);

        //fragmentChange_btn=(Button)findViewById(R.id.FragmentChange_btn);

        fragmentTest1=new Fragment_test1();
        //fragmentTest2=new Fragment_test2();

        //getSupportFragmentManager().beginTransaction().add(R.id.fl_container,fragmentTest1).commitAllowingStateLoss();

        /*
        fragmentChange_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,fragmentTest2).addToBackStack(null).commitAllowingStateLoss();
            }
        });

         */

    }
}