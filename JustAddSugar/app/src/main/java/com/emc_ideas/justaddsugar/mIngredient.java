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
        //quantity = new int[2];
        //measurement = Measurement.Null;
    }

    public mIngredient(String item){
        foodItem = item;
       // book.setTitle(title);
        measurement = Measurement.Null;
        quantity = new int[2];
    }

    public mIngredient(String item, int quan, int remaining_quan){
        foodItem = item;
        setAmt(quan);
        setRemaingAmt(remaining_quan);
        measurement = Measurement.Null;
        //book.setTitle(title);
    }

    public mIngredient(String item, int quan, int remaining_quan, Measurement m){
        foodItem = item;
        setAmt(quan);
        setRemaingAmt(remaining_quan);
        setMeasurement(m);
       // book.setTitle(title);
    }

    public String getMeasurement() {
        return measurement.getMeasurements();
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

    public int getAmt() {
        return quantity[0];
    }

    public int getRemaingAmt() {
        return quantity[1];
    }

    public void setAmt(int amt) {
        quantity[0] = amt;
    }
    public void setRemaingAmt(int amt) {
        quantity[1] = amt;
    }

}
