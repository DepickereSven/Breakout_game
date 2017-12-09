package nu.smashit.socket.actions;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.concurrent.Future;
import nu.smashit.data.Repositories;
import nu.smashit.data.UserRepository;
import nu.smashit.data.dataobjects.User;
import nu.smashit.socket.Client;
import org.json.JSONObject;

/**
 *
 * @author jodus
 */
public class UserLoginRequestAction implements RequestAction {

    private static final String CLIENT_ID = "870997935508-c4325ugimh126ub88kl8o5c8nr2ms6ot.apps.googleusercontent.com";

    public String country;
    public String token;

    private User createUserFromJSONBody(Client c, JSONObject body) {
        String userID = body.getString("sub");
        String email = body.getString("email");
        String username = body.getString("name");
        String imageUrl = body.getString("picture");

        UserRepository userRepo = Repositories.getUserRepository();
        User user;
        try {
            user = userRepo.getUser(userID)
                    .setClient(c)
                    .build();
        } catch (Exception ex) {
            user = User.builder()
                    .setUserData(userID, email, 0, username, imageUrl, country)
                    .setClient(c)
                    .build();
            userRepo.addUser(user);
        }
        return user;
    }

    @Override
    public void handler(Client c) {
        boolean isAccessToken = token.startsWith("ya29");
        String url = isAccessToken ? "https://www.googleapis.com/oauth2/v3/userinfo" : "https://www.googleapis.com/oauth2/v3/tokeninfo";
        Future<HttpResponse<JsonNode>> future = Unirest.post(url)
                .queryString(isAccessToken ? "access_token" : "id_token", token)
                .asJsonAsync(new Callback<JsonNode>() {

                    @Override
                    public void failed(UnirestException e) {
                        c.sendAction(new UserLoginFailureAction());
                    }

                    @Override
                    public void completed(HttpResponse<JsonNode> response) {
                        try {
                            int code = response.getStatus();
                            if (code != 200) {
                                c.sendAction(new UserLoginFailureAction());
                                return;
                            }

                            JSONObject body = response.getBody().getObject();

                            User user = createUserFromJSONBody(c, body);
                            c.setUser(user);
                            c.sendAction(new UserLoginSuccessAction(user));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            c.sendAction(new UserLoginFailureAction());
                        }
                    }

                    @Override
                    public void cancelled() {
                        c.sendAction(new UserLoginFailureAction());
                    }

                });
    }

}
