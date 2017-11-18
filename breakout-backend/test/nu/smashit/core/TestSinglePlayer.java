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
public class TestSinglePlayer {
    
    @Mock
    private Session session;
    
    public TestSinglePlayer() {}

    @Test
    public void makeSinglePlayerGame() {
        Client client = new Client(session);
        User u = User.builder()
                        .setUserData("1", "test.test@test.be", 0, "testing", "", "")
                        .setClient(client)
                        .build();
        client.setUser(u);
        
        GameManager gsm = GameManager.getInstance();
        int level = 1;
        
        SingleplayerGame sps = (SingleplayerGame) gsm.createSingleplayerGame(client.getUser(), level);
        
        Player player = sps.getPlayer();
        assertEquals(player.getUser().getClient(), client);
        
        sps.createGameLoop();
        sps.stopGame();
        sps.getKey();
        
        try {
            sps.join(client.getUser());
            fail();
        } catch (Error e) {
        }
    }
    
}
