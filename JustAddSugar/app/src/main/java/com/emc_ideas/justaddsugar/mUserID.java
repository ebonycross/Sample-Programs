package com.emc_ideas.justaddsugar;
import java.util.*;
/**
 * Created by ecross on 10/4/17.
 */

public class mUserID {

    private UUID uID;
    private String username;
    private String email;
    private String password;

    public mUserID(){
        username = email = password = null;

    }

    public mUserID(String name, String em, String pw){
        //generate random ID identifier
        uID = UUID.randomUUID();
        username = name;
        email = em;
        password = pw;
    }

    public UUID getuID() {
        return uID;
    }

    public void setuID(UUID uID) {
        this.uID = uID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
