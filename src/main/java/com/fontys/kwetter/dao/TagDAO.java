package com.fontys.kwetter.dao;

import com.fontys.kwetter.domain.Tag;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Kevin.
 */
public interface TagDAO extends CrudRepository<Tag, String> {
}
