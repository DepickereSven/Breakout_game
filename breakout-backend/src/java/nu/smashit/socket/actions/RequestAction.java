/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket.actions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nu.smashit.socket.Client;

/**
 *
 * @author jodus
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
    @Type(value = CreateSingleplayerRequestAction.class)
    ,
    @Type(value = CreateMultiplayerRequestAction.class)
    ,
    @Type(value = JoinPrivateGameRequestAction.class)
    ,
    @Type(value = JoinPublicGameRequestAction.class)
    ,
    @Type(value = MovePaddleStartAction.class)
    ,
    @Type(value = MovePaddleStopAction.class)
    ,
    @Type(value = PlayerReadyAction.class)
})
public interface RequestAction {

    @JsonIgnore
    public void handler(Client c);
}
