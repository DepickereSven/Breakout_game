/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.nio.ByteBuffer;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import org.msgpack.jackson.dataformat.MessagePackFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jodus
 */
public class ActionEncoder implements Encoder.Binary<ResponseAction> {

    private final ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public ByteBuffer encode(ResponseAction a) throws EncodeException {
        try {
            byte[] bytes = objectMapper.writeValueAsBytes(a);
            return ByteBuffer.wrap(bytes);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ActionEncoder.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
