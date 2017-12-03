package com.emc_ideas.justaddsugar;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by ecross on 11/5/17.
 */

public class mIngredient implements Parcelable {
    private String measurement;
   private  String foodItem;
    private String quantity1;
   private  String remain_quantity;
    private String pushID;
    //mCookbook book = new mCookbook();

    public mIngredient(){
        foodItem = null;
        quantity1 = remain_quantity = null;
        //quantity = new int[2];
        //measurement = Measurement.Null;
    }

    public mIngredient(String item){
        foodItem = item;
        // book.setTitle(title);
        //measurement = Measurement.Null;
        quantity1 = remain_quantity = null;
    }

    public mIngredient(String item, String quan, String remaining_quan){
        foodItem = item;
        setAmt(quan);
        setRemaingAmt(remaining_quan);
        measurement = null;
        //book.setTitle(title);
    }

    public mIngredient(String item, String quan, String remaining_quan, String m){
        foodItem = item;
        setAmt(quan);
        setRemaingAmt(remaining_quan);
        setMeasurement(m);
        // book.setTitle(title);
    }


    public String getMeasurement() {
       return measurement;
    }

    public void setMeasurement(String measure) {

        measurement = measure;
        /*
        for(Measurement m : Measurement.values()){
            if(measure.equals(m.getMeasurementsString()))
                this.measurement = m;
        }
        */
    }

    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public String getAmt() {
        return quantity1;
    }

    public String getRemaingAmt() {
        return remain_quantity;
    }

    public void setAmt(String amt) {
        quantity1 = amt;
    }
    public void setRemaingAmt(String amt) {
        remain_quantity = amt;
    }


    public String getPushID() {
        return pushID;
    }

    public void setPushID(String pushID) {
        this.pushID = pushID;
    }

    protected mIngredient(Parcel in) {
        measurement = in.readString();
        foodItem = in.readString();
        quantity1 = in.readString();
        remain_quantity = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(measurement);
        dest.writeString(foodItem);
        dest.writeString(quantity1);
        dest.writeString(remain_quantity);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<mIngredient> CREATOR = new Parcelable.Creator<mIngredient>() {
        @Override
        public mIngredient createFromParcel(Parcel in) {
            return new mIngredient(in);
        }

        @Override
        public mIngredient[] newArray(int size) {
            return new mIngredient[size];
        }
    };
}