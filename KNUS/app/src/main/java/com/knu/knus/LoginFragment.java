package com.knu.knus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        CardView login = view.findViewById(R.id.cv_login);
        TextView register = view.findViewById(R.id.tx_reg);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fgTrans = getFragmentManager().beginTransaction();
                fgTrans.replace(R.id.frameLayout, new HomeFragment());
                fgTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fgTrans.addToBackStack(null);

                fgTrans.commit();

                Toast toast = Toast.makeText(getActivity(), "asad", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(getActivity(), "asad", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return view;
    }

}