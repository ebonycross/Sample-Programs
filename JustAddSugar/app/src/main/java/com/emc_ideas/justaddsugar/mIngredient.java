package com.emc_ideas.justaddsugar;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ecross on 11/5/17.
 */

public class mIngredient implements Parcelable {
    Measurement measurement;
    String foodItem;
    int quantity1;
    int remain_quantity;
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
        quantity1 = remain_quantity = 0;
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
        return quantity1;
    }

    public int getRemaingAmt() {
        return remain_quantity;
    }

    public void setAmt(int amt) {
        quantity1 = amt;
    }
    public void setRemaingAmt(int amt) {
        remain_quantity = amt;
    }


    //paracable interface
    public static final Parcelable.Creator<mIngredient> CREATOR = new Parcelable.Creator<mIngredient>() {
        @Override
        public mIngredient createFromParcel(Parcel source) {
            return new mIngredient(source);

        }

        @Override
        public mIngredient[] newArray(int size) {
            return new mIngredient[size];
        }
    };


    //parcelling part
    public mIngredient(Parcel in){
        this.foodItem = in.readString();
        this.quantity1 = in.readInt();
        this.remain_quantity = in.readInt();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel outParcel, int flags) {
        outParcel.writeString(foodItem);
        outParcel.writeInt(quantity1);
        outParcel.writeInt(remain_quantity);
    }

}
