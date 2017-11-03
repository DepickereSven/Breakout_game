/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket;

import nu.smashit.socket.actions.RequestAction;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import org.msgpack.jackson.dataformat.MessagePackFactory;

/**
 *
 * @author jodus
 */
public class ActionDecoder implements Decoder.Text<RequestAction> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public RequestAction decode(String s) throws DecodeException {
        try {
            return objectMapper.readValue(s, RequestAction.class);
        } catch (IOException ex) {
            Logger.getLogger(ActionDecoder.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

}
