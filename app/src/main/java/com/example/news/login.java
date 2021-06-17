package com.example.news;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class login extends AppCompatActivity {
    private Button reg;
    private Button login;
    private EditText count;
    private EditText pwd;
    private TextView state;
    private List<User> userList;
    private List<User> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        reg= (Button) findViewById(R.id.btn_reg);
        login= (Button) findViewById(R.id.btn_login);
        count= (EditText) findViewById(R.id.et_username);
        pwd= (EditText) findViewById(R.id.et_password);
        state= (TextView) findViewById(R.id.state);

        //点击注册按钮跳转界面
        reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent2 =new Intent();
                intent2.setClass(com.example.news.login.this,register.class);
                login.this.startActivity(intent2);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=count.getText().toString().trim();
                String pass=pwd.getText().toString().trim();
                //userList=SqliteDB.getInstance(getApplicationContext()).loadUser();
                int result=SqliteDB.getInstance(getApplicationContext()).Quer(pass,name);
                if (result==1)
                {
                    state.setText("登录成功！");
                    Intent intent3 =new Intent();
                    intent3.setClass(com.example.news.login.this,firstpage.class);
                    login.this.startActivity(intent3);
                }
                else if (result==0){
                    state.setText("用户名不存在！");

                }
                else if(result==-1)
                {
                    state.setText("密码错误！");
                }


            }
        });
    }


}

