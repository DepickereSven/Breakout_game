package nu.smashit.socket;

import nu.smashit.socket.actions.ConnectionSuccessAction;
import nu.smashit.socket.actions.RequestAction;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import nu.smashit.core.Game;
import nu.smashit.core.GameManager;
import nu.smashit.socket.actions.GameStopAction;

@ServerEndpoint(
        value = "/socket",
        encoders = {ActionEncoder.class},
        decoders = {ActionDecoder.class}
)
public class SocketServer {

    private final ClientManager clientManager = ClientManager.getInstance();
    private final GameManager gameSessionManager = GameManager.getInstance();

    /**
     * @param session
     * @OnOpen allows us to intercept the creation of a new session. The session
     * class allows us to send data to the user. In the method onOpen, we'll let
     * the user know that the handshake was successful.
     */
    @OnOpen
    public void onOpen(Session session) {
        Client c = new Client(session);
        c.sendAction(new ConnectionSuccessAction(c.getId()));

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
    public void onMessage(RequestAction a, Session sess) {
        Client c = clientManager.getClient(sess);

        a.handler(c);
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
            Game gm = c.getGame();
            gm.broadcastAction(new GameStopAction());
            c.getGame().stopGame();
        }

        clientManager.removeClient(c);
    }
}
