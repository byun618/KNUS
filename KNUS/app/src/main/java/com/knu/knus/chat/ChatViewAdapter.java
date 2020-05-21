package com.knu.knus.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.knu.knus.R;

import java.util.ArrayList;

public class ChatViewAdapter extends BaseAdapter {

    private String myID = "byun618";
    private ArrayList<ChatViewItem> chatViewItems = new ArrayList<ChatViewItem>();

    public ChatViewAdapter() {}

    @Override
    public int getCount() {
        return chatViewItems.size();
    }

    @Override
    public Object getItem(int position) {
        return chatViewItems.get(position);
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
            convertView = inflater.inflate(R.layout.chat_item, parent, false);
        }

        ImageView chat_profile = convertView.findViewById(R.id.chat_your_profile);
        TextView chat_your_id = convertView.findViewById(R.id.chat_your_id);
        TextView chat_your_msg = convertView.findViewById(R.id.chat_your_msg);

        TextView chat_my_msg = convertView.findViewById(R.id.chat_my_msg);

        System.out.println("ID : " + chatViewItems.get(position).getUserID());
        if (chatViewItems.get(position).getUserID().equals(myID)) {

            chat_profile.setVisibility(View.GONE);
            chat_your_id.setVisibility(View.GONE);
            chat_your_msg.setVisibility(View.GONE);

            chat_my_msg.setVisibility(View.VISIBLE);

            chat_my_msg.setText(chatViewItems.get(position).getMsg());

        } else {

            chat_profile.setVisibility(View.VISIBLE);
            chat_your_id.setVisibility(View.VISIBLE);
            chat_your_msg.setVisibility(View.VISIBLE);

            chat_my_msg.setVisibility(View.GONE);

            chat_profile.setImageResource(R.drawable.knu_logo);
            chat_your_id.setText(chatViewItems.get(position).getUserID());
            chat_my_msg.setText(chatViewItems.get(position).getMsg());
        }


        return convertView;
    }

    public void addItem(String id, String msg) {

        ChatViewItem item = new ChatViewItem();

        item.setUserID(id);
        item.setMsg(msg);

        chatViewItems.add(item);


    }
}
