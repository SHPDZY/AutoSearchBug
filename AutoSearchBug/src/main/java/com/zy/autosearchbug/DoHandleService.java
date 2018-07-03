package com.zy.autosearchbug;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zy on 2018/7/2.
 */

public class DoHandleService extends Service implements AutoSearchBugGlobal {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String errorMsg = intent.getStringExtra(AutoSearchBugGlobal.ERROR_MSG);
        int maxSize = intent.getIntExtra(AutoSearchBugGlobal.MAX_SIZE, AutoSearchBug.maxSize);
        int showAnswer = intent.getIntExtra(AutoSearchBugGlobal.SHOW_ANSWER, AutoSearchBug.showAnswer);
        int searchType = intent.getIntExtra(AutoSearchBugGlobal.SEARCH_TYPE, AutoSearchBug.searchType);
        search(errorMsg, maxSize, showAnswer, searchType);
        return super.onStartCommand(intent, flags, startId);
    }

    private void search(final String errorMsg, int maxSize, final int showAnswer, int searchType) {
        HttpUtil.doGet(API.SEARCH_URL + API.PAGESIZE + maxSize +
                        API.ErrorMsg + errorMsg.replace(" ", "%20") +
                        API.ShowAnswer + showAnswer + API.SearchType + searchType,
                new HttpUtil.HResponse() {
                    @Override
                    public void onStart() {
                        StringBuffer log = new StringBuffer();
                        log.append(LINE_START);
                        log.append(SEARCHING);
                        log.append(LINE_END);
                        log(log);
                    }

                    @Override
                    public void onFinish(String msg) {
                        resolveLog(msg, errorMsg,showAnswer);
                        stopSelf();
                    }

                    @Override
                    public void onError(String error) {
                        StringBuffer log = new StringBuffer();
                        log.append(String.format(ASB_ERROR, error));
                        log.append(LINE_END);
                        log(log);
                        stopSelf();
                    }
                });
    }

    private void resolveLog(String msg, String errorMsg, int showAnswer) {
        StringBuffer log = new StringBuffer();
        log.append(LINE_START);
        try {
            JSONObject jsonObject = new JSONObject(msg);
            String code = jsonObject.getString("code");
            if (!TextUtils.isEmpty(code) && code.equals("0")) {
                log.append(SEARCHING_SUCCESS);
                String searchSize = jsonObject.getString("size");
                JSONArray data = jsonObject.getJSONArray(JSON_DATA);
                int length = data.length();
                if (length > 0) {
                    log.append(String.format(ASB_ERROR_TYPE, errorMsg));
                    log.append(String.format(ASB_RECOMMEND, searchSize, length));
                    log.append(LINE);
                    for (int i = 0; i < length; i++) {
                        JSONObject item = (JSONObject) data.get(i);
                        String url = item.getString(JSON_URL);
                        String title = item.getString(JSON_TITLE);
                        log.append(String.format(ASB_LINKS, title, url));
                        if (showAnswer==1){
                            String answer = item.getString(JSON_ANSWER);
                            String answerNumber = item.getString(JSON_ANUMBER);
                            if (TextUtils.isEmpty(answer)){
                                answer = "无回答";
                            }
                            if (TextUtils.isEmpty(answerNumber)){
                                answerNumber = "0";
                            }
                            log.append(String.format(ASB_ANSWER, answerNumber, answer));
                        }
                        if (i < length - 1) {
                            log.append(LINE);
                        }
                    }
                } else {
                    log.append(SEARCH_EMPTY);
                }

            } else {
                log.append(INTERNET_ERROR);
            }
            log.append(LINE_END);
            log(log);
        } catch (JSONException e) {
            log.append(String.format(ASB_JSON_ERROR, e.getMessage()));
            log.append(String.format(JSON, e.getMessage()));
            log.append(msg);
            log.append(LINE_END);
            log(log);
        }
    }

    private void log(StringBuffer log) {
        Log.e(AutoSearchBug.tag, log.toString());
    }
}
