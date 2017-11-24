package nu.smashit.data.dataobjects;

// @author Jonas

import com.fasterxml.jackson.annotation.JsonIgnore;
import nu.smashit.core.Game;
import nu.smashit.socket.Client;

public class User {

    private final Client client;
    private final String userID;
    private final String email;
    private final String username;
    private final String imageUrl;
    private final String country;
    private Game game;
    private int smashbit;

    private User(Client client, String userID, String email, int smashbit, String username, String imageUrl, String country) {
        this.client = client;
        this.userID = userID;
        this.email = email;
        this.smashbit = smashbit;
        this.username = username;
        this.imageUrl = imageUrl;
        this.country = country;
        this.game = null;
    }
    
    public boolean isInGame() {
        return game != null && getClient() != null;
    }

    public void setGame(Game gameSession) {
        this.game = gameSession;
    }

    @JsonIgnore
    public Game getGame() {
        return game;
    }

    public void removeGame() {
        this.game = null;
    }
    
    @JsonIgnore
    public Client getClient() {
        return client;
    }
    
    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public int getSmashbit() {
        return smashbit;
    }
    
    public void setSmashbit(int smashbit){
        this.smashbit = smashbit;
    }

    public String getUsername() {
        return username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCountry() {
        return country;
    }
    
    public static class Builder{
        
        private Client client;
        private String userID;
        private String email;
        private String username;
        private String imageUrl;
        private String country;
        private int smashbit;
        
        public User build(){
            return new User(client, userID, email, smashbit, username, imageUrl, country);
        }
        
        public Builder setClient(Client client){
            this.client = client;
            return this;
        }
        
        public Builder setUserData(String userID, String email, int smashbit, String username, String imageUrl, String country) {
            this.userID = userID;
            this.email = email;
            this.smashbit = smashbit;
            this.username = username;
            this.imageUrl = imageUrl;
            this.country = country;
            return this;
        }
        
    }
        
    public static Builder builder(){
        return new Builder();
    }

}
