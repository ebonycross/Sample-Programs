package com.emc_ideas.justaddsugar;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
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

import static android.content.ContentValues.TAG;

public class welcome_screen_frag extends Fragment {

    //declare database objects
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    private EditText userEmail;
    private EditText userPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.content_main, parent, false);


        // [END initialize_auth]

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
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // if(user.isEmailVerified()) {
                signIn(userEmail.getText().toString(), userPassword.getText().toString());

                //else{
                //  notifyUser("Error: Must verify account. Check email.");
                //}

            }

        });


        //3. sign-out
        Button signOutBtn = (Button) v.findViewById(R.id.sign_out_button);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //signOut();
            }
        });

        return v;
    }


    public boolean isVerifiedSignInEmailPassword(String email, String password) {
        final boolean isVerified = false;
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            setFlag(isVerified, 0);
                            notifyUser("Error.Email and/or password could not be verified.");
                        }
                    }
                });
        return isVerified;
    }

    private void notifyUser(String message) {
        Toast.makeText(getActivity(), message,
                Toast.LENGTH_SHORT).show();
    }


    private boolean setFlag(boolean flag, int n) {
        flag = true;
        if (n == 0)
            flag = false;


        return flag;
    }


    // [START on_start_check_user]

    public void onAttach() {
        super.onAttach(getContext());
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    // [END on_start_check_user]


    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }


        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            user = mAuth.getCurrentUser();
                            if (user.isEmailVerified()) {
                                updateUI(user);
                                Intent intent = new Intent(getActivity(), homeActivity.class);
                                startActivity(intent);
                            } else {
                                notifyUser("Error: Not verified");
                            }
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });
        // [END sign_in_with_email]
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = userEmail.toString();
        if (TextUtils.isEmpty(email)) {
            userEmail.setError("Required.");
            valid = false;
        } else {
            userEmail.setError(null);
        }

        String password = userPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            userPassword.setError("Required.");
            valid = false;
        } else {
            userPassword.setError(null);
        }

        return valid;
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }


    private void updateUI(FirebaseUser user) {


        if (user != null) {

            getView().findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);


        } else {
            getView().findViewById(R.id.sign_out_button).setVisibility(View.GONE);
        }
    }


}

