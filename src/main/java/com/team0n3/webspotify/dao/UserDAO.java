
package com.team0n3.webspotify.dao;

import java.util.List;
import com.team0n3.webspotify.model.User;

public interface UserDAO {
    public void addUser(User user);
    public User getUser(String username);
    public List<User> listUsers();
    public void updateUser(User user);
    public void deleteUser(String Username);
}
