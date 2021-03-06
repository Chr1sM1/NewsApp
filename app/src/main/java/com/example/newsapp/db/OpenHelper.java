package com.example.newsapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class OpenHelper extends SQLiteOpenHelper {


    //把数据库设置成可写入的状态
    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    //创建用户表

    public static final String CREATE_USER = "create table User ("
            + "id integer primary key autoincrement, "
            + "username text, "
            + "userpwd text)";

    //创建收藏表

    public static final String CREATE_COLLECT = "create table collect ("
            + "id integer primary key autoincrement, "
            + "username text, "
            + "newsTitle text, "
            + "newsDate text, "
            + "newsImgUrl text, "
            + "newsUrl text)";

    public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(CREATE_USER);//创建用户表
        db.execSQL(CREATE_COLLECT);//创建收藏表

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }


}

