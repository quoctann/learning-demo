package com.quoctan.repository;

import com.quoctan.pojos.User;
import java.util.List;


public interface UserRepository {
    boolean addUser(User user);
    List<User> getUsers(String username);
}
