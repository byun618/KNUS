package com.knu.knus.main;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.knu.knus.DataBaseHandler;
import com.knu.knus.HomeActivity;
import com.knu.knus.R;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginFragment extends Fragment {

    String userName = "";
    String passWord = "";
    EditText et_userName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        et_userName = view.findViewById(R.id.et_userName);
        final EditText et_passWord = view.findViewById(R.id.et_passWord);

        CardView login = view.findViewById(R.id.cv_login);
        TextView register = view.findViewById(R.id.tx_reg);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userName = et_userName.getText().toString();
                passWord = et_passWord.getText().toString();

                try{

                    String json = "";

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("user_id", userName);
                    jsonObject.accumulate("pwd", passWord);

                    json = jsonObject.toString();

                    new LoginRequest().execute(json);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                FragmentTransaction fgTrans = getFragmentManager().beginTransaction();
                fgTrans.replace(R.id.frameLayout, new SignupFragment());
                fgTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fgTrans.addToBackStack(null);

                fgTrans.commit();
            }
        });

        return view;
    }

    private class LoginRequest extends AsyncTask<String, Void, Integer>{

        @Override
        protected Integer doInBackground(String... params) {

            int code = 0;

            try {
                URL url = new URL("http://192.168.0.29:3000/api/user/login");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                os.write(params[0].getBytes("utf-8"));
                os.flush();

                code = conn.getResponseCode();

                conn.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return code;
        }

        @Override
        protected void onPostExecute(Integer code) {
            super.onPostExecute(code);

            if(code == 200){

                SQLiteDatabase db;
                ContentValues row;
                DataBaseHandler handler = new DataBaseHandler(getContext());
                db = handler.getWritableDatabase();
                row = new ContentValues();
                row.put("_id", 1);
                row.put("stdno", et_userName.getText().toString());
                db.replace("login", null, row);

                handler.close();

                Toast.makeText(getContext(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), HomeActivity.class);
                startActivity(intent);

            } else {
                Toast.makeText(getContext(), "로그인에 실패하였습니다. 다시 시도 해주세요", Toast.LENGTH_SHORT).show();
            }
        }
    }


}