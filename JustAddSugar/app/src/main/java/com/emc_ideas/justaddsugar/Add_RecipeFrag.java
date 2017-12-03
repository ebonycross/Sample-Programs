package com.emc_ideas.justaddsugar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ecross on 11/7/17.
 */

public class Add_RecipeFrag extends Fragment  {

    public static final String TAG = "ADD RECIPE FRAG";

    FragmentManager fm;
    Context mContext;
    private RecyclerView mAddRecycler;
    private RecyclerView.LayoutManager mLayoutMgr;
    private Intent i;

    private Adapter_Ingredient ingredAdapter;
    private EditText title, cooktime, servings, ingred_name;
    private TextInputLayout title_wrapper, cooktime_wrapper, serving_wrapper, ingred_name_wrapper;
    private mRecipe recipe = new mRecipe();
    public List<mIngredient> ingredients;
    private Button submitBtn;
    private Button add_RecipeBtn, cancel_igBtn;
    private mIngredient ig;
    private mCookbook book;
    private Bundle bundle;
    private String book_pushID;
    private TextView amt, fraction, meas_amt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initItems();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.add_recipe_frag_layout, parent, false);

        return v;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceSate) {
        super.onViewCreated(view, savedInstanceSate);
/*
        bundle = this.getArguments();
        if(bundle != null){
           book = bundle.getParcelable(Constants.COOKBOOK_OBJ_KEY);

           book_pushID = bundle.getString(Constants.COOKBOOK_ID_KEY);
            Toast.makeText(getActivity(), "Found cookbook nammed " + book.getTitle() + " and ID: "
                    + book_pushID, Toast.LENGTH_LONG).show();

        }
*/
        title = (EditText) getView().findViewById(R.id.form_title);
        cooktime = (EditText) getView().findViewById(R.id.form_cooktime);
        servings = (EditText) getView().findViewById(R.id.form_serving);
        ingred_name = (EditText) getView().findViewById(R.id.form_ingred_name);
        meas_amt = (TextView) getView().findViewById(R.id.spin_pick_meas);
        amt = (TextView) getView().findViewById(R.id.spin_pick_amt);
        fraction = (TextView) getView().findViewById(R.id.spin_pick_remain_amt);

        title_wrapper = (TextInputLayout) getView().findViewById(R.id.form_title_wrapper);
        cooktime_wrapper = (TextInputLayout) getView().findViewById(R.id.form_cooktime_wrapper);
        serving_wrapper = (TextInputLayout) getView().findViewById(R.id.form_serving_wrapper);
        ingred_name_wrapper = (TextInputLayout) getView().findViewById(R.id.form_ingred_name_wrapper);
        add_RecipeBtn = (Button) getView().findViewById(R.id.add_RecipeBtn);
        submitBtn = (Button) getView().findViewById(R.id.submit_recipe_btn);
        cancel_igBtn = (Button) getView().findViewById(R.id.ingred_cancelBtn);



        //SPINNER: QUANTITY WHOLE NUMBERS
        Spinner quantitySpinner1 = (Spinner) getView().findViewById(R.id.spinner1);

        String[] items = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, items);

        quantitySpinner1.setAdapter(adapter1);

        quantitySpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //ig = new mIngredient();
                Log.i(TAG, parent.getItemAtPosition(position).toString());


                //ig.setAmt(parent.getItemAtPosition(position).toString());
                String item = parent.getItemAtPosition(position).toString();
                amt.setText(item);
                //ig.setAmt(item);
                Log.i(TAG, "after" +item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        //SPINNER: REMAINNING QUANTITY
        Spinner quantitySpinner2 = (Spinner) getView().findViewById(R.id.spinner2);

        String[] items2 = new String[] { "1/8", "1/4", "1/2", "1/3", "2/3", "3/4"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, items2);

        quantitySpinner2.setAdapter(adapter2);

        quantitySpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //ig = new mIngredient();
                Log.i(TAG, parent.getItemAtPosition(position).toString());

                //ig.setAmt(parent.getItemAtPosition(position).toString());
                String item = parent.getItemAtPosition(position).toString();

                fraction.setText(item);
                // ig.setRemaingAmt(item);
                Log.i(TAG, "after" +item);

                // get the removed item name to display it in snack bar

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        //SPINNER: MEASUREMENT
        Spinner measSpin = (Spinner) getView().findViewById(R.id.spinner3);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> measAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.meas_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        measAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        measSpin.setAdapter(measAdapter);



        measSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                //ig = new mIngredient();
                Log.i(TAG, parent.getItemAtPosition(position).toString());


                //ig.setAmt(parent.getItemAtPosition(position).toString());
                String item = parent.getItemAtPosition(position).toString();
               //ig.setMeasurement(item);
                meas_amt.setText(item);
                Log.i(TAG, "after" +item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });



        //ingredients = new ArrayList<mIngredient>();
        //submitBtn.setOnClickListener(new SubmitRecipeBtnClick());

        mContext = getActivity();


        mAddRecycler = (RecyclerView) view.findViewById(R.id.rv_addRecipe);


        if (mAddRecycler != null) {
            mAddRecycler.setHasFixedSize(true);
        }

        //linearLayoutManager is user here, this will layout elems in like fashion of
        //listView would do element. The RecyclerView.LayoutManager defines how elements
        //are laid out
        mLayoutMgr = new LinearLayoutManager(mContext);

        mAddRecycler.setLayoutManager(mLayoutMgr);

        //mIngredient m = new mIngredient();
        //m.setFoodItem("Food");
        //Toast.makeText(getActivity(), "Food :" + m.getFoodItem() ,Toast.LENGTH_SHORT).show();


        //initialize adapter to list of books
        ingredAdapter = new Adapter_Ingredient(ingredients, getContext());
        //ingredAdapter.insertItem(m);



        mAddRecycler.addOnItemTouchListener(new RecyclerTouchListener(mContext,
                mAddRecycler, new RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                //Values are passing to activity & to fragment as well
                Toast.makeText(getActivity(), "Single Click on position        :" + position,
                        Toast.LENGTH_SHORT).show();

                String name = ingredients.get(position).getFoodItem();


                // backup of removed item for undo purpose
                final mIngredient deletedItem = ingredients.get(position);
                final int deletedIndex = position;


                // remove the item from recycler view
                ingredAdapter.removeAt(position);

                //remove book from database
                //cookbooks.put(deletedItem.getId(),null);
                //bookRef.updateChildren(cookbooks);

                ingredAdapter.notifyDataSetChanged();

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();

            }
        }));




        mAddRecycler.setItemAnimator(new DefaultItemAnimator());
        mAddRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        mAddRecycler.setAdapter(ingredAdapter);
        ingredAdapter.notifyDataSetChanged();

        fm = ((FragmentActivity) mContext).getSupportFragmentManager();


        add_RecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String name = ingred_name.getText().toString();
                    String meas = meas_amt.getText().toString();
                    String remain = fraction.getText().toString();
                    String amount = amt.getText().toString();

                    mIngredient c = new mIngredient();
                    c.setFoodItem(name);
                    c.setMeasurement(meas);
                    c.setRemaingAmt(remain);
                    c.setAmt(amount);
                    ingredAdapter.insertItem(c);
                    int position = ingredAdapter.getItemCount();

                    mAddRecycler.scrollToPosition(position);

                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }

            }
        });
/*

        mAddRecycler.addOnItemTouchListener(new Add_RecipeFrag.RecyclerTouchListener(mContext,
                mAddRecycler, new RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                //Values are passing to activity & to fragment as well
                Toast.makeText(getActivity(), "Single Click on position   :" + position,
                        Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();


            }
        }));*/
    }

    public void initItems() {
        ingredients = new ArrayList<mIngredient>();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
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
            //title_wrapper.setError("Enter valid name");
            flag = false;
            counter++;
        }

        if (cooktime.getText().toString().length() == 0) {
            // cooktime_wrapper.setError("Enter valid cook time");
            flag = false;
            counter++;
        }

        if (servings.getText().toString().length() == 0) {
            serving_wrapper.setError("Enter valid amount of servings");
            flag = false;
            counter++;
        }

        if (ingred_name.getText().toString().length() == 0) {
            ingred_name_wrapper.setError("Enter valid ingredient");
            flag = false;
            counter++;
        }

        if (counter > 0) {
            return true;
        } else
            return false;
    }

    /*INNER CLASS*/
    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private RecyclerViewClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final RecyclerViewClickListener clicklistener) {

            this.clicklistener = clicklistener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }//END of INNER CLASS



    /*INNNER CLASSES*/
    private class SubmitRecipeBtnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            submitRecipe();
        }

    }


}
