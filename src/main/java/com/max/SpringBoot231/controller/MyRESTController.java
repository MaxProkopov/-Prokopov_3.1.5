package com.max.SpringBoot231.controller;

import com.max.SpringBoot231.exception.NoSuchUserException;
import com.max.SpringBoot231.model.User;
import com.max.SpringBoot231.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {


    private UserService userService;

    @Autowired
    public MyRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/admin/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        User user = userService.getUser(id);

        if(user==null) {
            throw new NoSuchUserException("There is no user with ID = "
            + id + " in Database");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/admin/users")
    public ResponseEntity<HttpStatus> create(@RequestBody User user) {
        userService.userSave(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/admin/users/{id}")@Secured("ADMIN")
    public ResponseEntity<Void> editUser (@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);}

    @DeleteMapping("/admin/users/{id}")
    @Secured("ADMIN")public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
        User user = userService.getUser(id);
        if(user==null) {
            throw new NoSuchUserException("There is no user with ID = "
            + id + " in Database");
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    //Маппинг для навигационной панели
    @GetMapping("/barUserInfo")
    public ResponseEntity<User> index(Principal principal) {
        User user = userService.showName(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
