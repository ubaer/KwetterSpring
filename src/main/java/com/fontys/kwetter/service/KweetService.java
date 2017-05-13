package com.fontys.kwetter.service;

import com.fontys.kwetter.dao.KweetDAO;
import com.fontys.kwetter.domain.Kweet;
import com.fontys.kwetter.domain.User;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin.
 */
@Service
@Transactional
public class KweetService {

    private KweetDAO kweetDAO;
    private UserService userService;

    @Autowired
    public KweetService(KweetDAO kweetDAO, UserService userService) {
        this.kweetDAO = kweetDAO;
        this.userService = userService;
        userService.createNewUser("peter");
    }

    public List<Kweet> getAllKweets() {
        return Lists.newArrayList(kweetDAO.findAll());
    }

    public Kweet getKweetById(long id) {
        return kweetDAO.findOne(id);
    }

    public List<Kweet> getAllKweetsByUsername(String username) {
        User foundUser = userService.findByUsername(username);
        List<Kweet> userKweets = new ArrayList<>();

        if (foundUser != null) {
            userKweets = kweetDAO.findByPosterId(foundUser.getId());
        }

        return userKweets;
    }

    public Kweet postKweet(String username, String message) {
        User foundUser = userService.findByUsername(username);
        Kweet newKweet = null;

        if (foundUser != null) {
            newKweet = new Kweet(foundUser, message);
        }

        return newKweet;
    }
}
