package com.example.newsapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.newsapp.NewsInfoActivity;
import com.example.newsapp.R;
import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.bean.NewsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class NewsFragment extends Fragment {

    private View mView;
    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;

    /**
     * 新闻列表请求接口
     */
    public static final String URL = "http://v.juhe.cn/toutiao/index?type=top&key=b70c114114f788be802d976c6f2b631f";

    /**
     * ListView对象
     */
    private ListView listView;

    /**
     * 新闻集合对象
     */
    private List<NewsData> datas;

    /**
     * 自定义的Adapter对象
     */
    private NewsAdapter newsAdapter;

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.drawable.banner_01,
            R.drawable.banner_02,
            R.drawable.banner_03,
            R.drawable.banner_04,
            R.drawable.banner_05,
            R.drawable.banner_06
    };
    //存放图片的标题
    private String[] titles = new String[]{
            "",
            "",
            "",
            "",
            "",
            ""
    };
    private TextView title;
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView=inflater.inflate(R.layout.fragment_news, null);
        setView();
        setListView();
        return mView;
    }

    private void setListView() {
        listView = (ListView) mView.findViewById(R.id.lv_news);

        datas = new ArrayList<NewsData>();

        getDatas(URL);

        /**
         * 实例化Adapter对象(注意:必须要写在在getDatas() 方法后面,不然datas中没有数据)
         */
        newsAdapter = new NewsAdapter(getActivity(), datas);
    }

    /**
     * 通过接口获取新闻列表的方法
     * @param url
     */

    private void getDatas(String url) {
        final RequestQueue mQueue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest stringRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        try {

                            /**
                             * 对返回的json数据进行解析,然后装入datas集合中
                             */
                            JSONObject jsonObject2 = jsonObject.getJSONObject("result");
                            JSONArray jsonArray = jsonObject2.getJSONArray("data");

                            for (int i = 0; i <jsonArray.length() ; i++) {
                                JSONObject item = jsonArray.getJSONObject(i);
                                NewsData data = new NewsData();
                                data.setNewsTitle(item.getString("title"));
                                data.setNewsDate(item.getString("date"));
                                data.setNewsImgUrl(item.getString("thumbnail_pic_s"));
                                data.setNewsUrl(item.getString("url"));
                                datas.add(data);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        /**
                         * 请求成功后为ListView设置Adapter
                         */
                        listView.setAdapter(newsAdapter);
                        newsAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }
        );

        mQueue.add(stringRequest);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {

                /**
                 * 创建一个意图
                 */
                Intent intent = new Intent(getActivity(), NewsInfoActivity.class);

                /**
                 * 在datas中通过点击的位置position通过get()方法获得具体某个新闻
                 * 的数据然后通过Intent的putExtra()传递到NewsInfoActivity中
                 */
                intent.putExtra("newsTitle", datas.get(position).getNewsTitle());
                intent.putExtra("newsDate", datas.get(position).getNewsDate());
                intent.putExtra("newsImgUrl", datas.get(position).getNewsImgUrl());
                intent.putExtra("newsUrl", datas.get(position).getNewsUrl());

                startActivity(intent);//启动Activity

            }
        });
    }

    private void setView(){
        mViewPaper = (ViewPager)mView.findViewById(R.id.vp);

        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i = 0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的小点
        dots = new ArrayList<View>();
        dots.add(mView.findViewById(R.id.dot_0));
        dots.add(mView.findViewById(R.id.dot_1));
        dots.add(mView.findViewById(R.id.dot_2));
        dots.add(mView.findViewById(R.id.dot_3));
        dots.add(mView.findViewById(R.id.dot_4));
        dots.add(mView.findViewById(R.id.dot_5));

        title = (TextView) mView.findViewById(R.id.title);
        title.setText(titles[0]);

        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);

        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.drawable.shape_dot_selected);
                dots.get(oldPosition).setBackgroundResource(R.drawable.shape_dot);

                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }

    /*定义的适配器*/
    public class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//          super.destroyItem(container, position, object);
//          view.removeView(view.getChildAt(position));
//          view.removeViewAt(position);
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }

    }

    /**
     * 利用线程池定时执行动画轮播
     */
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                3,
                3,
                TimeUnit.SECONDS);
    }


    /**
     * 图片轮播任务
     * @author liuyazhuang
     *
     */
    private class ViewPageTask implements Runnable{

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 接收子线程传递过来的数据
     */
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        };
    };
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(scheduledExecutorService != null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }

}