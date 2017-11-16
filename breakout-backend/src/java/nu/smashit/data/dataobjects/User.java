package nu.smashit.data.dataobjects;

// @author Jonas
public class User {

    private String userID;
    private String email;
    private int smashbit;
    private String username;
    private String imageUrl;
    private String country;

    public User(String userID, String email, int smashbit, String username, String imageUrl, String country) {
        setUserID(userID);
        setEmail(email);
        setSmashbit(smashbit);
        setUsername(username);
        setImageUrl(imageUrl);
        setCountry(country);
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

    public String getUsername() {
        return username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCountry() {
        return country;
    }

    private void setUserID(String userID) {
        this.userID = userID;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setSmashbit(int smashbit) {
        this.smashbit = smashbit;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private void setCountry(String country) {
        this.country = country;
    }

}
