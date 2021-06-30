package com.example.newsapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.newsapp.bean.NewsData;
import com.example.newsapp.bean.User;

import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SqliteDB {
    /**
     * 数据库名
     */
    public static final String DB_NAME = "News";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static SqliteDB sqliteDB;
    private static SQLiteDatabase db;


    private SqliteDB(Context context) {
        OpenHelper dbHelper = new OpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }


    public synchronized static SqliteDB getInstance(Context context) {
        if (sqliteDB == null) {
            sqliteDB = new SqliteDB(context);
        }
        return sqliteDB;
    }


    //更新数据库

    public static void updata(String name, String password) {
        db.execSQL("UPDATE user SET userpwd =? where username=?", new Object[]{password, name});
//            SqliteDB.updata(name, password);

    }

//    public static ArrayList<User> getAllDATA() {
//
//            ArrayList<User> list = new ArrayList<User>();
//            Cursor cursor = db.query("user",null,null,null,null,null,"name DESC");
//            while(cursor.moveToNext()){
//                String username = cursor.getString(cursor.getColumnIndex("username"));
//                String userpwd = cursor.getString(cursor.getColumnIndex("userpwd"));
//                list.add(new User(username,userpwd));
//            }


    /**
     * 将User实例存储到数据库。
     */
    public int saveUser(User user) {
        if (user != null) {
           /* ContentValues values = new ContentValues();
            values.put("username", user.getUsername());
            values.put("userpwd", user.getUserpwd());
            db.insert("User", null, values);*/

            Cursor cursor = db.rawQuery("select * from User where username=?", new String[]{user.getUsername().toString()});
            if (cursor.getCount() > 0) {
                return -1;
            } else {
                try {
                    db.execSQL("insert into User(username,userpwd) values(?,?) ", new String[]{user.getUsername().toString(), user.getUserpwd().toString()});
                } catch (Exception e) {
                    Log.d("错误", e.getMessage().toString());
                }
                return 1;
            }
        } else {
            return 0;
        }
    }

    /**
     * 从数据库读取User信息。
     */
    public static ArrayList<User> getAllDATA() {
        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db
                .query("User", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                user.setUsername(cursor.getString(cursor
                        .getColumnIndex("username")));
                user.setUserpwd(cursor.getString(cursor

                        .getColumnIndex("userpwd")));
                list.add(user);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public void insertCollection(String newsTitle, String newsData, String newsImageUrl, String newsUrl, String userName) {

        ContentValues values = new ContentValues();
        values.put("username", userName);
        values.put("newsTitle", newsTitle);
        values.put("newsDate", newsData);
        values.put("newsImgUrl", newsImageUrl);
        values.put("newsUrl", newsUrl);
        db.insert("collect", null, values);
    }

    public void deleteCollection(String title, String name) {
        db.delete("collect", "newsTitle = ? and username = ?", new String[]{title, name});
    }

    public List<NewsData> getAllCollection(String name) {
        Cursor cursor = db.rawQuery("select * from collect where username=?  ", new String[]{name});
        List<NewsData> newsDataArrayList = new ArrayList<>();
        NewsData newsData;
        while (cursor.moveToNext()) {
            newsData = new NewsData();
            newsData.setNewsDate(cursor.getString(cursor.getColumnIndex("newsDate")));
            newsData.setNewsTitle(cursor.getString(cursor.getColumnIndex("newsTitle")));
            newsData.setNewsImgUrl(cursor.getString(cursor.getColumnIndex("newsImgUrl")));
            newsData.setNewsUrl(cursor.getString(cursor.getColumnIndex("newsUrl")));
            newsDataArrayList.add(newsData);

        }
        return newsDataArrayList;
    }

    /**
     * 查询是否收藏
     *
     * @param newsTitle
     * @param name
     * @return
     */
    public int isCollection(String newsTitle, String name) {


        HashMap<String, String> hashmap = new HashMap<String, String>();
        Cursor cursor = db.rawQuery("select * from collect where username=? and newsTitle =? ", new String[]{name, newsTitle});

        // hashmap.put("name",db.rawQuery("select * from User where name=?",new String[]{name}).toString());
        if (cursor.getCount() > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public int Quer(String pwd, String name) {


        HashMap<String, String> hashmap = new HashMap<String, String>();
        Cursor cursor = db.rawQuery("select * from User where username=?", new String[]{name});

        // hashmap.put("name",db.rawQuery("select * from User where name=?",new String[]{name}).toString());
        if (cursor.getCount() > 0) {
            Cursor pwdcursor = db.rawQuery("select * from User where userpwd=? and username=?", new String[]{pwd, name});
            if (pwdcursor.getCount() > 0) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }
}


