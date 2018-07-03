package com.zy.autosearchbug;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.lang.reflect.Method;

import static com.zy.autosearchbug.AutoSearchBugGlobal.ANDROID_CLASS_ERROR;
import static com.zy.autosearchbug.AutoSearchBugGlobal.LOG_TAG;

/**
 * Created by ZY on 2018/6/22.
 */

public class AutoSearchBugControl {
    private static AutoSearchBugControl AutoSearchBugControl = null;
    private static Context mApp;
    private  int maxSize = 5;
    private  int showAnswer = 0;
    private  int searchType = 0;
    private  String tag = LOG_TAG;

    /**
     * 反射获取context
     * @return
     */
    public static AutoSearchBugControl getBuilder() {
        if (AutoSearchBugControl == null) {
            synchronized (AutoSearchBugControl.class) {
                if (AutoSearchBugControl == null) {
                    AutoSearchBugControl = new AutoSearchBugControl(getApp()).builder();
                }
            }
        }
        return AutoSearchBugControl;
    }

    /**
     * 手动传入context
     * @param context
     * @return
     */
    public static AutoSearchBugControl getBuilder(Context context) {
        if (AutoSearchBugControl == null) {
            synchronized (AutoSearchBugControl.class) {
                if (AutoSearchBugControl == null) {
                    AutoSearchBugControl = new AutoSearchBugControl(context).builder();
                }
            }
        }
        return AutoSearchBugControl;
    }

    public AutoSearchBugControl(Context context) {
        this.mApp = context;
    }

    private AutoSearchBugControl builder() {
        return this;
    }

    /**
     * @param maxSize 获取几条回答数据 默认为5
     */
    public AutoSearchBugControl setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    /**
     * @param isShow 是否显示问题的回答
     */
    public AutoSearchBugControl showAnswer(boolean isShow) {
        this.showAnswer = isShow?1:0;
        return this;
    }

    /**
     * @param searchType  0：搜索stackoverflow  1：搜索百度 默认为0
     */
    public AutoSearchBugControl setSearchType(int searchType) {
        this.searchType = searchType;
        return this;
    }

    /**
     * @param tag     自定义日志 默认 AutoSearchBug
     */
    public AutoSearchBugControl setTag(String tag) {
        this.tag = tag;
        return this;
    }

    /**
     * 初始化
     */
    public void init(){
        if (mApp != null) {
            AutoSearchBug.init(mApp,maxSize,showAnswer,searchType,tag);
        } else {
            Log.e(tag, AutoSearchBugControl.class.getName(), new RuntimeException(ANDROID_CLASS_ERROR));
        }

    }

    private static Context getApp() {
        if (mApp != null) {
            return mApp;
        } else {
            try {
                Class<?> clazz = Class.forName("android.app.ActivityThread");
                Method method = clazz.getDeclaredMethod("currentApplication");
                Application mApp = (Application) method.invoke(null);
                return mApp;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
