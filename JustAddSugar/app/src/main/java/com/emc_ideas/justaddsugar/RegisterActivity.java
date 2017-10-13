package com.emc_ideas.justaddsugar;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ecross on 10/4/17.
 */
public class RegisterActivity extends AppCompatActivity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        FragmentManager fm = getFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.welcomeContainer);

        fragment = new RegisterUser_frag();
        fm.beginTransaction().replace(R.id.welcomeContainer, fragment).commit();
        }
    }


