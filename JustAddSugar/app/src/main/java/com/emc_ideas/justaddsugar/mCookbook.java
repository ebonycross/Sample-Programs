package com.emc_ideas.justaddsugar;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ecross on 10/25/17.
 */

public class mCookbook implements Parcelable {

    //data members
    private String title;
    private List<mUserID> membership;
    private mUserID user;
    private String author;
    private String id;
    private R.drawable picture;
    private List<mRecipe> recipeList;

    public mCookbook() {
        title = id = null;
        membership = new ArrayList<mUserID>();
        recipeList = new ArrayList<mRecipe>();
    }

    public mCookbook(String a, String t) {
        author = a;
        title = t;
        membership = new ArrayList<mUserID>();
        recipeList = new ArrayList<mRecipe>();
    }

    public mCookbook(String a, String t,String id) {
        author = a;
        this.id = id;
        title = t;
        membership = new ArrayList<mUserID>();
        recipeList = new ArrayList<mRecipe>();
    }

    public mCookbook(String a, String t, mUserID u, String id) {
        author = a;
        title = t;
        this.id = id;
        membership = new ArrayList<mUserID>();
        membership.add(u);
        recipeList = new ArrayList<mRecipe>();
    }

    public mCookbook(String t, R.drawable icon) {

        title = t;
        membership = new ArrayList<mUserID>();
        picture = icon;
        recipeList = new ArrayList<mRecipe>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //add other users to the list of co-publishers for cookbook
    public List<mUserID> addMember(mUserID u) {
        membership.add(u);
        return membership;
    }

    public List<mUserID> removeAuthors(mUserID u) {
        membership.remove(u);
        return membership;
    }

    //add other users to the list of co-publishers for cookbook
    public List<mRecipe> addRecipe(mRecipe r) {
        recipeList.add(r);
        return recipeList;
    }

    public List<mRecipe> removeRecipe(int index) {
        recipeList.remove(index);
        return recipeList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public R.drawable getPicture() {
        return picture;
    }

    public void setPicture(R.drawable picture) {
        this.picture = picture;
    }

    public mUserID getMembers(int i) {
        return membership.get(i);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    //paracable interface
    public static final Parcelable.Creator<mCookbook> CREATOR = new Creator<mCookbook>() {
        @Override
        public mCookbook createFromParcel(Parcel source) {
            return new mCookbook(source);

        }

        @Override
        public mCookbook[] newArray(int size) {
            return new mCookbook[size];
        }
    };


    //parcelling part
    public mCookbook(Parcel in){
        this.id = in.readString();
        this.author = in.readString();
        this.title = in.readString();
        this.membership = new ArrayList<mUserID>();
        this.recipeList = new ArrayList<>();
        this.membership = in.readTypedList(membership, mUserID.CREATOR);
        this.recipeList = in.readTypedList(recipeList, mRecipe.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel outParcel, int flags) {
        outParcel.writeString(author);
        outParcel.writeString(id);
        outParcel.writeString(title);
        outParcel.writeTypedList(membership);
        outParcel.writeTypedList(recipeList);
    }

}
