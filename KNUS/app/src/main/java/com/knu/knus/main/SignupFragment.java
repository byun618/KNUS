package com.knu.knus.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.knu.knus.Expression;
import com.knu.knus.R;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignupFragment extends Fragment {

    String stdnumber = "";
    String pwd = "";
    String repwd = "";
    String name = "";
    String department = "";
    String grade = "";

    boolean dupcheck = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        final EditText et_stdnumber = view.findViewById(R.id.signup_stdnumber);
        final EditText et_pwd = view.findViewById(R.id.signup_pwd);
        final EditText et_repwd = view.findViewById(R.id.signup_repwd);
        final EditText et_name = view.findViewById(R.id.signup_name);


        Spinner gradeSpinner = view.findViewById(R.id.signup_grade);
        Spinner departSpinner = view.findViewById(R.id.signup_department);
        CardView signup = view.findViewById(R.id.signup_cardview);
        Button btn_dupcheck = view.findViewById(R.id.btn_dupcheck);

        gradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grade =  parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        departSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                department = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_dupcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stdnumber = et_stdnumber.getText().toString();

                try{
                    String json = "";

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("user_id", stdnumber);

                    json = jsonObject.toString();

                    new DupCheckRequest().execute(json);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stdnumber = et_stdnumber.getText().toString();
                pwd = et_pwd.getText().toString();
                repwd = et_repwd.getText().toString();
                name = et_name.getText().toString();

                if(!Expression.validateStdnum(stdnumber)){
                    Toast.makeText(getContext(), "학번을 확인 해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!dupcheck){
                    Toast.makeText(getContext(), "학번 중복체크 먼저 해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if(pwd != repwd){
//                    Toast.makeText(getContext(), "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if(!Expression.validatePassword(pwd)) {
                    Toast.makeText(getContext(), "비밀번호 조건을 만족해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(name.isEmpty()){
                    Toast.makeText(getContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try{
                    String json = "";

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("user_id", stdnumber);
                    jsonObject.accumulate("pwd", pwd);
                    jsonObject.accumulate("name", name);
                    jsonObject.accumulate("department", department);
                    jsonObject.accumulate("grade", grade);

                    json = jsonObject.toString();

                    new SignupRequest().execute(json);

                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });


        return view;
    }

    public class SignupRequest extends AsyncTask<String, Void, Integer>{

        @Override
        protected Integer doInBackground(String... params) {

            int code = 0;

            try {
                URL url = new URL("http://192.168.0.29:3000/api/user/signup");

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


                Toast.makeText(getContext(), "회원가입에 성공하였습니다", Toast.LENGTH_SHORT).show();

                FragmentTransaction fgTrans = getFragmentManager().beginTransaction();
                fgTrans.replace(R.id.frameLayout, new LoginFragment());
                fgTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fgTrans.addToBackStack(null);

                fgTrans.commit();
            } else {
                Toast.makeText(getContext(), "회원가입에 실패하였습니다. 다시 시도 해주세요", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private class DupCheckRequest extends AsyncTask<String, Void, Integer>{

        @Override
        protected Integer doInBackground(String... params) {

            int code = 0;

            try {
                URL url = new URL("http://192.168.0.29:3000/api/user/check");

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
                Toast.makeText(getContext(), "사용가능합니다.", Toast.LENGTH_SHORT).show();
                dupcheck = true;
            } else {
                Toast.makeText(getContext(), "이 학번은 사용하실 수 없습니다.", Toast.LENGTH_SHORT).show();
                dupcheck = false;
            }




        }
    }
}
