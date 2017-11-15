package com.emc_ideas.justaddsugar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ecross on 11/7/17.
 */

public class Add_RecipeFrag extends Fragment {

    FragmentManager fm;
    Context mContext;
    private RecyclerView mAddRecycler;
    private RecyclerView.LayoutManager mLayoutMgr;
    private Intent i;

    private Adapter_Ingredient ingredAdapter;
    private EditText title, cooktime, servings, ingred_name;
    private TextInputEditText title_wrapper, cooktime_wrapper, serving_wrapper, ingred_name_wrapper;
    private mRecipe recipe;
    private ArrayList<mIngredient> ingredients;
    private Button submitBtn;
    private Button add_RecipeBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.add_recipe_frag_layout, parent, false);

        return v;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceSate) {
        super.onViewCreated(view, savedInstanceSate);
        initItems();
        recipe = new mRecipe();
        ingredients = new ArrayList<mIngredient>();
        submitBtn.setOnClickListener(new SubmitRecipeBtnClick());

        mContext = getActivity();

/*
        mAddRecycler = (RecyclerView) view.findViewById(R.id.rv_addRecipe);


        if (mAddRecycler != null) {
            mAddRecycler.setHasFixedSize(true);
        }

        //linearLayoutManager is user here, this will layout elems in like fashion of
        //listView would do element. The RecyclerView.LayoutManager defines how elements
        //are laid out
        mLayoutMgr = new LinearLayoutManager(mContext);

        mAddRecycler.setLayoutManager(mLayoutMgr);


        mAddRecycler.addOnItemTouchListener(new RecyclerTouchListener(mContext,
                mAddRecycler, new RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                //Values are passing to activity & to fragment as well
                Toast.makeText(getActivity(), "Single Click on position        :" + position,
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();
            }
        }));


        //initialize adapter to list of books
        ingredAdapter = new Adapter_Ingredient(ingredients);

        //bookAdapter.setListContent(cBooks);
        //set CookBookAdapter as the adapter for RecyclerView

        mAddRecycler.setItemAnimator(new DefaultItemAnimator());
        mAddRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        mAddRecycler.setAdapter(ingredAdapter);

        fm = ((FragmentActivity) mContext).getSupportFragmentManager();


        add_RecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String name = ingred_name.getText().toString();

                    mIngredient c = new mIngredient();
                    c.setFoodItem(name);
                    ingredAdapter.insertItem(c);
                    int position = ingredAdapter.getItemCount();

                    mAddRecycler.scrollToPosition(position);

                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        });

        */

    }

    public void initItems() {
        title = (EditText) getView().findViewById(R.id.form_title);
        submitBtn = (Button) getView().findViewById(R.id.submit_recipe_btn);
        cooktime = (EditText) getView().findViewById(R.id.form_cooktime);
        servings = (EditText) getView().findViewById(R.id.form_serving);
        ingred_name = (EditText) getView().findViewById(R.id.form_ingred_name);


        title_wrapper = (TextInputEditText) getView().findViewById(R.id.form_title_wrapper);
        cooktime_wrapper = (TextInputEditText) getView().findViewById(R.id.form_cooktime_wrapper);
        serving_wrapper = (TextInputEditText) getView().findViewById(R.id.form_serving_wrapper);
        ingred_name_wrapper = (TextInputEditText) getView().findViewById(R.id.form_ingred_name_wrapper);

        add_RecipeBtn = (Button) getView().findViewById(R.id.add_RecipeBtn);
    }

//button listners

//numberpick inflate and listener


    //create recipe object
    public void submitRecipe() {
        if (validate()) {
            String str = "title: " + title.getText().toString() +
                    ", cooktime: " + cooktime.getText().toString();


            recipe.setTitle(title.getText().toString());
            recipe.setCooktime(cooktime.getText().toString());
            Toast.makeText(getActivity(), "Title: " + str, Toast.LENGTH_SHORT).show();

        }
    }


    /*on click of the submit button, check the edittext is not empty and buttons have been select
    on Submit-->boolean validate(): EditText fields true else setOnError() and return;
     */
    public boolean validate() {
        boolean flag = true;
        int counter = 0;
        if (title.getText().toString().length() == 0) {
            title_wrapper.setError("Enter valid name");
            flag = false;
            counter++;
        }

        if (cooktime.getText().toString().length() == 0) {
            cooktime_wrapper.setError("Enter valid cook time");
            flag = false;
            counter++;
        }

        if (servings.getText().toString().length() == 0) {
            cooktime_wrapper.setError("Enter valid amount of servings");
            flag = false;
            counter++;
        }

        if (counter > 0) {
            return true;
        } else
            return false;
    }

    private class SubmitRecipeBtnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            submitRecipe();
        }

    }

}
