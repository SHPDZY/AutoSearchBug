package com.zy.autosearchbug;

/**
 * Created by zy on 2018/7/2.
 */

interface AutoSearchBugGlobal {

    String LINE_START           =       "\n*****************************AutoSearchBug*****************************";
    String SEARCHING            =       "\n*****************************正在查找中。。*****************************";
    String SEARCHING_SUCCESS    =       "\n*****************************查找成功**********************************";
    String LINE                 =       "\n***********************************************************************";
    String ASB_ERROR_TYPE       =       "\n* 错误Message:%s。";
    String ASB_RECOMMEND        =       "\n* 查询到%s条相关问题，为您显示%s条问题。↓详细数据请看下方↓";
    String ASB_LINKS            =       "\n* 问题标题:%s\n* 问题链接:%s";
    String ASB_ANSWER           =       "\n* 回答数量:%s\n* 回答信息:%s";
    String ASB_ERROR            =       "\n* AutoSearchBug响应错误:%s";
    String ASB_JSON_ERROR       =       "\n* AutoSearchBug解析JSON错误:%s";
    String JSON                 =       "\n* AutoSearchBug返回JSON:%s";
    String SEARCH_EMPTY         =       "\n* 没有在stackoverflow同相关问题，您的问题貌似有点难解决哦!~。";
    String INTERNET_ERROR       =       "\n* 查询失败，服务器异常，请联系QQ：82001945，万分感谢！";
    String LINE_END             =       "\n*****************************AutoSearchBug*****************************";

    String ANDROID_CLASS_ERROR  =       "反射不可用，请调用带Context构造函传入Context对象";

    String JSON_DATA            =       "data";
    String JSON_URL             =       "url";
    String JSON_TITLE           =       "title";
    String JSON_ANSWER          =       "answer";
    String JSON_ANUMBER         =       "answerNumber";

    String ERROR_MSG            =       "ERROR_MSG";
    String LOG_TAG              =       "AutoSearchBug";
    String MAX_SIZE             =       "MAX_SIZE";
    String SHOW_ANSWER          =       "SHOW_ANSWER";

    String SEARCH_TYPE          =       "SEARCH_TYPE";

}
