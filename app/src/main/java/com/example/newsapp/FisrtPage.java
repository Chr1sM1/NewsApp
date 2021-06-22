package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;


public class FisrtPage extends AppCompatActivity {
    private RelativeLayout firstlogin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);

        firstlogin = findViewById(R.id.enter);

        firstlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =new Intent();
                intent1.setClass(FisrtPage.this,login.class);
                FisrtPage.this.startActivity(intent1);
            }
        });
    }



}