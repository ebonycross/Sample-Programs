package com.emc_ideas.justaddsugar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class homeActivity extends AppCompatActivity {
    //android.support.v4.app.FragmentManager fm1 = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.topToolbar);
        setSupportActionBar(toolbar);

        final FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.welcomeContainer);

        fragment = new home_screen_cookbook_frag();
        fm.beginTransaction().replace(R.id.home_container, fragment).commit();


        //create screens to naviagate to
        final Fragment home_frag = new home_screen_cookbook_frag();
        final Fragment profile_frag = new profile_screen_frag();
        final Fragment recipe_frag = new recipe_screen_frag();
        final Fragment setting_frag = new settings_screen_frag();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navViewBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toolbar toolbar = (Toolbar) findViewById(R.id.topToolbar);
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.home_container, home_frag).commit();
                        getSupportActionBar().setTitle("Welcome");

                        return true;
                    case R.id.ic_profile:
                        ft = fm.beginTransaction();
                        ft.replace(R.id.home_container, profile_frag).commit();
                        toolbar.setTitle("Profile");
                        return true;
                    case R.id.ic_food:
                        ft = fm.beginTransaction();
                        ft.replace(R.id.home_container, recipe_frag).commit();
                        return true;
                    case R.id.ic_setting:
                        ft = fm.beginTransaction();
                        ft.replace(R.id.home_container, setting_frag).commit();
                        return true;
                }
                return false;
            }

        });//end of nav-menu item listener



    }

}
