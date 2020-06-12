package com.knu.knus.notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.knu.knus.R;

import java.util.ArrayList;
import java.util.Collections;

public class NoticeViewAdapter extends BaseAdapter {

    private ArrayList<NoticeViewItem> noticeViewItems = new ArrayList<NoticeViewItem>();

    public NoticeViewAdapter() {

    }

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

        ImageView content_profile = convertView.findViewById(R.id.content_profile);
        TextView content_title = convertView.findViewById(R.id.content_title);
        TextView content_when = convertView.findViewById(R.id.content_when);
        TextView content_body = convertView.findViewById(R.id.content_body);
        TextView content_like = convertView.findViewById(R.id.content_like);

         NoticeViewItem noticeViewItem = noticeViewItems.get(position);

        content_profile.setImageResource(R.drawable.knu_logo);
        content_title.setText(noticeViewItem.getTitle());
        content_when.setText("10분전");
        content_body.setText(noticeViewItem.getContents());

        String like = "좋아요 " + noticeViewItem.getLike() + "개";
        content_like.setText(like);

        Button content_like_btn = convertView.findViewById(R.id.content_like_btn);
        content_like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //디비에서 받아와서 좋아요 수 올리기
            }
        });

        return convertView;
    }

    public void addItem(String title, String contents, int like) {
        NoticeViewItem item = new NoticeViewItem();

        item.setTitle(title);
        item.setContents(contents);
        item.setLike(like);

        noticeViewItems.add(item);
    }
}
