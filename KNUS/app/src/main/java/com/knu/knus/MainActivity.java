package com.knu.knus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fgManager;
    private FragmentTransaction fgTransaction;
    private LoginFragment loginFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fgManager = getSupportFragmentManager();
        fgTransaction = fgManager.beginTransaction();
        loginFragment = new LoginFragment();
        fgTransaction.replace(R.id.frameLayout, loginFragment).commitAllowingStateLoss();
    }
}
