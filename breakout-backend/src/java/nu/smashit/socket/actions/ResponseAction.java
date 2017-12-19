package nu.smashit.socket.actions;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 *
 * @author jodus
 */
@JsonTypeInfo(use = Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "t")
@JsonSubTypes({
    @JsonSubTypes.Type(value = GameStateUpdateAction.class, name = "U")
})
public interface ResponseAction {

}
