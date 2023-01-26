package com.max.SpringBoot231.service;

import com.max.SpringBoot231.model.Role;

import java.util.List;

public interface RoleServices {
    List<Role> listRole();

    Role show(int id);

    Role showName(String name);

    void save(Role role);
}
