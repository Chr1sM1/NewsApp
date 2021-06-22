package com.example.newsapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.db.SqliteDB;

import java.util.ArrayList;
import java.util.List;

public class login extends AppCompatActivity {
    private Button reg;
    private Button login;
    private EditText count;
    private EditText pwd;
    private List<User> userList;
    private List<User> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    //使用findviewbyId找到按钮
        reg= (Button) findViewById(R.id.btn_reg);
        login= (Button) findViewById(R.id.btn_login);
        count= (EditText) findViewById(R.id.et_username);
        pwd= (EditText) findViewById(R.id.et_password);


        //点击注册按钮跳转界面
        reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent2 =new Intent();
                intent2.setClass(com.example.newsapp.login.this,register.class);
                login.this.startActivity(intent2);
            }
        });
            //login按钮的点击事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=count.getText().toString().trim();
                String pass=pwd.getText().toString().trim();
                int result= SqliteDB.getInstance(getApplicationContext()).Quer(pass,name);
                if (result==1)
                {
                    Toast.makeText(login.getContext(),"登陆成功",Toast.LENGTH_SHORT).show();
                    //state.setText("登录成功！");
                    Intent intent3 =new Intent();
                    intent3.setClass(com.example.newsapp.login.this,MainActivity.class);
                    login.this.startActivity(intent3);
                }
                else if (result==0){
                    Toast.makeText(login.getContext(),"用户名不存在",Toast.LENGTH_SHORT).show();
                    //state.setText("用户名不存在！");

                }
                else if(result==-1)
                {
                    Toast.makeText(login.getContext(),"密码错误",Toast.LENGTH_SHORT).show();
                    //state.setText("密码错误！");
                }
            }


        });
    }


}

