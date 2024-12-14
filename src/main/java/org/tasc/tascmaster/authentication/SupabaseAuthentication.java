package org.tasc.tascmaster.authentication;
import java.io.IOException;
import java.net.http.HttpResponse;

import org.json.JSONObject;
import org.tasc.tascmaster.Models.UserModel;
import org.tasc.tascmaster.database_https.Requests;
import org.tasc.tascmaster.entities.User;

public class SupabaseAuthentication {

    public static int login(String email, String password) throws IOException, InterruptedException {

        HttpResponse<String> response = Requests.getResponse(Requests.buildLoginRequest(email, password));

        // logged in successfully.
        if (response.statusCode() == 200)
        {
            JSONObject responseBody = new JSONObject(response.body());

            String accessToken = responseBody.getString("access_token");
            String refreshToken = responseBody.getString("refresh_token");

            // store tokens for persistence.
            UserModel.getInstance().storeTokens(accessToken, refreshToken);

            // sync user up.
            UserModel.getInstance().setUser(User.fromAccessToken(accessToken));

        }
        else{
            System.err.println("Erroneous response:" +  response.body());
        }

        return response.statusCode();
    }

}
