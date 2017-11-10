package nu.smashit.data;

// @author Jonas

import nu.smashit.core.User;

public interface UserRepository {

    public void addUser(User user);
    public User getUser(String email);
    
}
