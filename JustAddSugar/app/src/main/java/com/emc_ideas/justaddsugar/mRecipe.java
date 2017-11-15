package com.emc_ideas.justaddsugar;

import com.emc_ideas.justaddsugar.mIngredient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ecross on 11/5/17.
 */

public class mRecipe {
    /*
    object contains:
    1. list of ingredients (incl. measurement amt)
    2. measurement
    3. directions
    4. cooktime
    5. serving amount
     */
    private ArrayList<mIngredient> ingredient;
    private String direction;
    private String cooktime;
    private int servingAmt;
    private Date d;
    private DateFormat df;



    private String title;

    public mRecipe(){
        ingredient = new ArrayList<mIngredient>();
        direction = null;
        cooktime = null;
        servingAmt = 0;
        d = new Date();
        title = null;
    }

    public mRecipe(String t, String dir, String time, int serv){
        title = t;
        ingredient = new ArrayList<mIngredient>();
        direction = dir;
        cooktime = time;
        servingAmt = serv;
        d = new Date();
    }

    public ArrayList<mIngredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(ArrayList<mIngredient> ingredient) {
        this.ingredient = ingredient;
    }

    public ArrayList<mIngredient> addIngredient(mIngredient ingred){
        ingredient.add(ingred);
        return ingredient;
    }

    public ArrayList<mIngredient> removeIngredient(mIngredient ingred, int i){
        ingredient.remove(i);
        return ingredient;
    }

    public int getItemCount(){
        return ingredient.size();
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCooktime() {
        return cooktime;
    }

    public void setCooktime(String cooktime) {
        this.cooktime = cooktime + " minutes";
    }

    public int getServingAmt() {
        return servingAmt;
    }

    public void setServingAmt(int servingAmt) {
        this.servingAmt = servingAmt;
    }

    public String getPublishDate(){
       df = new SimpleDateFormat("dd/MM/yyyy");
       return df.format(d);

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
