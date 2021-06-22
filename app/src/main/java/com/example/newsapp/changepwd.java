package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class changepwd extends AppCompatActivity {
    private  EditText cg_name;
    private EditText cg_pwd;
    private EditText cg_surepwd;
    private Button cg_sure;
    private TextView cg_backhome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepwd);

        cg_name=findViewById(R.id.cg_name);
        cg_pwd=findViewById(R.id.cg_pwd);
        cg_surepwd=findViewById(R.id.cg_surepwd);
        cg_sure=findViewById(R.id.cg_sure);
        cg_backhome=findViewById(R.id.cg_backhome);


        cg_backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 =new Intent();
                intent2.setClass(com.example.newsapp.changepwd.this,personal.class);
                changepwd.this.startActivity(intent2);
            }
        });
    }
}
