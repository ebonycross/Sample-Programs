package com.emc_ideas.justaddsugar;


import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.regex.*;

public class RegisterUser_frag extends Fragment {

    private TextInputLayout usrEmailWrapper;
    private TextInputLayout passwordWrapper;
    private TextInputLayout usernameWrapper;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        return v;
    }

    public void clickAlreadMemberHandler(View view) {
       // Intent intent = new Intent(getActivity(), welcome_screen_frag.class);
        //startActivity(intent);
    }

    public void clickCreateActHandler(View view) {

        final TextInputLayout usrEmailWrapper = (TextInputLayout) view.findViewById(R.id.signupEmailWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) view.findViewById(R.id.signupPasswordWrapper);
        final TextInputLayout usernameWrapper = (TextInputLayout) view.findViewById(R.id.signupUsernameWrapper);
        String username = usrEmailWrapper.getEditText().getText().toString();
        String password = passwordWrapper.getEditText().getText().toString();
        if (!validateEmail(username)) {
            usernameWrapper.setError("Not a valid email address!");
        } else if (!validatePassword(password)) {
            passwordWrapper.setError("Not a valid password!");
        } else {
            usernameWrapper.setErrorEnabled(false);
            passwordWrapper.setErrorEnabled(false);
            //Intent intent = new Intent(getActivity(), welcome_screen_frag.class);
            //startActivity(intent);
        }
    }






    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        System.out.println("length of password:" +password.length());
        return password.length() > 5;
    }
}
