package com.example.software_engineeringproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    private Button user_message_btn;
    private Button user_contacts_btn;
    private Button user_functions_btn;
    private Button user_test_btn;
    private TextView user_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        user_message_btn=findViewById(R.id.User_message_btn);
        user_contacts_btn=findViewById(R.id.User_contacts_btn);
        user_functions_btn=findViewById(R.id.User_functions_btn);
        user_test_btn=findViewById(R.id.User_test_btn);
        user_tv=findViewById(R.id.User_tv);
    }
}
