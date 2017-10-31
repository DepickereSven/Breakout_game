/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket.actions;

/**
 *
 * @author jodus
 */
public class JoinGameSuccessAction implements ResponseAction {

    public final String key;

    public JoinGameSuccessAction(String key) {
        this.key = key;
    }
}
