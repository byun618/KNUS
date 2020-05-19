package com.knu.knus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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

        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.notice_item, parent, false);
        }

        TextView title = convertView.findViewById(R.id.notice_title);
        TextView subTitle = convertView.findViewById(R.id.notice_author);
        TextView contents = convertView.findViewById(R.id.notice_content);

        NoticeViewItem noticeViewItem = noticeViewItems.get(position);

        title.setText(noticeViewItem.getTitle());
        subTitle.setText(noticeViewItem.getAuthor());
        contents.setText(noticeViewItem.getContents());

        return convertView;
    }

    public void addItem(String title, String subTitle, String contents) {
        NoticeViewItem item = new NoticeViewItem();

        item.setTitle(title);
        item.setAuthor(subTitle);
        item.setContents(contents);

        noticeViewItems.add(item);
    }
}
