package nu.smashit.core;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import nu.smashit.data.dataobjects.User;
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
    private Session sessionPlayer1;
    
    @Mock
    private Session sessionPlayer2;
    
    public TestMultiplayer() {}

    @Test
    public void makeMultiplayerGame() {
        Client clientPlayer1 = new Client(sessionPlayer1);
        User u = User.builder()
                        .setUserData("1", "test.test@test.be", 0, "testing", "", "")
                        .setClient(clientPlayer1)
                        .build();
        clientPlayer1.setUser(u);
        
        Client clientPlayer2 = new Client(sessionPlayer2);
        User u2 = User.builder()
                        .setUserData("1", "test.test@test.be", 0, "testing", "", "")
                        .setClient(clientPlayer2)
                        .build();
        clientPlayer2.setUser(u2);

        GameManager gsm = GameManager.getInstance();
        
        MultiplayerGame mps = (MultiplayerGame) gsm.createMultiplayerGame(clientPlayer1.getUser());
        mps.join(clientPlayer2.getUser());
        
        Player player1 = mps.getPlayer(clientPlayer1.getUser());
        Player player2 = mps.getPlayer(clientPlayer2.getUser());
        assertEquals(player1.getUser().getClient(), clientPlayer1);
        assertEquals(player2.getUser().getClient(), clientPlayer2);
        
        assertEquals((int)player1.getScore().getPoints(), 100);
        assertEquals((int)player2.getScore().getPoints(), 100);
        
        int level = 1;
        mps.createGameLoop();
        mps.stopGame();
        mps.getKey();
    }
    
}
