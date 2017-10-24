package com.emc_ideas.justaddsugar;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class welcome_screen_frag extends Fragment {

    //declare database objects
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser currentUser;
    private EditText userEmail;
    private EditText userPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState){
        View v = inflater.inflate(R.layout.content_main, parent, false);
        userEmail = (EditText) v.findViewById(R.id.userEmail);
        userPassword = (EditText) v.findViewById(R.id.userPassword);


        //1. navigate to sign-up screen
        TextView tv = (TextView) v.findViewById(R.id.signUp_btn);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        //2. register sign-in and verify
        Button loginBtn = (Button) v.findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                isVerifiedSignInEmailPassword(userEmail.getText().toString(), userPassword.getText().toString());
                if (currentUser.isEmailVerified() == true) {
                    Intent intent = new Intent(getActivity(), homeActivity.class);
                    startActivity(intent);
                }
                else{
                    notifyUser("Email has not been verified, check email account.");
                }
                Intent intent = new Intent(getActivity(), homeActivity.class);
                startActivity(intent);
            }
        });


        return v;
    }

    public void clickSignUpHandler(View view) {
        Intent intent = new Intent(getActivity(), RegisterActivity.class);
        startActivity(intent);

    }


    public boolean isVerifiedSignInEmailPassword (String email, String password){
        final boolean isVerified = false;
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()) {
                            setFlag(isVerified, 0);
                            notifyUser("Error.Email and/or password could not be verified.");
                        }
                    }
                });
        return isVerified;
    }

    private void notifyUser(String message){
        Toast.makeText(getActivity(), message,
                Toast.LENGTH_SHORT).show();
    }

    private boolean setFlag (boolean flag, int n){
        flag = true;
        if (n == 0)
            flag = false;


        return flag;
    }

}

