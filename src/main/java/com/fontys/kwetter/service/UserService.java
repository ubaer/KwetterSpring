package com.fontys.kwetter.service;

import com.fontys.kwetter.dao.UserDAO;
import com.fontys.kwetter.domain.User;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kevin.
 */
@Service
@Transactional
public class UserService {
    UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers(){
        return Lists.newArrayList(userDAO.findAll());
    }

    /**
     * creates a new User
     * @param username of the new user
     * @return new User when created successfully
     * null if the username is already taken
     */
    public User createNewUser(String username){
        User newUser = new User(username);
        User savedUser = null;
        if(userDAO.findByUserName(username) == null){
            savedUser = userDAO.save(newUser);
        }

        return savedUser;
    }

    public User findByUsername(String username){
        return userDAO.findByUserName(username);
    }
}
