package nu.smashit.data;

// @author Jonas
import nu.smashit.data.dataobjects.User;

public interface UserRepository {

    public void addUser(User user);

    public User.Builder getUser(String userID);

}
