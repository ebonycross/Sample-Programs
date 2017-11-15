package com.emc_ideas.justaddsugar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ecross on 11/13/17.
 */

public class List_Pager_Group_info_frag extends Fragment {

    public static Fragment newInstance(Context context){
        List_Pager_Group_info_frag gif = new List_Pager_Group_info_frag();
        return gif;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.group_info_layout, parent, false);
        return v;
    }
}
