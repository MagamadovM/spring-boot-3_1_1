package org.example.springboot.service;

import org.example.springboot.model.User;


import javax.validation.Valid;
import java.util.List;

public interface UserService {

    List<User> getAllUsers ();
    User getUserById(long id);
    void addUser(User user);
    void removeUser(long id);
    void updateUser(@Valid User user);
}