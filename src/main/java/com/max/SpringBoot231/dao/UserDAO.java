package com.max.SpringBoot231.dao;

import com.max.SpringBoot231.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();

    void update(User user);

    void userSave(User user);

    public User getUser(int id);

    public void deleteUser(int id);

    public User showName(String name);
}
