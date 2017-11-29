package com.emc_ideas.justaddsugar;
import java.util.*;
/**
 * Created by ecross on 10/4/17.
 */

public class mUserID {

    private UUID uID;
    private String fname;
    private String lname;
    private String email;
    private String password;

    public mUserID(){
        fname = lname = email = password = null;

    }

    public mUserID(String first, String last, String em, String pw){
        //generate random ID identifier
        uID = UUID.randomUUID();
        fname = first;
        lname = last;
        email = em;
        password = pw;
    }

    public UUID getuID() {
        return uID;
    }

    public void setuID(UUID uID) {
        this.uID = uID;
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

}
