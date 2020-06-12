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

import org.json.JSONObject;

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

        adapter.addItem("첫 번째 글", "변상현", 2);
        adapter.addItem("두 번째 글", "변상현", 2);
        adapter.addItem("세 번째 글", "변상현", 1);
        adapter.addItem("네 번째 글", "변상현", 1);

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


}
