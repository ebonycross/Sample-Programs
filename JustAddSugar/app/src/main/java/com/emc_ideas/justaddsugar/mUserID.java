package com.emc_ideas.justaddsugar;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.*;
/**
 * Created by ecross on 10/4/17.
 */

public class mUserID  implements Parcelable{


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


    //paracable interface
    public static final Parcelable.Creator<mUserID> CREATOR = new Parcelable.Creator<mUserID>() {
        @Override
        public mUserID createFromParcel(Parcel source) {
            return new mUserID(source);

        }

        @Override
        public mUserID[] newArray(int size) {
            return new mUserID[size];
        }
    };


    //parcelling part
    public mUserID(Parcel in){
        this.id = in.readString();
        this.fname = in.readString();
        this.lname = in.readString();
        this.password = in.readString();
        this.email = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel outParcel, int flags) {
        outParcel.writeString(fname);
        outParcel.writeString(id);
        outParcel.writeString(lname);
        outParcel.writeString(password);
        outParcel.writeString(email);

    }

}
