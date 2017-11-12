package nu.smashit.core;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import nu.smashit.socket.Client;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;

/**
 *
 * @author Jonas
 */
public class TestSinglePlayer {
    
    @Mock
    private HttpSession session;
    
    public TestSinglePlayer() {}

    @Test
    public void makeSinglePlayerGame() {
        Client client = new Client((Session) session);
        GameManager gsm = GameManager.getInstance();
        
        SingleplayerGame sps = (SingleplayerGame) gsm.createSingleplayerGame(client);
        
        Player player = sps.getPlayer();
        assertEquals(player.client, client);
        
        sps.createGameLoop();
        sps.stopGame();
        sps.getKey();
        
        try {
            sps.join(client);
            fail();
        } catch (Error e) {
        }
    }
    
}
