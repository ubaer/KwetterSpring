package com.fontys.kwetter.controller;

import com.fontys.kwetter.domain.Kweet;
import com.fontys.kwetter.service.KweetService;
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
@RequestMapping("/kweet")
public class KweetRestController {
    KweetService kweetService;

    @Autowired
    public KweetRestController(KweetService kweetService) {
        this.kweetService = kweetService;
    }

    /**
     * Gets all the kweets that exist.
     *
     * @return List<Kweet> of all kweets,
     * HttpStatus.OK when successful,
     * HttpStatus.NO_CONTENT when there are no kweets.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Kweet>> getAllKweets() {
        List<Kweet> kweets = kweetService.getAllKweets();
        HttpStatus status = HttpStatus.OK;

        if (kweets.isEmpty()) {
            status = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(kweets, status);
    }

    /**
     * Gets a kweet by its ID.
     *
     * @param kweetId of the kweet desired kweet.
     * @return Kweet with the corresponding ID,
     * HttpStatus.OK when the kweet is successfully found,
     * HttpStatus.NOT_FOUND if there is no kweet with the specific ID.
     */
    @RequestMapping(value = "{kweetId}", method = RequestMethod.GET)
    public ResponseEntity<Kweet> getKweetById(@PathVariable("kweetId") long kweetId) {
        Kweet kweet = kweetService.getKweetById(kweetId);
        HttpStatus status = HttpStatus.OK;

        if (kweet == null) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(kweet, status);
    }

    /**
     * Gets all the kweets from a user.
     *
     * @param username of the user.
     * @return List<Kweet> of all the users kweets,
     * HttpStatus.OK if kweets are successfully found,
     * HttpStatus.NOT_FOUND if the user has no kweets.
     */
    @RequestMapping(value = "{username}/all", method = RequestMethod.GET)
    public ResponseEntity<List<Kweet>> getKweetsByUsername(@PathVariable("username") String username) {
        List<Kweet> kweets = kweetService.getAllKweetsByUsername(username);
        HttpStatus status = HttpStatus.OK;

        if (kweets == null) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(kweets, status);
    }

    /**
     * Posts a kweet.
     *
     * @param username of the poster.
     * @param message of the kweet.
     * @return Kweet that is posted,
     * HttpStatus.OK if kweet is successfully posted,
     * HttpStatus.NOT_FOUND if the user doesn't exist.
     */
    @RequestMapping(value = "{username}/{message}", method = RequestMethod.POST)
    public ResponseEntity<Kweet> postKweet(@PathVariable("username") String username, @PathVariable("message") String message) {
        Kweet postedKweet = kweetService.postKweet(username, message);
        HttpStatus status = HttpStatus.OK;

        if(postedKweet == null){
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(postedKweet, status);
    }
}