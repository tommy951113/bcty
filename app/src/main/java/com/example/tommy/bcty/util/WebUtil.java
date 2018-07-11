package com.example.tommy.bcty.util;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by tommy on 2018/7/8.
 */

public class WebUtil {
    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private static final String rootURL = "http://192.168.0.121/bcty/";

    public static String post(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(rootURL + url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String get(String url) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(rootURL + url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
