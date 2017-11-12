package com.emc_ideas.justaddsugar;

import android.content.Intent;

/**
 * Created by ecross on 11/5/17.
 */

public class mIngredient {
    Measurement measurement;
    String foodItem;
    int [] quantity;
    //mCookbook book = new mCookbook();

    public mIngredient(){
       foodItem = null;
        quantity = new int[2];
    }

    public mIngredient(String item){
        foodItem = item;
       // book.setTitle(title);
        quantity = new int[2];
    }

    public mIngredient(String item, int quan, int remaining_quan){
        foodItem = item;
        setQuantity(quan, 0);
        setQuantity(remaining_quan, 1);
        //book.setTitle(title);
    }

    public mIngredient(String item, int quan, int remaining_quan, Measurement m){
        foodItem = item;
        setQuantity(quan, 0);
        setQuantity(remaining_quan, 1);
        setMeasurement(m);
       // book.setTitle(title);
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        //add a switch??
        this.measurement = measurement;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public int[] getQuantity() {
        return quantity;
    }

    public void setQuantity(int amt,int index) {
        quantity[index] = amt;
    }

}
