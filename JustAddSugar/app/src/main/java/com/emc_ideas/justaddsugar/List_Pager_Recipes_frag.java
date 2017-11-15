package com.emc_ideas.justaddsugar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by ecross on 11/13/17.
 */

public class List_Pager_Recipes_frag extends Fragment  {

    private Context mContext;
    private ImageView addRecipeBtn;

    public static Fragment newInstance(Context context){
        List_Pager_Recipes_frag crf = new List_Pager_Recipes_frag();
        return crf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.book_recipe_list_info, parent, false);

        addRecipeBtn = (ImageView) v.findViewById(R.id.iv_addbtn);
        addRecipeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "Clicked",
                        Toast.LENGTH_SHORT).show();


                Fragment add_recipe_frag2 = new Add_RecipeFrag();
                FragmentManager fm = List_Pager_Recipes_frag.this.getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.home_container, add_recipe_frag2).addToBackStack(null).commit();


            }
        });

        return v;
    }

}
