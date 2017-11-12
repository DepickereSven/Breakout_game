package nu.smashit.socket;

import javax.websocket.Session;
import nu.smashit.core.GameManager;
import nu.smashit.core.SingleplayerGame;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;

/**
 *
 * @author Jonas
 */
public class TestClient {
    
    @Mock
    private Session session;
    
    public TestClient() {}
    
    @Test
    public void testMakeClient() {
        Client client = new Client(session);
        GameManager gsm = GameManager.getInstance();
        SingleplayerGame sps = (SingleplayerGame) gsm.createSingleplayerGame(client);
        
        client.setGame(sps);
        assertEquals(client.getGame(), sps);

        client.removeGame();
        assertNull(client.getGame());
    }
}
