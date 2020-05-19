package com.knu.knus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SignupFragment extends Fragment {

    String name = "";
    String number = "";
    String department = "";
    String grade = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        EditText name_et = view.findViewById(R.id.signup_name);
        EditText number_et = view.findViewById(R.id.signup_nunmber);
        Spinner gradeSpinner = view.findViewById(R.id.signup_grade);
        Spinner departSpinner = view.findViewById(R.id.signup_department);
        CardView signup = view.findViewById(R.id.signup_cardview);

        name = name_et.getText().toString();
        number = number_et.getText().toString();
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

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fgTrans = getFragmentManager().beginTransaction();
                fgTrans.replace(R.id.frameLayout, new LoginFragment());
                fgTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fgTrans.addToBackStack(null);

                fgTrans.commit();
            }
        });


        return view;
    }
}
