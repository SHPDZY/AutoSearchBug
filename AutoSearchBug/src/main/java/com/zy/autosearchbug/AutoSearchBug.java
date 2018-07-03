package com.zy.autosearchbug;

import android.content.Context;
import android.content.Intent;


/**
 * Created by zy on 2018/7/2.
 */

 class AutoSearchBug implements AutoSearchBugGlobal {
    private static Context mApp;
    private Thread.UncaughtExceptionHandler mUEH;
    public static int maxSize = 5;
    public static int showAnswer = 0;
    public static int searchType = 0;
    public static String tag = LOG_TAG;
    private static volatile AutoSearchBug autoSearchBug;

    /**
     * @param mApp    Applicatin
     * @param maxSize 获取几条回答数据 默认为5
     * @param showAnswer 是否显示问题的回答，1显示 默认为0
     * @param searchType  0：搜索stackoverflow  1：搜索百度 默认为0
     * @param tag     自定义日志 默认 AutoSearchBug
     */
    public static void init(Context mApp, int maxSize, int showAnswer,int searchType,String tag) {
        if (autoSearchBug == null) {
            synchronized (AutoSearchBug.class) {
                if (autoSearchBug == null) {
                    autoSearchBug = new AutoSearchBug();
                    autoSearchBug.create(mApp, maxSize, showAnswer,searchType,tag);
                }
            }

        }
    }

    private void create(Context mApp, int maxSize,int showAnswer,int searchType, String tag ) {
        this.mApp = mApp;
        this.maxSize = maxSize;
        this.tag = tag;
        this.showAnswer = showAnswer;
        this.searchType = searchType;
        mUEH = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }

    Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(final Thread thread, final Throwable ex) {
            String errorMsg = getErrorMsg(ex);
            Intent intent = new Intent(mApp, AutoSearchBugService.class);
            intent.putExtra(ERROR_MSG, errorMsg);
            intent.putExtra(MAX_SIZE, maxSize);
            intent.putExtra(SHOW_ANSWER, showAnswer);
            intent.putExtra(SEARCH_TYPE, searchType);
            mApp.startService(intent);
            mUEH.uncaughtException(thread, ex);
        }
    };


    private String getErrorMsg(Throwable ex) {
        String message = ex.getMessage();
        return message.substring(message.indexOf(":") + 2);
    }

}
