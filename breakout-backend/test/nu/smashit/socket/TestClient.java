package nu.smashit.socket;

import javax.websocket.Session;
import nu.smashit.core.GameManager;
import nu.smashit.core.SingleplayerGame;
import nu.smashit.data.dataobjects.User;
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
        User user = User.builder()
                        .setUserData("1", "test.test@test.be", 0, "testing", "", "")
                        .setClient(client)
                        .build();
        client.setUser(user);
        
        GameManager gsm = GameManager.getInstance();
        int level = 1;
        
        SingleplayerGame sps = (SingleplayerGame) gsm.createSingleplayerGame(user, level);
        
        client.getUser().setGame(sps);
        assertEquals(client.getUser().getGame(), sps);

        client.getUser().removeGame();
        assertNull(client.getUser().getGame());
    }
}
