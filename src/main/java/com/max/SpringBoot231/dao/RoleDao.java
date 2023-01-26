package com.max.SpringBoot231.dao;

import com.max.SpringBoot231.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> listRole();

    Role show(int id);

    Role showName(String name);

    void save(Role role);
}
