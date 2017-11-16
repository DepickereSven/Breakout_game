/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket.actions;

import nu.smashit.data.dataobjects.User;
import nu.smashit.socket.Client;

/**
 *
 * @author jodus
 */
public class UserLoginRequestAction implements RequestAction {

    public String id;
    public String username;
    public String email;
    public String imageUrl;
    public String country;

    @Override
    public void handler(Client c) {
        User u = new User(id, email, 0, username, imageUrl, country);

        c.sendAction(new UserLoginSuccessAction());
    }

}
