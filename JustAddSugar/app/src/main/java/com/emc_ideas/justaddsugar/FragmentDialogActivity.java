package com.emc_ideas.justaddsugar;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FragmentDialogActivity extends AppCompatActivity {
    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



    }
}

        /*
        //onClick button for adding cookbooks dynamically
       Button cookBookBtn = (Button) findViewById(R.id.fabAddBookBtn);
        cookBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    public void showDialog(){
        DialogFragment newFragment = cookBookDialogFragment.newInstance("UserInput");
        newFragment.show(getFragmentManager(), "dialog");
    }

    public void doPositiveClick(){
        //do stuff
    }

    public void doNegativeClick(){
        //do stuff
    }

    public static class cookBookDialogFragment extends DialogFragment{
        public static cookBookDialogFragment newInstance(int title){
            cookBookDialogFragment frag = new cookBookDialogFragment();
            Bundle args = new Bundle();
            args.putInt("title", title);
            frag.setArguments(args);
            return frag;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        int title = getArguments().getInt("title");
        AlertDialog.Builder myDialog = new AlertDialog.Builder(getActivity());

        myDialog.setTitle(title);

        //set up buttons
        myDialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
                ((FragmentDialogActivity.getActivity()).doPositiveClick();
            }
        });

        myDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
                ((FragmentDialogActivity.getActivity()).doNegativeClick();
            }
    });
        return myDialog.create();
}

*/