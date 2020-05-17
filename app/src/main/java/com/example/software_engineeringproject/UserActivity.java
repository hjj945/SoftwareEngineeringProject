package com.example.software_engineeringproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.software_engineeringproject.fragment.Fragment_Chessbox;
import com.example.software_engineeringproject.fragment.Fragment_Contacts;
import com.example.software_engineeringproject.fragment.Fragment_Functions;
import com.example.software_engineeringproject.fragment.Fragment_Message;
import com.example.software_engineeringproject.fragment.Fragment_test1;

public class UserActivity extends AppCompatActivity {

    private Button user_message_btn;
    private Button user_contacts_btn;
    private Button user_functions_btn;
    private Button user_test_btn;
    private TextView user_tv;

    private Fragment_Message fragment_message;
    private Fragment_Contacts fragment_contacts;
    private Fragment_Functions fragment_functions;
    private Fragment_Chessbox fragment_chessbox;

    private Fragment currentFragment=new Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        user_message_btn=findViewById(R.id.User_message_btn);
        user_contacts_btn=findViewById(R.id.User_contacts_btn);
        user_functions_btn=findViewById(R.id.User_functions_btn);
        user_test_btn=findViewById(R.id.User_test_btn);
        user_tv=findViewById(R.id.User_tv);

        fragment_message=new Fragment_Message();
        fragment_contacts=new Fragment_Contacts();
        fragment_functions=new Fragment_Functions();
        fragment_chessbox=new Fragment_Chessbox();

        //getSupportFragmentManager().beginTransaction().add(R.id.User_fl_Container,Fragment_Message.newInstance("hello,world")).commitAllowingStateLoss();
        getSupportFragmentManager().beginTransaction().add(R.id.User_fl_Container, fragment_chessbox).commitAllowingStateLoss();
        user_contacts_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.User_fl_Container, Fragment_Contacts.newInstance("Hello,World!")).addToBackStack(null).commitAllowingStateLoss();
            }
        });

        user_functions_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.User_fl_Container, fragment_functions).addToBackStack(null).commitAllowingStateLoss();
            }
        });

        user_message_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.User_fl_Container, Fragment_Message.newInstance("Hello,World!")).addToBackStack(null).commitAllowingStateLoss();
            }
        });

        user_test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getSupportFragmentManager().beginTransaction().replace(R.id.User_fl_Container, Fragment_Message.newInstance("Hello,World!")).addToBackStack(null).commitAllowingStateLoss();
            }
        });

    }

    //切换fragment
    private FragmentTransaction switchFragment(Fragment targetFragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        if(!targetFragment.isAdded()){
            if(targetFragment!=null){
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.User_fl_Container, targetFragment,targetFragment.getClass().getName());
        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        currentFragment=targetFragment;
        return  transaction;
    }

}
