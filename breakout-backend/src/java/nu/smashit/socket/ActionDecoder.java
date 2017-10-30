/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket;

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
public class ActionDecoder implements Decoder.Binary<RequestAction> {

    private final ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public RequestAction decode(ByteBuffer bytes) throws DecodeException {
        try {
            return objectMapper.readValue(bytes.array(), RequestAction.class);
        } catch (IOException ex) {
            Logger.getLogger(ActionDecoder.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean willDecode(ByteBuffer bytes) {
        return true;
    }

}
