package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.bean.User;
import com.example.newsapp.db.SqliteDB;

public class register extends AppCompatActivity {
    private Button reg000;
    private EditText regname;
    private EditText regpwd;
    private TextView backlog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        reg000=findViewById(R.id.reg000);
        regname=findViewById(R.id.regname);
        regpwd=findViewById(R.id.regpwd);
        backlog=findViewById(R.id.backlog);

        backlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(register.this,login.class);
                startActivity(intent);
            }
        });

        reg000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=regname.getText().toString().trim();
                String pass=regpwd.getText().toString().trim();

                User user=new User();
                user.setUsername(name);
                user.setUserpwd(pass);

                int result= SqliteDB.getInstance(getApplicationContext()).saveUser(user);
                if (result==1){
                    Toast.makeText(reg000.getContext(),"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent();
                    intent.setClass(register.this,login.class);
                    startActivity(intent);

                }else  if (result==-1)
                {
                    Toast.makeText(reg000.getContext(),"用户名已经存在！",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}


