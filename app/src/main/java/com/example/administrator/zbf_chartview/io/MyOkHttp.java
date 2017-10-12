package com.example.administrator.zbf_chartview.io;

import android.content.Context;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 封装OKhttp请求
 * Created by hs-301 on 2017/9/5.
 */
public class MyOkHttp {
    OkHttpClient client = new OkHttpClient();
    Request request;

    public void getJson(String url, final IOHttpCallBack callBack) {

        request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        callBack.getIOHttpCallBack(e.getMessage(), 1);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String jsonBody = response.body().string();
                        String headers = response.headers().toString();
                        callBack.getIOHttpCallBack(jsonBody, 0);
                    }
                });

    }

    public void postJson(String url, String json, final IOHttpCallBack callBack) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);

        request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        callBack.getIOHttpCallBack(e.getMessage(), 1);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String jsonBody = response.body().string();
                        String headers = response.headers().toString();
                        callBack.getIOHttpCallBack(jsonBody, 0);
                    }
                });


    }


    /**
     * 下载文件
     *
     * @param url      链接地址
     * @param saveUrl  文件保存地址
     * @param callBack 结果回调
     * @param context  上下文
     */
    public void downLoadFile(String url, String saveUrl, final IOHttpCallBack callBack, Context context) {
        request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.getIOHttpCallBack(e.getMessage(), 1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("failed to downliad file:" + response);
                }
                InputStream ips = response.body().byteStream();
                FileOutputStream fos = new FileOutputStream(new File("/sdcard/text.pdf"));
                byte[] buffer = new byte[2048];
                int len = 0;
                while ((len = ips.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                fos.flush();

            }
        });


    }

    /**
     * 取消请求
     */
    public void cancle() {
    }
}
