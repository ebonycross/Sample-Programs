package com.emc_ideas.justaddsugar;

import android.os.Parcel;
import android.os.Parcelable;

import com.emc_ideas.justaddsugar.mIngredient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ecross on 11/5/17.
 */

public class mRecipe implements Parcelable{
    /*
    object contains:
    1. list of ingredients (incl. measurement amt)
    2. measurement
    3. directions
    4. cooktime
    5. serving amount
     */
    private List<mIngredient> ingredient;
    private String direction;
    private String cooktime;
    private int servingAmt;
    private String pushID;
    private String date;
    private Date d;
    private DateFormat df;
    private String author;



    private String title;

    public mRecipe(){
        ingredient = new ArrayList<mIngredient>();
        direction = null;
        cooktime = null;
        servingAmt = 0;
        date = getDate();
        author = pushID =title = null;
    }
    public mRecipe(String t, String dir, String time, int serv){
        title = t;
        ingredient = new ArrayList<mIngredient>();
        direction = dir;
        cooktime = time;
        servingAmt = serv;
        date = getDate();
    }

    public mRecipe(String t, String dir, String time, int serv, String pushID){
        title = t;
        ingredient = new ArrayList<mIngredient>();
        direction = dir;
        cooktime = time;
        servingAmt = serv;
        date = getDate();
        this.pushID = pushID;
    }

    public List<mIngredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<mIngredient> ingredient) {
        this.ingredient = ingredient;
    }

    public List<mIngredient> addIngredient(mIngredient ingred){
        ingredient.add(ingred);
        return ingredient;
    }

    public List<mIngredient> removeIngredient(mIngredient ingred, int i){
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

    public String getPublishDate(Date dt, DateFormat df2){
        dt = new Date();
       df2 = new SimpleDateFormat("dd/MM/yyyy");
       return df2.format(d);

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPushID() {
        return pushID;
    }

    public void setPushID(String pushID) {
        this.pushID = pushID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    //paracable interface


    protected mRecipe(Parcel in) {
        if (in.readByte() == 0x01) {
            ingredient = new ArrayList<mIngredient>();
            in.readList(ingredient, mIngredient.class.getClassLoader());
        } else {
            ingredient = null;
        }
        direction = in.readString();
        cooktime = in.readString();
        servingAmt = in.readInt();
        pushID = in.readString();
        date = in.readString();
       // long tmpD = in.readLong();
        //d = tmpD != -1 ? new Date(tmpD) : null;
       // df = (DateFormat) in.readValue(DateFormat.class.getClassLoader());
        author = in.readString();
        title = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (ingredient == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(ingredient);
        }
        dest.writeString(direction);
        dest.writeString(cooktime);
        dest.writeInt(servingAmt);
        dest.writeString(pushID);
        dest.writeString(date);
        //dest.writeLong(d != null ? d.getTime() : -1L);
        //dest.writeValue(df);
        dest.writeString(author);
        dest.writeString(title);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<mRecipe> CREATOR = new Parcelable.Creator<mRecipe>() {
        @Override
        public mRecipe createFromParcel(Parcel in) {
            return new mRecipe(in);
        }

        @Override
        public mRecipe[] newArray(int size) {
            return new mRecipe[size];
        }
    };
}

