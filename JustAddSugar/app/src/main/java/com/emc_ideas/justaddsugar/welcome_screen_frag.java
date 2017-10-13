package com.emc_ideas.justaddsugar;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class welcome_screen_frag extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState){
        View v = inflater.inflate(R.layout.content_main, parent, false);


        TextView tv = (TextView) v.findViewById(R.id.signUp_btn);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button loginBtn = (Button) v.findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
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
}

