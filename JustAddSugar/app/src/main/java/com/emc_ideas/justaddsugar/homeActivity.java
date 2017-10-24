package com.emc_ideas.justaddsugar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class homeActivity extends AppCompatActivity {
    @VisibleForTesting
    public ProgressDialog mProgressDialog;


    public void showProgressDialog(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(msg);
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.topToolbar);
        setSupportActionBar(toolbar);

        final FragmentManager fm = getFragmentManager();

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
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.home_container, home_frag).commit();
                        return true;
                    case R.id.ic_profile:
                        ft = fm.beginTransaction();
                        ft.replace(R.id.home_container, profile_frag).commit();
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
