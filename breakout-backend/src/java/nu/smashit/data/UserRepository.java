package nu.smashit.data;

// @author Jonas

import nu.smashit.data.dataobjects.User;

public interface UserRepository {

    public void addUser(User user);
    public User getUser(String email);
    public User getUser(int userID);
    
}