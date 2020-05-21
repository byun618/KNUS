package com.knu.knus.notice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.knu.knus.R;

public class NoticeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_notice, container, false);

        ListView listView = view.findViewById(R.id.notice_listVIew);
        NoticeViewAdapter adapter = new NoticeViewAdapter();
        listView.setAdapter(adapter);

        adapter.addItem("첫 번째 글", "변상현", 2);
        adapter.addItem("두 번째 글", "변상현", 2);
        adapter.addItem("세 번째 글", "변상현", 1);
        return view;
    }
}
