package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.newsapp.db.SqliteDB;


public class NewsInfoActivity extends AppCompatActivity {

    private ImageView ivImg;
    private TextView tvTitle;
    private TextView tvDate;
    private WebView webView;
    private boolean isCollection = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        initViews();
    }

    /**
     * 初始化数据
     */
    public void initViews() {
        ivImg = (ImageView) this.findViewById(R.id.iv_img);
        tvTitle = (TextView) this.findViewById(R.id.tv_title);
        tvDate = (TextView) this.findViewById(R.id.tv_date);
        webView = (WebView) this.findViewById(R.id.wv_content);
        ImageView ivCollect = findViewById(R.id.iv_collect);

        /**
         * 获得传递过来的数据
         */
        Intent intent = this.getIntent();
        String newsTitle = intent.getStringExtra("newsTitle");
        String newsDate = intent.getStringExtra("newsDate");
        String newsImgUrl = intent.getStringExtra("newsImgUrl");
        String newsUrl = intent.getStringExtra("newsUrl");
        SqliteDB sqliteDB = SqliteDB.getInstance(this);
        String string = getSharedPreferences("user", MODE_PRIVATE).getString("name", "");

        if (sqliteDB.isCollection(newsTitle, string) > 0) {
            isCollection=true;
            ivCollect.setImageResource(R.drawable.collect_select);
        } else {
            isCollection=false;
            ivCollect.setImageResource(R.drawable.collect_normal);
        }

        getImage(this, newsImgUrl, ivImg);

        /**
         * 显示新闻信息
         */
        tvTitle.setText(newsTitle);
        tvDate.setText(newsDate);
        webView.loadUrl(newsUrl);
        ivCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCollection){
                    sqliteDB.deleteCollection(newsTitle,string);
                    ivCollect.setImageResource(R.drawable.collect_normal);
                    isCollection=false;

                }else {
                    ivCollect.setImageResource(R.drawable.collect_select);
                    sqliteDB.insertCollection(newsTitle,newsDate,newsImgUrl,newsUrl,string);
                    isCollection=true;
                }
            }
        });
    }


    /**
     * 加载网络图片
     */
    public void getImage(Context context, String imgUrl,
                         final ImageView imageView) {

        RequestQueue mQueue = Volley.newRequestQueue(context);
        ImageRequest imageRequest = new ImageRequest(imgUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        mQueue.add(imageRequest);
    }

}