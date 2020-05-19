package com.hometest.db_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    WordDBHelper mHelper;
    EditText mText;
    EditText name_edit;
    EditText pwd_edit;
    String name_text;
    String pwd_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelper = new WordDBHelper(this);    //  (1) DB 생성을 위한 객체 생성
        mText = (EditText)findViewById(R.id.edit_text);
        name_edit= (EditText)findViewById(R.id.name);
        pwd_edit = (EditText)findViewById(R.id.pwd);
        name_text = name_edit.getText().toString();
        pwd_text = pwd_edit.getText().toString();
    }
    public void mOnClick(View v) {   // 레코드 저장 처리
        SQLiteDatabase db;   // (4) db명의 DB 얻기 위한 참조 객체 선언, 한 어플당 한 개 DB만 허용
        ContentValues row;   // (5) 한 개 레코드 삽입을 위한 참조 객체 선언

        switch (v.getId()) {
            case R.id.insert:
                db = mHelper.getWritableDatabase();  // (6) DB에 저장하기 위해 DB를 열음, DB객체리턴
                // (삽입 방법 1) insert 메서드로 삽입 – 첫번 레코드 (DML 명령어 사용)
                row = new ContentValues(); // (7) 한 개 레코드 삽입위한 객체 생성
                row.put("name", "name");  // (8) 첫번 레코드의 첫번 속성의 값 삽입
                row.put("pwd",  "password");   //    첫번 레코드의 두번째 속성의 값 삽입
                db.insert("login", null, row);   // (9) dic 테이블에 row에 저장된 한개 레코드 DB에 저장
                // (삽입 방법 2) SQL 명령으로 삽입 – 두번째 레코드 (자바 메소드 이용)
                db.execSQL("INSERT INTO login VALUES (null, 'test', '테스트');");
                mHelper.close();
                mText.setText("Insert Success");
                break;
            case R.id.delete:
                db = mHelper.getWritableDatabase();
                // delete 메서드로 삭제
                db.delete("login", null, null);
                // SQL 명령으로 삭제
                //db.execSQL("DELETE FROM dic;");
                mHelper.close();
                mText.setText("Delete Success");
                break;

            case R.id.update:
                db = mHelper.getWritableDatabase();
                // (방법 1) update 메서드로 갱신
                row = new ContentValues();
                row.put("pwd", "소년");  // han 속성에 대해 “머스마”를 “소년” 값으로 변경
                db.update("login", row, "name = 'boy'", null); // name가 “boy”인 레코드의 han을 “소년으로 변경
                // (방법 2) SQL 명령으로 갱신
                //db.execSQL("UPDATE dic SET han = '소년' WHERE eng = 'boy';");
                mHelper.close();
                mText.setText("Update Success");
                break;
            case R.id.select:
                db = mHelper.getReadableDatabase();
                Cursor cursor;
                // (방법 1) query 메서드로 읽기
                // cursor = db.query("dic", new String[] {"eng", "han"}, null,
                // null, null, null, null);
                // (방법 2) SQL 명령으로 읽기
                cursor = db.rawQuery("SELECT name, pwd FROM login", null);  // cursor 객체에 검색된 모든 레코드 저장

                String Result = "";
                while (cursor.moveToNext()) { //한개레코드씩 읽음
                    String name = cursor.getString(0);
                    String pwd = cursor.getString(1);
                    Result += (name + " = " + pwd + "\n");
                }

                if (Result.length() == 0) {
                    mText.setText("Empyt Set");
                } else {
                    mText.setText(Result); // 검색 레코드 출력
                }
                cursor.close();
                mHelper.close();
                break;
        }
    }
    class WordDBHelper extends SQLiteOpenHelper {

        // (2) DB 생성 (UserInfo.db)
        public WordDBHelper(Context context) {
            super(context, "UserInfo.db", null, 1);
        }

        // (3) DB내 테이블의 스키마 생성 (login)
        // 테이블내 속성 : name, pwd
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE login ( _id INTEGER PRIMARY KEY AUTOINCREMENT,  " + "name TEXT, pwd TEXT);");
        }

        // 테이블 변경
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // 기존 테이블 삭제후, 동일 테이블 다시 생성
            db.execSQL("DROP TABLE IF EXISTS login");
            onCreate(db);
        }

    }
}


