package com.fontys.kwetter.controller;

import com.fontys.kwetter.domain.User;
import com.fontys.kwetter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Kevin.
 */
@RestController
@RequestMapping("/user")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }


    /**
     * Gets all the users that exist.
     *
     * @return List<User> of all users,
     * HttpStatus.OK when successful,
     * HttpStatus.NO_CONTENT when there are no users.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        HttpStatus status = HttpStatus.OK;

        if (users.isEmpty()) {
            status = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(users, status);
    }

    /**
     * Gets a User by its username
     *
     * @param username of the desired user.
     * @return the requested user,
     * HttpStatus.OK if user is found,
     * HttpStatus.NOT_FOUND if there is no user known with the corresponding username.
     */
    @RequestMapping(value = "{username}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
        User foundUser = userService.findByUsername(username);
        HttpStatus status = HttpStatus.OK;

        if(foundUser == null){
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(foundUser, status);
    }

    /**
     * Create a new user
     *
     * @param username of the new user.
     * @return the newly created User, null if the username is already taken,
     * HttpStatus.OK if the user is created,
     * HttpStatus.ALREADY_REPORTED if the username is already taken.
     */
    @RequestMapping(value = "{username}", method =  RequestMethod.POST)
    public ResponseEntity<User> createNewUser(@PathVariable("username") String username){
        User createdUser = userService.createNewUser(username);
        HttpStatus status = HttpStatus.OK;

        if(createdUser == null){
            status = HttpStatus.ALREADY_REPORTED;
        }

        return new ResponseEntity<>(createdUser, status);
    }
}
