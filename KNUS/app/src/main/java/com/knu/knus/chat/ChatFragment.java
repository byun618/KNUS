package com.knu.knus.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.knu.knus.R;

public class ChatFragment extends Fragment {

    ListView listView;
    EditText my_message_dt;
    Button send_btn;
    ChatViewAdapter adapter;
    String myID = "byun618";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_chat, container, false);

        listView = view.findViewById(R.id.chat_listView);
        adapter = new ChatViewAdapter();
        listView.setAdapter(adapter);

        my_message_dt = view.findViewById(R.id.chat_message);
        send_btn = view.findViewById(R.id.chat_send_btn);

        adapter.addItem("asda", "asdasdasda");
        adapter.addItem("asda", "123");
        adapter.addItem("asda", "32");
        adapter.addItem("asda", "sfweca");
        adapter.addItem("asda", "3tb");
        adapter.addItem("asda", "nbvcdf");


        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Adapter에 추가하기 et 초기화
                adapter.addItem(myID, my_message_dt.getText().toString());
                adapter.notifyDataSetChanged();
                my_message_dt.setText("");

            }
        });

        return view;
    }
}
