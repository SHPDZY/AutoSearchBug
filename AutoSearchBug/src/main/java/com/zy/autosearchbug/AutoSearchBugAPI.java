package com.zy.autosearchbug;

/**
 * Created by Bolex on 2018/4/30.
 */

 class AutoSearchBugAPI {

    public final static String SEARCH_URL ="http://9aiplay.com/api/android/autosearchbug";
    /*控制查找问题的条数*/
    public final static String PAGE_SIZE ="?page=";
    /*查找的问题msg*/
    public final static String ERROR_MSG ="&errorMsg=";
    /*type==0 去stackoverflow查找  type==1去百度查找*/
    public final static String SEARCH_TYPE ="&type=";
    /*是否显示问题的回答，1=true*/
    public final static String SHOW_ANSWER ="&showAnswer=";

}
