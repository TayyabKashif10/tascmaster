package org.tasc.tascmaster.entities;

import org.json.JSONObject;
import org.tasc.tascmaster.util.Helper;

import java.util.Base64;

public class User{
    private String UID;
    private USER_ROLE role;
    private String email;

    public User(String UID, String role, String email){
        this.UID = UID;
        this.role = USER_ROLE.valueOf(role);
        this.email = email;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public USER_ROLE getRole() {
        return role;
    }

    public void setRole(USER_ROLE role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static User fromAccessToken(String access_token){

        JSONObject jwt = Helper.decodeJWT(access_token);

        return new User(jwt.getString("sub") ,jwt.getString("db_role"),jwt.getString("email"));

    }

}
