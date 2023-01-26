package com.max.SpringBoot231.service;

import com.max.SpringBoot231.dao.UserDAO;
import com.max.SpringBoot231.model.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;
    private final RoleServices roleServices;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl (UserDAO userDAO, RoleServices roleServices,@Lazy PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleServices = roleServices;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void update(User user) {
        encodePassword(user);
        setRolesForUser(user);
        userDAO.update(user);
    }


    @Override
    @Transactional
    public void userSave(User user) {
        encodePassword(user);
        setRolesForUser(user);
        userDAO.userSave(user);
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    @Override
    public User showName(String name) {
        return userDAO.showName(name);
    }


    public void setRolesForUser(User user) {
        user.setRoles(user.getRoles().stream().map(role -> roleServices.showName(role.getName())).collect(Collectors.toSet()));
    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
}
