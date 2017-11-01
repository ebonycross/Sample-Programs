package com.emc_ideas.justaddsugar;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by ecross on 10/9/17.
 */
public class home_screen_cookbook_frag extends Fragment{
    FragmentManager fm;
    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.content_home_cook_frag, parent, false);
        mContext = getActivity();
        fm = ((FragmentActivity) mContext).getSupportFragmentManager();
        FloatingActionButton cookBookBtn = (FloatingActionButton) v.findViewById(R.id.fabAddBookBtn);
        cookBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(mContext);
                View promptsView = li.inflate(R.layout.activity_fragment_dialog, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext, R.style.DialogStyle);

                //set dialog layout .xml
                alertDialogBuilder.setView(promptsView);

                alertDialogBuilder.setCancelable(false)
                        .setTitle("Alert Frag")



                        .setPositiveButton("OKay Dokey", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){

                            }
                        })

                        .setNegativeButton("Naw", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();

                //show it
                alertDialog.show();

               // DialogFragment alertDFragment = new AlertDFragment();

                //alertDFragment.show(fm, "Alert Dia");
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();



            }
        });

        return v;
    }
}
