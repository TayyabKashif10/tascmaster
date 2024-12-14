package org.tasc.tascmaster.Models;

import org.tasc.tascmaster.entities.User;
import java.util.prefs.Preferences;

/*
* RESPONSIBLE FOR PERSISTENTLY MAINTAINING USER STATE INFO
* */

public class UserModel {

    private User user;
    static private UserModel instance;
    private static Preferences preferences = Preferences.userNodeForPackage(UserModel.class);
    private String accessToken;
    private String refreshToken;

    public void storeTokens(String access, String refresh){
        preferences.put("access_token" , access);
        preferences.put("refresh_token", refresh);
        accessToken = access;
        refreshToken = access;
    }

    public void clearTokens(){
        preferences.remove("access_token");
        preferences.remove("refresh_token");
        accessToken = null;
        refreshToken = null;
    }

    private  void fetchTokens() {
        accessToken = preferences.get("access_token" , null);
        refreshToken = preferences.get("refresh_token" , null);
    }


    private UserModel(){

    }

    static public UserModel getInstance()
    {
        if (instance == null){
            initialize();
        }

        return instance;
    }

    public boolean userLoggedIn(){
        return (user != null);
    }


    // should be ran once at Startup

    //TODO: handle access token expiry
    static private void initialize(){
        System.out.println("Initializing");
        instance = new UserModel();
        instance.fetchTokens();
        if (instance.accessToken != null){
            instance.setUser(User.fromAccessToken(instance.accessToken));
        }
    }


    public User getUser(){
        return user;
    }

    public  String getAccessToken() {
        return accessToken;
    }

    public  String getRefreshToken() {
        return refreshToken;
    }

    public void setUser(User newUser){
        this.user = newUser;
    }

    // should be called when user logs out
    // TODO: direct all logouts to here
    public void destroyUser()
    {
        this.user = null;
        clearTokens();
    }

}
