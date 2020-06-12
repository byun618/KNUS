package com.knu.knus.notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.knu.knus.HTTPRequest;
import com.knu.knus.R;

import org.json.JSONObject;

import java.util.ArrayList;

public class NoticeViewAdapter extends BaseAdapter {

    private ArrayList<NoticeViewItem> noticeViewItems = new ArrayList<NoticeViewItem>();


    @Override
    public int getCount() {
        return noticeViewItems.size();
    }

    @Override
    public Object getItem(int position) {

        return noticeViewItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.notice_item, parent, false);
        }

        ImageView notice_profile = convertView.findViewById(R.id.notice_profile);
        TextView notice_title = convertView.findViewById(R.id.notice_title);
        TextView notice_when = convertView.findViewById(R.id.notice_when);
        TextView notice_body = convertView.findViewById(R.id.notice_body);
        TextView notice_like = convertView.findViewById(R.id.notice_like);
        TextView notice_who = convertView.findViewById(R.id.notice_who);


        final NoticeViewItem noticeViewItem = noticeViewItems.get(position);

        notice_profile.setImageResource(R.drawable.knu_logo);
        notice_title.setText(noticeViewItem.getTitle());
        notice_when.setText(noticeViewItem.getWhen());
        notice_body.setText(noticeViewItem.getBody());
        notice_who.setText(noticeViewItem.getWho());

        String like = "좋아요 " + noticeViewItem.getLike() + "개";
        notice_like.setText(like);

        Button content_like_btn = convertView.findViewById(R.id.notice_like_btn);
        content_like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String json = "";

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("board_id", noticeViewItem.getId());

                    json = jsonObject.toString();

                    HTTPRequest request = new HTTPRequest("/board/good");

                    int code = request.execute(json).get();

                    System.out.println(code);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        return convertView;
    }

    public void addItem(String id, String title, String body, int like, String who, String when) {
        NoticeViewItem item = new NoticeViewItem();

        item.setId(id);
        item.setTitle(title);
        item.setBody(body);
        item.setLike(like);
        item.setWho(who);
        item.setWhen(when);

        noticeViewItems.add(item);
    }
}
