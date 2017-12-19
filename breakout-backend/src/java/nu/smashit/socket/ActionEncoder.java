package nu.smashit.socket;

import nu.smashit.socket.actions.ResponseAction;
import com.fasterxml.jackson.core.JsonProcessingException;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.smashit.socket.actions.GameStateUpdateAction;

/**
 *
 * @author jodus
 */
public class ActionEncoder implements Encoder.Text<ResponseAction> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public String encode(ResponseAction a) throws EncodeException {
        try {
            String s = objectMapper.writeValueAsString(a);
            s = s.substring(1, s.length() - 1);
            if (a instanceof GameStateUpdateAction) {
                s = s.replaceFirst("\"t\":\"GameStateUpdateAction\",", "");
            }
            return s;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ActionEncoder.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
