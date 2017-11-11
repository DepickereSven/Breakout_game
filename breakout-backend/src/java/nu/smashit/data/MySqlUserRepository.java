package nu.smashit.data;

// @author Jonas

import nu.smashit.data.dataobjects.User;

public class MySqlUserRepository implements UserRepository{

    @Override
    public void addUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUser(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
