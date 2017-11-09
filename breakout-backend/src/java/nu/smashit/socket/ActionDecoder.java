package nu.smashit.socket;

import nu.smashit.socket.actions.RequestAction;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

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
