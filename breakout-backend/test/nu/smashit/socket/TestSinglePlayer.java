package nu.smashit.socket;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import nu.smashit.core.GameSessionManager;
import nu.smashit.core.Player;
import nu.smashit.core.SingleplayerSession;
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
        GameSessionManager gsm = GameSessionManager.getInstance();
        
        SingleplayerSession sps = (SingleplayerSession) gsm.createSingleplayerGame(client);
        
        Player player = sps.getPlayer();
        assertEquals(player.client, client);
        
        sps.startGame();
        sps.stopGame();
        sps.getKey();
        
        try {
            sps.join(client);
            fail();
        } catch (Error e) {
        }
    }
    
}
