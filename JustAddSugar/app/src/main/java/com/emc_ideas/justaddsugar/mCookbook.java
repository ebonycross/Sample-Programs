package com.emc_ideas.justaddsugar;

import com.emc_ideas.justaddsugar.R;

import java.util.ArrayList;
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

    public mCookbook() {
        title = author = null;
        coAuthors = new ArrayList<String>();
    }

    public mCookbook(String t, String a) {
        uID = UUID.randomUUID();
        title = t;
        coAuthors = new ArrayList<String>();
        author = a;
    }

    public mCookbook(String t, String a, R.drawable icon) {
        uID = UUID.randomUUID();
        title = t;
        coAuthors = new ArrayList<String>();
        picture = icon;
        author = a;
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

    public void setCoAuthors(ArrayList<String> coAuthors) {
        this.coAuthors = coAuthors;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
