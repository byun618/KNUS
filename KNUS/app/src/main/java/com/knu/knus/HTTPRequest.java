package com.knu.knus;

import android.os.AsyncTask;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPRequest extends AsyncTask<String, Void, Integer> {

    private String url = "http://192.168.0.29:3000/api";
    private String uri;

    public HTTPRequest(String uri) {
        this.uri = uri;
    }

    @Override
    protected Integer doInBackground(String... params) {
        int code = 0;

        try {
            URL url = new URL(this.url + this.uri);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");

            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(params[0].getBytes("utf-8"));
            os.flush();

            code = conn.getResponseCode();

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return code;
    }
}
