package nu.smashit.socket;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import nu.smashit.core.GameSessionManager;
import nu.smashit.core.SingleplayerSession;
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
        GameSessionManager gsm = GameSessionManager.getInstance();
        SingleplayerSession sps = (SingleplayerSession) gsm.createSingleplayerGame(client);
        
        client.setGame(sps);
        assertEquals(client.getGame(), sps);
        assertTrue(client.isInGame());

        client.removeGame();
        assertNull(client.getGame());
        assertFalse(client.isInGame());
    }
}
