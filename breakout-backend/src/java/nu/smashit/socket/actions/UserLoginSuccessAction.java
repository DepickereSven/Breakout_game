/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket.actions;

import nu.smashit.data.dataobjects.User;

/**
 *
 * @author jodus
 */
public class UserLoginSuccessAction implements ResponseAction {

    public final User user;

    public UserLoginSuccessAction(User user) {
        this.user = user;
    }
}
