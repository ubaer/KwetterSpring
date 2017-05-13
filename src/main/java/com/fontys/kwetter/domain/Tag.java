package com.fontys.kwetter.domain;

import javax.persistence.Entity;

import javax.persistence.*;
import java.util.*;
/**
 * Created by Kevin.
 */
@Entity
public class Tag {
    @Id
    String name;
    @OneToMany(cascade = {CascadeType.ALL})
    List<Kweet> kweets;

    public Tag(){}

    public Tag(String name) {
        this.name = name;
        kweets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public void addKweet(Kweet kweet){
        kweets.add(kweet);
    }

    public void removeKweet(Kweet kweet){
        if(kweets.contains(kweet)){
            kweets.remove(kweet);
        }
    }

    public List<Kweet> getKweets(){
        return kweets;
    }
}
