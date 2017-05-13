package com.fontys.kwetter.domain;

import javax.persistence.Entity;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Kevin.
 */
@Entity
public class Kweet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String message;
    Date date;
    long posterId;

    public long getPosterId() {
        return posterId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPosterId(long posterId) {
        this.posterId = posterId;
    }

    public void setMentions(List<User> mentions) {
        this.mentions = mentions;
    }

    public void setLovers(List<User> lovers) {
        this.lovers = lovers;
    }

    public String getMessage() {
        return message;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @OneToMany(cascade = {CascadeType.ALL}) @JoinTable(name = "kweet_mention")
    List<User> mentions;
    @OneToMany(cascade = {CascadeType.ALL})  @JoinTable(name = "kweet_lover")
    List<User> lovers;
    @ElementCollection
    List<String> tags;

    public Kweet(User poster, String message, ArrayList<Tag> tags, ArrayList<User> mentions) {
        this.message = message;
        this.posterId = poster.getId();
        this.date = new Date();
        this.mentions = mentions;
        this.lovers = new ArrayList<>();

        if (tags == null) {
            this.tags = new ArrayList<>();
        }

        if (mentions == null) {
            this.mentions = new ArrayList<>();
        }

        for (Tag tag : tags) {
            this.tags.add(tag.getName());
            tag.addKweet(this);
        }
        poster.addKweet(this);
    }
    public Kweet(long id, String message, Date date, User poster, ArrayList<Tag> tags, ArrayList<User> mentions) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.posterId = poster.getId();
        this.tags = new ArrayList<>();
        this.mentions = mentions;
        this.lovers = new ArrayList<>();

        if (mentions == null) {
            this.mentions = new ArrayList<>();
        }

        for (Tag tag : tags) {
            this.tags.add(tag.getName());
            tag.addKweet(this);
        }

        poster.addKweet(this);
    }
    public Kweet(User poster, String message) {
        this.message = message;
        this.posterId = poster.getId();
        this.date = new Date();
        this.lovers = new ArrayList<>();

        if (tags == null) {
            this.tags = new ArrayList<>();
        }

        if (mentions == null) {
            this.mentions = new ArrayList<>();
        }

        poster.addKweet(this);
    }
    public Kweet() {
    }

    public void addLover(User user) {
        if (!lovers.contains(user)) {
            lovers.add(user);
        }
    }

    public void removeLover(User user) {
        if (lovers.contains(user)) {
            lovers.remove(user);
        }
    }

    public List<User> getLovers() {
        return lovers;
    }

    public void addMentions(ArrayList<User> users) {
        if (mentions == null) {
            mentions = new ArrayList<>();
        }
        mentions.addAll(users);
    }

    public void addMention(User user){
        if(!mentions.contains(user)){
            mentions.add(user);
        }
    }

    public List<User> getMentions() {
        return mentions;
    }

    public void addTags(ArrayList<Tag> tags) {
        for (Tag tag : tags) {
            this.tags.add(tag.getName());
            tag.addKweet(this);
        }
    }

    public void addTag(Tag tag) {
        this.tags.add(tag.getName());
        tag.addKweet(this);
    }

    public List<String> getTags() {
        return tags;
    }
}