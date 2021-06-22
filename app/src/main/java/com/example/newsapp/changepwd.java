package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.db.SqliteDB;
import com.example.newsapp.fragment.PersonalFragment;

import java.util.ArrayList;

public class changepwd extends AppCompatActivity {
    private  EditText cg_name;
    private EditText cg_oldpwd;
    private EditText cg_newpwd;
    private Button cg_sure;
    private TextView cg_backhome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepwd);

        cg_name=findViewById(R.id.cg_name);
        cg_oldpwd=findViewById(R.id.cg_oldpwd);
        cg_newpwd=findViewById(R.id.cg_newpwd);
        cg_sure=findViewById(R.id.cg_sure);
        cg_backhome=findViewById(R.id.cg_backhome);


        cg_backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 =new Intent();
                intent2.setClass(com.example.newsapp.changepwd.this, PersonalFragment.class);
                changepwd.this.startActivity(intent2);

                    //更新密码
                String oldpass = cg_oldpwd.getText().toString().trim();
                String newpass = cg_newpwd.getText().toString().trim();
                String name = cg_name.getText().toString().trim();
                if (TextUtils.isEmpty(oldpass)||TextUtils.isEmpty(newpass)||TextUtils.isEmpty(name)){
                    Toast.makeText(cg_sure.getContext(),"选项不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<User> data = SqliteDB.getAllDATA();
                boolean userdata = false;
                for (int i = 0; i < data.size(); i++) {
                    User user = data.get(i);   //可存储账号数量
                    if (name.equals(user.getUsername()) && oldpass.equals(user.getUserpwd())) {
                        userdata = true;
                        break;
                    } else {
                        userdata = false;
                    }
                }

                if(userdata){
                    SqliteDB.updata(name,newpass);
                    Toast.makeText(cg_sure.getContext(),"修改成功",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(changepwd.this, PersonalFragment.class);
                    startActivity(intent1);
                    finish();
                }else {
                    Toast.makeText(cg_sure.getContext(),"用户名或旧密码不正确",Toast.LENGTH_SHORT).show();
                }

                if (userdata==false){
                    Toast.makeText(cg_sure.getContext(),"用户名或旧密码不正确",Toast.LENGTH_SHORT).show();
                }else if (newpass.equals(oldpass)){
                    Toast.makeText(cg_sure.getContext(),"新密码和旧密码不能一致",Toast.LENGTH_SHORT).show();
                }else if(userdata==true){
                    SqliteDB.updata(name,newpass);
                    Toast.makeText(cg_sure.getContext(),"修改成功",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(changepwd.this, PersonalFragment.class);
                    startActivity(intent1);
                    finish();
                }



            }


        });
    }
}
