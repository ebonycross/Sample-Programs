package com.emc_ideas.justaddsugar;


import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser_frag extends Fragment {

    private static final String TAG = "REGISTER_USER";
    private TextInputLayout usrFnameWrapper;
    private TextInputLayout usrLnameWrapper;
    private TextInputLayout passwordWrapper;
    private TextInputLayout usrEmailWrapper;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;
    private Map<String, mUserID> users = new HashMap<>();
    private ProgressDialog dialog;

    //Firebase objects
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    // Write a message to the database
    private FirebaseDatabase database;
    private FirebaseUser currentUser;
    private DatabaseReference userRef;

    //create instance of homeScreen Activity
    homeActivity home = new homeActivity();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // dialog = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
        //setContentView(R.layout.activity_register_user);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        //enable back button from registration screen
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.activity_register_user, parent, false);

        usrFnameWrapper = (TextInputLayout) v.findViewById(R.id.signupfnameWrapper);
        usrLnameWrapper = (TextInputLayout) v.findViewById(R.id.signuplnameWrapper);
        passwordWrapper = (TextInputLayout) v.findViewById(R.id.signupPasswordWrapper);
        usrEmailWrapper = (TextInputLayout) v.findViewById(R.id.signupEmailWrapper);
        usrEmailWrapper.setHint("Email");
        passwordWrapper.setHint("Password");

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        //  mAuthListener = new FirebaseAuth.AuthStateListener();
        // [END initialize_auth]

        final Button createAcctBtn = (Button) v.findViewById(R.id.signup_btn);
        createAcctBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });

        TextView alreadyUserBtn = (TextView) v.findViewById(R.id.alreadyUser);
        alreadyUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }


    //add listener
    @Override
    public void onStart() {
        super.onStart();
        // mAuth.addAuthStateListener(mAuthListener);
    }


    public void createAccount() {
        final String email = usrEmailWrapper.getEditText().getText().toString();
        notifyUser("email:" + email);
        final String password = passwordWrapper.getEditText().getText().toString();
        final String fname = usrFnameWrapper.getEditText().getText().toString();
        final String lname = usrLnameWrapper.getEditText().getText().toString();

        Log.d(TAG, "createAccount:" + email);

        if (!validateEmail(email)) {
            usrEmailWrapper.setError("Not a valid email address!");

        }
        if (!validatePassword(password)) {
            passwordWrapper.setError("Not a valid password!");
        }
        if (fname.isEmpty()) {
            usrFnameWrapper.setError("Enter first name");

        }
        if (lname.isEmpty()) {
            usrLnameWrapper.setError("Enter last name");

        } else {
            //home.showProgressDialog("Intializing");

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this.getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                notifyUser("Account creation failed");
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            } else {
                                notifyUser("Account has been created. Check Email!");
                                Log.d(TAG, "createUserWithEmail:success");

                                database = FirebaseDatabase.getInstance();
                                currentUser = mAuth.getCurrentUser();

                                userRef = database.getReference(Constants.FIREBASE_CHILD_USERS);
                                //String userID = userRef.push().getKey();

                                String userID = currentUser.getUid();

                                mUserID user = new mUserID(fname, lname,email, userID);

                                users.put(userID, user);
                                userRef.setValue(users);


                                Log.d(TAG, "new user" + currentUser.getEmail());
                                verifyEmailPassword(currentUser);
                            }

                            // home.hideProgressDialog();
                        }
                    });
        }

        return;
    }

    public void verifyEmailPassword(FirebaseUser user) {
        user.sendEmailVerification()
                .addOnCompleteListener(this.getActivity(), new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            notifyUser("Email verification has been sent");
                        } else {
                            notifyUser("Error in verification");
                        }
                    }
                });

        Intent intent = new Intent(getActivity(), WelcomeActivity.class);
        startActivity(intent);
    }


    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        System.out.println("length of password:" + password.length());
        return password.length() > 5;
    }

    private void notifyUser(String message) {
        Toast.makeText(getActivity(), message,
                Toast.LENGTH_SHORT).show();
    }


    //remove listener
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener !=null) {
          mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    /*
    add: onAttach() and onDetach() methods for fragments
     */


}
