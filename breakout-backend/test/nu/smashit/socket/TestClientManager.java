package nu.smashit.socket;

import javax.websocket.Session;

import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 *
 * @author Jonas
 */
public class TestClientManager {

    public TestClientManager() {}

    @Test
    public void testAddAndRemoveClientInManager() {
        Session session = Mockito.mock(Session.class);
        when(session.getId()).thenReturn("5555");
        
        Client client = new Client(session);
        ClientManager cm = new ClientManager();

        cm.addClient(client);
        assertTrue(cm.containsClient(client));
        assertEquals(cm.getClient(session), client);

        cm.removeClient(client);
        assertFalse(cm.containsClient(client));
        assertNull(cm.getClient(session));
    }
    
}