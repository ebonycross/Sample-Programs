package com.emc_ideas.justaddsugar;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ecross on 11/14/17.
 */

public class Adapter_Ingredient extends RecyclerView.Adapter<Adapter_Ingredient.ViewHolder> {
    private List<mIngredient> ingredientList;
    Context context;

    /*
    to get a ref to get item in the list of cookbooks
    call inner private class. use View Holder to see all
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        //grab items from the cardview

        TextView amt, remain_amt, ingred_name, meas, num;
        ImageView ingred_cancelBtn;
        CardView cv;
        Spinner quan_spin, remain_spin, meas_spin;

        //LinearLayout ingred_view;


        //public RelativeLayout viewBackground, viewForeground;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cv = (CardView) itemView.findViewById(R.id.bookHolder2);
            num = (TextView) itemView.findViewById(R.id.ingred_num);
            amt = (TextView) itemView.findViewById(R.id.ingred_amt);
            remain_amt = (TextView) itemView.findViewById(R.id.ingred_amt_remain);
            ingred_name = (TextView) itemView.findViewById(R.id.ingred_title);
            meas = (TextView) itemView.findViewById(R.id.ingred_meas);
            ingred_cancelBtn = (ImageView) itemView.findViewById(R.id.ingred_cancelBtn);
            //quan_spin = (Spinner) itemView.findViewById(R.id.spinner1);
            //remain_spin = (Spinner) itemView.findViewById(R.id.spinner2);
            //meas_spin = (Spinner) itemView.findViewById(R.id.spinner3);
            //ingred_view = (LinearLayout) itemView.findViewById(R.id.add_Recipe_holder);

        }//end of viewholder constructor

        @Override
        public void onClick(View v) {
        }

    }//end of inner class


    //constructor of adapter
    public Adapter_Ingredient(List<mIngredient> bookList, Context context) {
        //updateList(bookList);
        this.context = context;

        this.ingredientList = bookList;
    }


    public void updateList(List<mIngredient> dataset) {
        ingredientList.clear();
        ingredientList.addAll(dataset);
        notifyDataSetChanged();
    }


    //create new view when inviked by the layout manager
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_list, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    //replace the contents of a view that is invoked by the laoyout manager
    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {

        //1. get the element from arraylist at this position
        //2. replace contents of the view with that element
        mIngredient ingredient = ingredientList.get(i);
       // holder.amt.setText(ingredient.getAmt());
        //holder.remain_amt.setText(ingredient.getRemaingAmt());
        //holder.meas.setText(ingredient.getMeasurement());
        holder.num.setText((i+1)+".");
        holder.ingred_name.setText(ingredient.getFoodItem());
        holder.amt.setText(ingredient.getAmt());
        holder.remain_amt.setText(ingredient.getRemaingAmt());
        holder.meas.setText(ingredient.getMeasurement());

    }

    //setting the arraylist
    public void setListContent(List<mIngredient> list_members) {
        ingredientList = list_members;
        notifyItemRangeChanged(0, ingredientList.size());
    }

    public void removeAt(int i) {
        if (ingredientList.size() > 0) {
            ingredientList.remove(i);
            //notify adapter that item preivously has been removed from the data set
            notifyItemRemoved(i);

            //notify any registered oberevers that itemCount itmes poistion has changed as well
            notifyItemRangeChanged(0, ingredientList.size());
        } else { //create a label to tell user no more items in the list}

        }
    }

    public void insertItem(mIngredient c) {
        ingredientList.add(c);
        notifyItemInserted(getItemCount());
    }

    public void updateList(mIngredient c) {
        insertItem(c);
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView rv1) {
        super.onAttachedToRecyclerView(rv1);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public List<mIngredient> getIngredientList() {
        return ingredientList;
    }

    public void restoreItem(mIngredient item, int position) {
        ingredientList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }

}


