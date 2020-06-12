package com.knu.knus.compete;

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
import com.knu.knus.notice.NoticeViewItem;

import org.json.JSONObject;

import java.util.ArrayList;

public class CompeteViewAdapter extends BaseAdapter {

    private ArrayList<CompeteViewItem> competeViewItems = new ArrayList<CompeteViewItem>();

    @Override
    public int getCount() {
        return competeViewItems.size();
    }

    @Override
    public Object getItem(int position) {

        return competeViewItems.get(position);
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
            convertView = inflater.inflate(R.layout.compete_item, parent, false);
        }

        ImageView compete_profile = convertView.findViewById(R.id.compete_profile);
        TextView compete_title = convertView.findViewById(R.id.compete_title);
        TextView compete_when = convertView.findViewById(R.id.compete_when);
        TextView compete_body = convertView.findViewById(R.id.compete_body);
        TextView compete_like = convertView.findViewById(R.id.compete_like);
        TextView compete_who = convertView.findViewById(R.id.compete_who);

        final CompeteViewItem competeViewItem = competeViewItems.get(position);

        compete_profile.setImageResource(R.drawable.knu_logo);
        compete_title.setText(competeViewItem.getTitle());
        compete_when.setText(competeViewItem.getWhen());
        compete_body.setText(competeViewItem.getBody());
        compete_who.setText(competeViewItem.getWho());

        String like = "좋아요 " + competeViewItem.getLike() + "개";
        compete_like.setText(like);

        Button compete_like_btn = convertView.findViewById(R.id.compete_like_btn);
        compete_like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String json = "";

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("board_id", competeViewItem.getId());

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
        CompeteViewItem item = new CompeteViewItem();

        item.setId(id);
        item.setTitle(title);
        item.setBody(body);
        item.setLike(like);
        item.setWho(who);
        item.setWhen(when);

        competeViewItems.add(item);
    }
}
