package nu.smashit.socket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import nu.smashit.core.GameSession;
import nu.smashit.core.GameSessionManager;
import nu.smashit.utils.Logger;

@ServerEndpoint(
        value = "/socket",
        encoders = {ActionEncoder.class},
        decoders = {ActionDecoder.class}
)
public class SocketServer {

    private final ClientManager clientManager = ClientManager.getInstance();
    private final GameSessionManager gameSessionManager = GameSessionManager.getInstance();

    /**
     * @param session
     * @OnOpen allows us to intercept the creation of a new session. The session
     * class allows us to send data to the user. In the method onOpen, we'll let
     * the user know that the handshake was successful.
     */
    @OnOpen
    public void onOpen(Session session) {
        Client c = new Client(session);
        c.sendAction(new ConnectionSuccessAction());

        clientManager.addClient(c);
    }

    /**
     * When a user sends a message to the server, this method will intercept the
     * message and allow us to react to it. For now the message is read as a
     * String.
     *
     * @param a
     * @param sess
     */
    @OnMessage
    public void onMessage(Action a, Session sess) {
        Client c = clientManager.getClient(sess);

        if (a instanceof CreateGameRequestAction) {
            onClientMessage((CreateGameRequestAction) a, c);
        }
        if (a instanceof JoinGameRequestAction) {
            onClientMessage((JoinGameRequestAction) a, c);
        }
    }

    private void onClientMessage(CreateGameRequestAction a, Client c) {
        try {
            GameSession gm = gameSessionManager.createGame(c);
            c.sendAction(new CreateGameSuccessAction(gm.key));
        } catch (Error err) {
            c.sendAction(new CreateGameFailureAction(err.getMessage()));
        }
    }

    private void onClientMessage(JoinGameRequestAction a, Client c) {
        try {
            GameSession gm = gameSessionManager.joinGame(a.key, c);
            c.sendAction(new JoinGameSuccessAction(gm.key));
            gm.startGame();
        } catch (Error err) {
            c.sendAction(new CreateGameFailureAction(err.getMessage()));
        }
    }

    /**
     * The user closes the connection.
     *
     * Note: you can't send messages to the client from this method
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        Client c = clientManager.getClient(session);

        if (c.isInGame()) {
            c.getGame().stopGame();
        }

        clientManager.removeClient(c);
    }
}
