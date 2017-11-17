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
        GameManager gsm = GameManager.getInstance();
        
        MultiplayerGame mps = (MultiplayerGame) gsm.createMultiplayerGame(clientPlayer1);
        mps.join(clientPlayer2);
        
        Player player1 = mps.getPlayer(clientPlayer1);
        Player player2 = mps.getPlayer(clientPlayer2);
        assertEquals(player1.client, clientPlayer1);
        assertEquals(player2.client, clientPlayer2);
        
        assertEquals((int)player1.score.getPoints(), 100);
        assertEquals((int)player2.score.getPoints(), 100);
        
        int level = 1;
        mps.createGameLoop();
        mps.stopGame();
        mps.getKey();
    }
    
}
