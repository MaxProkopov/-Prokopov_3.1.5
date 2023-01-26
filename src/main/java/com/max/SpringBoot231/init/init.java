package com.max.SpringBoot231.init;

import com.max.SpringBoot231.model.Role;
import com.max.SpringBoot231.model.User;
import com.max.SpringBoot231.service.RoleServices;
import com.max.SpringBoot231.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class init {
    private final RoleServices roleServices;
    private final UserService userService;

    @Autowired
    public init(RoleServices roleServices, UserService userServices) {
        this.roleServices = roleServices;
        this.userService = userServices;
    }

    @PostConstruct
    public void postConstruct() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            Role user = new Role("USER");
            Role admin = new Role("ADMIN");
            roleServices.save(admin);
            roleServices.save(user);

            Set<Role> adminRole = new HashSet<>();
            adminRole.add(admin);
            adminRole.add(user);
            Set<Role> userRole = new HashSet<>();
            userRole.add(user);

            userService.userSave(new User("admin", "adminov", 28, 1300, "admin", adminRole));
            userService.userSave(new User("user", "userov", 25, 800, "user", userRole));
        }
    }

}
