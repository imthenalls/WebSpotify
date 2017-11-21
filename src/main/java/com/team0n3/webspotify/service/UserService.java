
package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.User;

public interface UserService {
    public User login(String username, String password);
    public void signup(String username, String password, String email);
}
