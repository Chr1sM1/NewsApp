package com.example.news;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {
    private Button reg000;
    private EditText regname;
    private EditText regpwd;
    private TextView hint;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        reg000=findViewById(R.id.reg000);
        regname=findViewById(R.id.regname);
        regpwd=findViewById(R.id.regpwd);
        hint=findViewById(R.id.hint);

        reg000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        String name=regname.getText().toString().trim();
        String pass=regpwd.getText().toString().trim();

        User user=new User();
        user.setUsername(name);
        user.setUserpwd(pass);

        int result=SqliteDB.getInstance(getApplicationContext()).saveUser(user);
        if (result==1){

            hint.setText("注册成功！");
        }else  if (result==-1)
        {
            hint.setText("用户名已经存在！");
        }
//        else
//        {
//            Toast.makeText(register.getContext(),"！",Toast.LENGTH_SHORT).show();
//            //hint.setText("！");
//        }


            }
        });

    }


}
