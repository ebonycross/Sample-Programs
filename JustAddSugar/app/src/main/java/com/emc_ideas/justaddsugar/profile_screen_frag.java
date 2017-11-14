package com.emc_ideas.justaddsugar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ecross on 10/10/17.
 */

public class profile_screen_frag extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState){
        View v = inflater.inflate(R.layout.profile_frag, parent, false);

        return v;
    }

}
