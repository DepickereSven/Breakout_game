package nu.smashit.socket.actions;

// @author Jonas

import nu.smashit.socket.Client;

public class ScoresRequestAction implements RequestAction{

    @Override
    public void handler(Client c) {
        c.sendAction( new ScoresResponseAction() );
    }

}
