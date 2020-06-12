package com.knu.knus.notice;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.knu.knus.DataBaseHandler;
import com.knu.knus.HTTPRequest;
import com.knu.knus.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NoticeFragment extends Fragment {

    DataBaseHandler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_notice, container, false);

        handler = new DataBaseHandler(getContext());

        ListView listView = view.findViewById(R.id.notice_listVIew);
        NoticeViewAdapter adapter = new NoticeViewAdapter();
        listView.setAdapter(adapter);

        Button btn_write = view.findViewById(R.id.btn_notice_show_write);

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });

        try {
            String result = new HTTPLoadBoard().execute().get();

            JSONArray arr = new JSONArray(result);

            for(int i=0; i<arr.length(); i++){
                JSONObject object = arr.getJSONObject(i);

                String id = object.getString("id");
                String title = object.getString("title");
                String body = object.getString("contents");
                String good = object.getString("good");
                String who = object.getString("user_id");
                String when = object.getString("time");

                adapter.addItem(id, title, body, Integer.parseInt(good), who, when);
                System.out.println(id + " " + title + " " + body + " " + good + " " + who + " " + when);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;
    }

    /* Dialog */
    public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_write, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();

        final EditText et_title = view.findViewById(R.id.write_title);
        final EditText et_content = view.findViewById(R.id.write_content);
        final Button write = view.findViewById(R.id.write_btn_write);
        final Button cancel = view.findViewById(R.id.write_btn_cancel);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString();
                String content = et_content.getText().toString();

                try {

                    String json = "";

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("type", "notice");
                    jsonObject.accumulate("user_id", getUser());
                    jsonObject.accumulate("title", title);
                    jsonObject.accumulate("contents", content);

                    json = jsonObject.toString();

                    HTTPRequest request = new HTTPRequest("/board/write");

                    Integer code = request.execute(json).get();

                    if(code == 200) {

                        Toast.makeText(getContext(), "업로드에 성공했습니다.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "업로드에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    /* DB */
    public String getUser(){

        SQLiteDatabase db;

        db = handler.getReadableDatabase();
        Cursor cursor = db.query("login", new String[] {"stdno"}, null, null, null, null, null);

        String stdno = "";
        while(cursor.moveToNext()){
            stdno = cursor.getString(0);
        }

        cursor.close();
        handler.close();

        return stdno;
    }

    private class HTTPLoadBoard extends AsyncTask<Void, Void, String> {

        private String url = "http://192.168.0.29:3000/api";
        private String uri = "/board/load/notice";
        @Override
        protected String doInBackground(Void... voids) {

            String result = "";

            try {
                URL url = new URL(this.url + this.uri);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestMethod("GET");

                conn.setDoInput(true);

                if(conn.getResponseCode() == 200) {

                    InputStream is = conn.getInputStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] byteBuffer = new byte[1024];
                    byte[] byteData = null;
                    int nLength = 0;
                    while((nLength = is.read(byteBuffer, 0, byteBuffer.length)) != -1) {
                        baos.write(byteBuffer, 0, nLength);
                    }
                    byteData = baos.toByteArray();

                    result = new String(byteData);

                } else {
                    result = "Fail";
                }

                conn.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }
    }

}
