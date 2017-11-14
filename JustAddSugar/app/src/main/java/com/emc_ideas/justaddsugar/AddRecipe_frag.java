package com.emc_ideas.justaddsugar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ecross on 11/7/17.
 */

public class AddRecipe_frag extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState){
        View v = inflater.inflate(R.layout.add_recipe_frag_layout, parent, false);

        return v;
    }
}
