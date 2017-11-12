package com.emc_ideas.justaddsugar;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ecross on 10/25/17.
 */

public class mCookbook {

    //data members
    private String title;
    private ArrayList<String> coAuthors;
    private String author;
    private UUID uID;
    private R.drawable picture;
    private List<mRecipe> recipeList;

    public mCookbook() {
        title = author = "pow";
        coAuthors = new ArrayList<String>();
        recipeList = new ArrayList<mRecipe>();
    }

    public mCookbook(String t) {
        uID = UUID.randomUUID();
        title = t;
        coAuthors = new ArrayList<String>();
        recipeList = new ArrayList<mRecipe>();
    }

    public mCookbook(String t, String a) {
        uID = UUID.randomUUID();
        title = t;
        coAuthors = new ArrayList<String>();
        author = a;
        recipeList = new ArrayList<mRecipe>();
    }

    public mCookbook(String t, String a, R.drawable icon) {
        uID = UUID.randomUUID();
        title = t;
        coAuthors = new ArrayList<String>();
        picture = icon;
        author = a;
        recipeList = new ArrayList<mRecipe>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //add other users to the list of co-publishers for cookbook
    public ArrayList<String> addAuthors(String name) {
        coAuthors.add(name);
        return coAuthors;
    }

    public ArrayList<String> removeAuthors(String name) {
        for (int i = 0; i < coAuthors.size(); i++) {
            if (coAuthors.get(i).equals(name))
                coAuthors.remove(i);
        }
        return coAuthors;
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


    public UUID getuID() {
        return uID;
    }

    public void setuID(UUID uID) {
        this.uID = uID;
    }

    public R.drawable getPicture() {
        return picture;
    }

    public void setPicture(R.drawable picture) {
        this.picture = picture;
    }

    public ArrayList<String> getCoAuthors() {
        return coAuthors;
    }

    public void setCoAuthors(String author, int i) {
        coAuthors.get(i).replace(coAuthors.get(i), author);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
