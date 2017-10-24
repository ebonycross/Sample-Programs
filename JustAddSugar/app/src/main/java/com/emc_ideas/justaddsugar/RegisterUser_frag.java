package com.emc_ideas.justaddsugar;


import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.regex.*;
import android.util.Log;
import android.widget.Button;
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

public class RegisterUser_frag extends Fragment {

    private static final String TAG = "REGISTER_USER";
    private TextInputLayout usrEmailWrapper;
    private TextInputLayout passwordWrapper;
    private TextInputLayout usernameWrapper;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;
    private ProgressDialog dialog;

    //declare database objects
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser currentUser;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState){
        View v = inflater.inflate(R.layout.activity_register_user, parent, false);

        usrEmailWrapper = (TextInputLayout) v.findViewById(R.id.signupEmailWrapper);
        passwordWrapper = (TextInputLayout) v.findViewById(R.id.signupPasswordWrapper);
        usernameWrapper = (TextInputLayout) v.findViewById(R.id.signupUsernameWrapper);
        usrEmailWrapper.setHint("Email");
        passwordWrapper.setHint("Password");
        usernameWrapper.setHint("Username");

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

        return v;
    }


    //add listener
    @Override
    public void onStart(){
        super.onStart();
       // mAuth.addAuthStateListener(mAuthListener);
    }


    public void createAccount() {
        String email = usrEmailWrapper.getEditText().getText().toString();
        notifyUser("email:" + email);
        String password = passwordWrapper.getEditText().getText().toString();

        Log.d(TAG, "createAccount:" + email);

        if (!validateEmail(email)) {
            usernameWrapper.setError("Not a valid email address!");
            return;
        } else if (!validatePassword(password)) {
            passwordWrapper.setError("Not a valid password!");
            return;
      }

        else {
            home.showProgressDialog("Intializing");

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
                                currentUser = mAuth.getCurrentUser();
                                Log.d(TAG, "new user" + currentUser.getEmail());
                                verifyEmailPassword(currentUser);
                            }

                            home.hideProgressDialog();
                        }
                    });
        }

        return;
    }

    public void  verifyEmailPassword(FirebaseUser user){
        user.sendEmailVerification()
                .addOnCompleteListener(this.getActivity(), new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()){
                            notifyUser("Email verification has been sent");
                        }
                        else{
                            notifyUser("Error in verification");
                        }
                    }
                });
    }


    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        System.out.println("length of password:" +password.length());
        return password.length() > 5;
    }

    private void notifyUser(String message){
        Toast.makeText(getActivity(), message,
                Toast.LENGTH_SHORT).show();
    }


    //remove listener
    @Override
    public void onStop(){
        super.onStop();
        //if (mAuthListener !=null) {
          //  mAuth.removeAuthStateListener(mAuthListener);
        //}
    }


    /*
    add: onAttach() and onDetach() methods for fragments
     */


}
