package org.tasc.tascmaster.util;

import org.json.JSONObject;

import java.util.Base64;

public class Helper{

    static public JSONObject decodeJWT(String jwt){
        // Split the JWT into parts
        String[] parts = jwt.split("\\.");

        // Decode the payload (2nd part)
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));

        return new JSONObject(payload);
    }
}
