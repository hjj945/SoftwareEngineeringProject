package com.example.software_engineeringproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    TextView userActivity_tv;
    EditText userActivity_et;
    Button userActivity_btn;
    mySocket mysocket;

    String data;
    String target_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userActivity_tv=(TextView)findViewById(R.id.userActivity_tv);
        userActivity_et=(EditText)findViewById(R.id.userActivity_et);
        userActivity_btn=(Button)findViewById(R.id.userActivity_btn);

        mysocket=new mySocket();

        userActivity_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                data=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        userActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mysocket.finish_sending();
    }
}

