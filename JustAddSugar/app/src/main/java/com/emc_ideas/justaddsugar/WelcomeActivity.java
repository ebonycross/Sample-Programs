package com.emc_ideas.justaddsugar;

import android.content.Intent;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;




/**
 * Created by ecross on 10/5/17.
 */

public class WelcomeActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        FragmentManager fm = getFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.welcomeContainer);

        //add backstack to all layouts

        if(savedInstanceState == null){
            fragment = new welcome_screen_frag();
            fm.beginTransaction().add(R.id.welcomeContainer, fragment).commit();
        }
    }
}



