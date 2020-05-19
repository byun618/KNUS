package com.knu.knus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class NoticeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_notice, container, false);

        ListView listView = view.findViewById(R.id.notice_listVIew);
        NoticeViewAdapter adapter = new NoticeViewAdapter();
        listView.setAdapter(adapter);

        adapter.addItem("첫 번째 글", "변상현", "안녕하세요");
        adapter.addItem("두 번째 글", "변상현", "안녕하세요");
        adapter.addItem("세 번째 글", "변상현", "안녕하세요");
        adapter.addItem("네 번째 글", "변상현", "안녕하세요");
        adapter.addItem("다섯 번째 글", "변상현", "안녕하세요");

        adapter.addItem("첫 번째 글", "변상현", "안녕하세요");
        adapter.addItem("두 번째 글", "변상현", "안녕하세요");
        adapter.addItem("세 번째 글", "변상현", "안녕하세요");
        adapter.addItem("네 번째 글", "변상현", "안녕하세요");
        adapter.addItem("다섯 번째 글", "변상현", "안녕하세요");
        return view;
    }
}
