package nu.smashit.socket;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import nu.smashit.core.GameSessionManager;
import nu.smashit.core.MultiplayerSession;
import nu.smashit.core.Player;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;

/**
 *
 * @author Jonas
 */
public class TestMultiplayer {
    
    @Mock
    private HttpSession sessionPlayer1;
    
    @Mock
    private HttpSession sessionPlayer2;
    
    public TestMultiplayer() {}

    @Test
    public void makeMultiplayerGame() {
        Client clientPlayer1 = new Client((Session) sessionPlayer1);
        Client clientPlayer2 = new Client((Session) sessionPlayer2);
        GameSessionManager gsm = GameSessionManager.getInstance();
        
        MultiplayerSession mps = (MultiplayerSession) gsm.createMultiplayerGame(clientPlayer1);
        mps.join(clientPlayer2);
        
        Player player1 = mps.getPlayer(clientPlayer1);
        Player player2 = mps.getPlayer(clientPlayer2);
        assertEquals(player1.client, clientPlayer1);
        assertEquals(player2.client, clientPlayer2);
        
        assertEquals((int)player1.score.getPoints(), 100);
        assertEquals((int)player2.score.getPoints(), 100);
        
        mps.startGame();
        mps.stopGame();
        mps.getKey();
    }
    
}
