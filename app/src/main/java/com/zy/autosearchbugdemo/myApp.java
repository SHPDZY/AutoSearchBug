package com.zy.autosearchbugdemo;

import android.app.Application;

import com.zy.autosearchbug.AutoSearchBugControl;


/**
 * Created by ZY on 2018/7/3.
 */

public class myApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AutoSearchBugControl.getBuilder()
                .setMaxSize(3)
                .showAnswer(true)
                .setSearchType(0)
                .init();
    }
}
