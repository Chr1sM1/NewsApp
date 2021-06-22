package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class personal extends AppCompatActivity {
    private RelativeLayout collect;
    private  RelativeLayout change;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal);

        collect=findViewById(R.id.rl_collect);
        change=findViewById(R.id.rl_settings);

        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(com.example.newsapp.personal.this,collect.class);
                personal.this.startActivity(intent);
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(com.example.newsapp.personal.this,changepwd.class);
                personal.this.startActivity(intent);
            }
        });


    }


}
