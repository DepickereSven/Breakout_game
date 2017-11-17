/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket.actions;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.concurrent.Future;
import nu.smashit.data.MySqlUserRepository;
import nu.smashit.data.Repositories;
import nu.smashit.data.UserRepository;
import nu.smashit.data.dataobjects.User;
import nu.smashit.socket.Client;
import nu.smashit.utils.BreakoutException;
import org.json.JSONObject;

/**
 *
 * @author jodus
 */
public class UserLoginRequestAction implements RequestAction {

    private static final String CLIENT_ID = "870997935508-c4325ugimh126ub88kl8o5c8nr2ms6ot.apps.googleusercontent.com";

    public String country;
    public String token;

    @Override
    public void handler(Client c) {
        Future<HttpResponse<JsonNode>> future = Unirest.post("https://www.googleapis.com/oauth2/v3/tokeninfo")
                .queryString("id_token", token)
                .asJsonAsync(new Callback<JsonNode>() {

                    @Override
                    public void failed(UnirestException e) {
                        c.sendAction(new UserLoginFailureAction());
                    }

                    @Override
                    public void completed(HttpResponse<JsonNode> response) {
                        UserRepository userRepo = Repositories.getUserRepository();
                        int code = response.getStatus();

                        if (code != 200) {
                            c.sendAction(new UserLoginFailureAction());
                            return;
                        }

                        JSONObject body = response.getBody().getObject();

                        String aud = body.getString("aud");

                        if (!aud.equalsIgnoreCase(CLIENT_ID)) {
                            c.sendAction(new UserLoginFailureAction());
                            return;
                        }

                        String userID = body.getString("sub");
                        String email = body.getString("email");
                        String username = body.getString("name");
                        String imageUrl = body.getString("picture");

                        User user;
                        try {
                            user = userRepo.getUser(userID);
                        } catch (Exception ex) {
                            user = new User(userID, email, 0, username, imageUrl, country);
                            userRepo.addUser(user);
                        }

                        c.setUser(user);

                        c.sendAction(new UserLoginSuccessAction(user));
                    }

                    @Override
                    public void cancelled() {
                        c.sendAction(new UserLoginFailureAction());
                    }

                });
    }

}
