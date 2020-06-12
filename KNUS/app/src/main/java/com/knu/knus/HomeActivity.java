package com.knu.knus;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.knu.knus.chat.ChatFragment;
import com.knu.knus.compete.CompeteFragment;
import com.knu.knus.notice.NoticeFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBar actionbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView bar_title;
    DataBaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        handler = new DataBaseHandler(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        actionbar.setDisplayShowCustomEnabled(true);
        actionbar.setDisplayShowTitleEnabled(false);

        bar_title = findViewById(R.id.home_user_id);
        bar_title.setText(getUser() + "님 환영합니다");


        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(new NoticeFragment(), "notice");
        viewPagerAdapter.addFragment(new CompeteFragment(), "competition");
        viewPagerAdapter.addFragment(new ChatFragment(), "chat");

        viewPager.setAdapter(viewPagerAdapter);

    }

    /* DB Method */
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
    public void deleteUser(){
        SQLiteDatabase db;
        db = handler.getReadableDatabase();
        db.delete("login", null, null);
        handler.close();
    }

    /* Menu */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                deleteUser();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_LONG).show();

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            fragmentTitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }
}
