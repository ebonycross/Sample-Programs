package com.emc_ideas.justaddsugar;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
/**
 * Created by ecross on 10/31/17.
 */
public class AlertDFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity())
                .setTitle("Alert Frag")

                .setMessage("Enter New Cookbook")

               .setPositiveButton("OKay Dokey", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){

                    }
                })

                .setNegativeButton("Naw", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                    }
                    });
                 AlertDialog alertDialog = ad.create();

                    //show it
                    alertDialog.show();
                //ad.create();
        return alertDialog;
    }
}
