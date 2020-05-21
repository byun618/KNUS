package com.knu.knus.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.knu.knus.HomeActivity;
import com.knu.knus.R;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        CardView login = view.findViewById(R.id.cv_login);
        TextView register = view.findViewById(R.id.tx_reg);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                FragmentTransaction fgTrans = getFragmentManager().beginTransaction();
//                fgTrans.replace(R.id.frameLayout, new HomeFragment());
//                fgTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                fgTrans.addToBackStack(null);
//
//                fgTrans.commit();

                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                startActivity(intent);


                /* HTTP Connection */
//                AsyncTask.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            URL url = new URL("http://172.30.1.1:3001");
//
//                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                            conn.setRequestProperty("Accept", "application/json");
//                            conn.setRequestProperty("Content-Type", "application/json");
//                            conn.setRequestMethod("GET");
//
//                            if(conn.getResponseCode() == 200){
//
//                                InputStream responseBody = conn.getInputStream();
//                                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
//
//                                JsonReader jsonReader = new JsonReader(responseBodyReader);
//                                jsonReader.beginObject();
//
//                                while(jsonReader.hasNext()){
//                                    String key = jsonReader.nextName();
//                                    if(key.equals("result")){
//                                        String value = jsonReader.nextString();
//
//                                        System.out.println("Result : " + value);
//
//                                        break;
//                                    } else {
//                                        jsonReader.skipValue();
//                                    }
//                                }
//
//                                jsonReader.close();
//
//                            }else {
//                                System.out.println("Fail");
//                            }
//
//                            conn.disconnect();
//
//                        } catch (MalformedURLException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });


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


}