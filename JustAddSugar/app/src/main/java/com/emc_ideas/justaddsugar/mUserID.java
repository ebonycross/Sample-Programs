package com.emc_ideas.justaddsugar;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.*;
/**
 * Created by ecross on 10/4/17.
 */

public class mUserID implements Parcelable {


    private String fname;
    private String lname;
    private String email;
    private String password;
    private String id;

    public mUserID(){
        // fname = lname = email = password = null;

    }
    public mUserID(mUserID user){
        fname = user.fname;
        lname = user.lname;
        email = user.email;
        id = user.id;
    }


    public mUserID(String first, String last, String em){
        //generate random ID identifier
        fname = first;
        lname = last;
        email = em;
    }

    public mUserID(String first, String last, String em, String i){
        //generate random ID identifier
        fname = first;
        lname = last;
        email = em;
        id = i;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }





    protected mUserID(Parcel in) {
        fname = in.readString();
        lname = in.readString();
        email = in.readString();
        password = in.readString();
        id = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fname);
        dest.writeString(lname);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<mUserID> CREATOR = new Parcelable.Creator<mUserID>() {
        @Override
        public mUserID createFromParcel(Parcel in) {
            return new mUserID(in);
        }

        @Override
        public mUserID[] newArray(int size) {
            return new mUserID[size];
        }
    };
}