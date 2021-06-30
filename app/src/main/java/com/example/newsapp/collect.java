package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.bean.NewsData;
import com.example.newsapp.db.SqliteDB;

import java.util.ArrayList;
import java.util.List;

public class collect extends AppCompatActivity {
    /**
     * ListView对象
     */
    private ListView listView;

    /**
     * 自定义的Adapter对象
     */
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //设置全屏幕
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        listView = findViewById(R.id.recyclerView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        String string = getSharedPreferences("user", MODE_PRIVATE).getString("name", "");

        List<NewsData> allCollection = SqliteDB.getInstance(this).getAllCollection(string);


        /**
         * 实例化Adapter对象(注意:必须要写在在getDatas() 方法后面,不然datas中没有数据)
         */
        newsAdapter = new NewsAdapter(this, allCollection);
        listView.setAdapter(newsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {

                /**
                 * 创建一个意图
                 */
                Intent intent = new Intent(collect.this, NewsInfoActivity.class);

                /**
                 * 在datas中通过点击的位置position通过get()方法获得具体某个新闻
                 * 的数据然后通过Intent的putExtra()传递到NewsInfoActivity中
                 */
                intent.putExtra("newsTitle", allCollection.get(position).getNewsTitle());
                intent.putExtra("newsDate", allCollection.get(position).getNewsDate());
                intent.putExtra("newsImgUrl", allCollection.get(position).getNewsImgUrl());
                intent.putExtra("newsUrl", allCollection.get(position).getNewsUrl());

                startActivity(intent);//启动Activity

            }
        });
    }
}
