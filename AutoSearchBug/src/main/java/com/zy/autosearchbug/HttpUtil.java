package com.zy.autosearchbug;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zy on 2018/7/2.
 */

 class HttpUtil {

    public interface HResponse {
        void onStart();

        void onFinish(String msg);

        void onError(String error);
    }

    public static void doGet(final String urls, final HResponse mHResponse) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url;
                HttpURLConnection httpURLConnection = null;
                try {
                    mHResponse.onStart();
                    url = new URL(urls);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(15000);
                    httpURLConnection.setReadTimeout(15000);
                    if (httpURLConnection.getResponseCode() == 200) {
                        InputStream is = httpURLConnection.getInputStream();
                        BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                        StringBuffer buffer = new StringBuffer();
                        String line = "";
                        while ((line = bf.readLine()) != null) {
                            buffer.append(line);
                        }
                        bf.close();
                        is.close();
                        mHResponse.onFinish(buffer.toString());
                    } else {
                        mHResponse.onError(String.valueOf(httpURLConnection.getResponseCode()));
                    }
                } catch (Exception e) {
                    mHResponse.onError(e.getMessage());
                } finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
        }).start();

    }
}
