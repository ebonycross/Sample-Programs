package com.emc_ideas.justaddsugar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by ecross on 11/13/17.
 */

public class List_Pager_Recipes_frag extends Fragment  {

    public static final String TAG = "LIST OF RECIPE FRAG";

    private Context mContext;
    private ImageView addRecipeBtn;
    private Bundle bundle;
    private String book_pushID;
    private mCookbook book;
    private Bundle args;

    public static Fragment newInstance(Context context){
        List_Pager_Recipes_frag crf = new List_Pager_Recipes_frag();


        return crf;
    }

    public static Fragment newInstance(Context context, Bundle g){
        Bundle arg = g;
        //args.putParcelable(Constants.COOKBOOK_OBJ_KEY,c);
        List_Pager_Recipes_frag crf = new List_Pager_Recipes_frag();
        crf.setArguments(arg);


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
        ((homeActivity) getActivity()).getSupportActionBar().setTitle("List of Recipes");


        args = getArguments();

        if(args!= null){
            book = (mCookbook) args.get(Constants.COOKBOOK_OBJ_KEY);

            Log.i(TAG,"LIST OF RECIPE FRAG TITLE "+ book.getTitle());



            //book_pushID = bundle.getString(Constants.COOKBOOK_ID_KEY);
            Toast.makeText(getActivity(), "List FRAG Found cookbook nammed " + book.getTitle() + "and pusid is " + book.getId(), Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(getActivity(), "NOT FOUND!!!", Toast.LENGTH_LONG).show();
        }

        addRecipeBtn = (ImageView) v.findViewById(R.id.iv_addbtn);
        addRecipeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "Clicked",
                        Toast.LENGTH_SHORT).show();

                /***** BUNDLE COOKBOOK OBJECT***/
                Bundle bundle = new Bundle();


                bundle.putParcelable(Constants.COOKBOOK_OBJ_KEY,book);
                Boolean t = bundle.isEmpty();

                Log.i(TAG, "bundle empty? " +t );



                Fragment add_recipe_frag2 = new Add_RecipeFrag();
                add_recipe_frag2.setArguments(bundle);
                /***** BUNDLE COOKBOOK OBJECT***/

                FragmentManager fm = List_Pager_Recipes_frag.this.getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                        ft.replace(R.id.home_container, add_recipe_frag2).addToBackStack(null).commit();


            }
        });

        return v;
    }

}
