package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.newsapp.adapter.MyFragmentPagerAdapter;
import com.example.newsapp.fragment.FindFragment;
import com.example.newsapp.fragment.NewsFragment;
import com.example.newsapp.fragment.PersonalFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager2 viewPager;
    private LinearLayout llNews,llFind,llPersonal;
    ImageView ivNews,ivFind,ivPersonal,ivCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPager();
        iniTabView();

    }

    private void iniTabView() {
        llNews = findViewById(R.id.id_tab_news);
        llNews.setOnClickListener(this);
        llFind = findViewById(R.id.id_tab_find);
        llFind.setOnClickListener(this);
        llPersonal = findViewById(R.id.id_tab_personal);
        llPersonal.setOnClickListener(this);

        ivNews = findViewById(R.id.tab_iv_news);
        ivFind = findViewById(R.id.tab_iv_find);
        ivPersonal = findViewById(R.id.tab_iv_personal);

        ivNews.setSelected(true);
        ivCurrent = ivNews; //保存当前的按钮
    }

    private void initPager() {
        viewPager = findViewById(R.id.id_viewPager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(NewsFragment.newInstance());
        fragments.add(FindFragment.newInstance());
        fragments.add(PersonalFragment.newInstance());

        MyFragmentPagerAdapter PagerAdapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager(),getLifecycle(),fragments);
        viewPager.setAdapter(PagerAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }

        });
    }

    private void changeTab(int position) {
        ivCurrent.setSelected(false);//滑动时先复位
        switch (position){
            case R.id.id_tab_news :
                viewPager.setCurrentItem(0);
            case 0 :
                ivNews.setSelected(true);
                ivCurrent = ivNews;//赋值
                break;
            case R.id.id_tab_find :
                viewPager.setCurrentItem(1);
            case 1 :
                ivFind.setSelected(true);
                ivCurrent = ivFind;
                break;
            case R.id.id_tab_personal :
                viewPager.setCurrentItem(2);
            case 2 :
                ivPersonal.setSelected(true);
                ivCurrent=ivPersonal;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        changeTab(v.getId());//获取id
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int mFlag = intent.getIntExtra("flag", 0);
        if (mFlag == 2) { // 返回到个人中心
            changeTab(mFlag);
        }
    }
}