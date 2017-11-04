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
    @Type(value = CreateGameRequestAction.class)
    ,
    @Type(value = JoinGameRequestAction.class)
    ,
    @Type(value = MovePaddleLeftAction.class)
    ,
    @Type(value = MovePaddleRightAction.class)
})
public interface RequestAction {

    @JsonIgnore
    public void handler(Client c);
}