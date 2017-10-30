/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket;

import java.util.HashMap;
import java.util.Map;
import javax.websocket.Session;

/**
 *
 * @author jodus
 */
public class ClientManager {

    private static final ClientManager INSTANCE = new ClientManager();
    private final Map<String, Client> clientsMap;

    public ClientManager() {
        this.clientsMap = new HashMap<>();
    }

    public static ClientManager getInstance() {
        return INSTANCE;
    }

    public Client getClient(Session session) {
        return clientsMap.get(session.getId());
    }

    public void addClient(Client c) {
        clientsMap.put(c.getId(), c);
    }

    public void removeClient(Client c) {
        clientsMap.remove(c.getId());
    }

    public boolean containsClient(Client c) {
        return clientsMap.containsKey(c.getId());
    }
}
