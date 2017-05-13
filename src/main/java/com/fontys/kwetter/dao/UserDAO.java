package com.fontys.kwetter.dao;

import com.fontys.kwetter.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Kevin.
 */
public interface UserDAO extends CrudRepository<User, Long> {
    User findByUserName(String userName);
}
